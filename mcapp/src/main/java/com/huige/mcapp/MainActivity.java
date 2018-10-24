package com.huige.mcapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huige.mcapp.adapter.RelayLayoutRecyclerViewAdapter;
import com.huige.mcapp.utils.SendCmdUtils;
import com.huige.mcapp.view.BaseActivity;
import com.huige.mcapp.vo.VoRelayParameter;
import com.huige.mcapp.vo.VoRs232Parameter;
import com.huige.mcapp.vo.VoRunParameter;

import org.apache.commons.codec.DecoderException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @ViewInject(R.id.rv_relay_layout)
    private RecyclerView rv_relay_layout;
    @ViewInject(R.id.ll_rs232_layout)
    private LinearLayout ll_rs232_layout;

    private VoRunParameter voRunParameter;
    private AlertDialog dialog;
    private SoundPool soundPool;
    private int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        RecyclerView.LayoutManager layoutManager =
                new StaggeredGridLayoutManager(3, // 每行显示的item项数目
                        StaggeredGridLayoutManager.VERTICAL); // 垂直排列
        rv_relay_layout.setLayoutManager(layoutManager);
        voRunParameter = (VoRunParameter) mACache.getAsObject(VoRunParameter.RUN_PARAMETER); //获取配置参数

        if (voRunParameter != null && voRunParameter.getRelayParameters() != null && voRunParameter.getRelayParameters().size() > 0) {  //初始化继电按钮
            RelayLayoutRecyclerViewAdapter relayLayoutRecyclerViewAdapter = new RelayLayoutRecyclerViewAdapter(voRunParameter.getRelayParameters(), onRelayLayoutListener);
            rv_relay_layout.setAdapter(relayLayoutRecyclerViewAdapter);
        }

        //初始化RS232按钮
        if (voRunParameter != null && voRunParameter.getRs232Parameters() != null && voRunParameter.getRs232Parameters().size() > 0) {
            for (int i = 0; i < voRunParameter.getRs232Parameters().size(); i++) {
                if (voRunParameter.getRs232Parameters().get(i).getRs232Enable()) {
                    LayoutInflater inflater = LayoutInflater.from(this);
                    View view = inflater.inflate(R.layout.item_rs232, null);
                    view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                    Button item_button_off = (Button) view.findViewById(R.id.item_button_off);
                    Button item_button_on = (Button) view.findViewById(R.id.item_button_on);
                    TextView item_title = (TextView) view.findViewById(R.id.item_title);
                    item_title.setText(voRunParameter.getRs232Parameters().get(i).getTitle());
                    item_button_off.setTag(i);
                    item_button_on.setTag(i);
                    item_button_on.setOnClickListener(onClickOnListener);
                    item_button_off.setOnClickListener(onClickOffListener);
                    ll_rs232_layout.addView(view);
                }
            }
        }

        // 失败弹框
        dialog = new AlertDialog.Builder(this)
                .setTitle("消息")
                .setMessage("控制指令发送异常，请检查设备是否启动或关闭！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .create();

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                // 设置场景
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                // 设置类型
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                // 设置上面的属性
                .setAudioAttributes(audioAttributes)
                // 设置最多1个音频流文件
                .setMaxStreams(1).build();

        soundId = soundPool.load(this, R.raw.button_sound, 1);
    }


    //RS232打开指令
    View.OnClickListener onClickOnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final VoRs232Parameter rs232Parameter = voRunParameter.getRs232Parameters().get((int) view.getTag());
            playSound();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SendCmdUtils.CallSendCmd(voRunParameter.getRunParameterGlobal().getRs232Host(), rs232Parameter.getOnCmd(), rs232Parameter.getRs232Port());
                        Toast.makeText(MainActivity.this, "操作成功", Toast.LENGTH_LONG).show();
                    } catch (DecoderException | IOException e) {
                        e.printStackTrace();
                        dialog.show();
                    }
                }
            });
        }
    };

    //RS232关闭指令
    View.OnClickListener onClickOffListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final VoRs232Parameter rs232Parameter = voRunParameter.getRs232Parameters().get((int) view.getTag());
            playSound();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SendCmdUtils.CallSendCmd(voRunParameter.getRunParameterGlobal().getRs232Host(), rs232Parameter.getOffCmd(), rs232Parameter.getRs232Port());
                        Toast.makeText(MainActivity.this, "操作成功", Toast.LENGTH_LONG).show();
                    } catch (DecoderException | IOException e) {
                        e.printStackTrace();
                        dialog.show();
                    }
                }
            });
        }
    };

    //继电端口指令
    private RelayLayoutRecyclerViewAdapter.OnRelayLayoutListener onRelayLayoutListener = new RelayLayoutRecyclerViewAdapter.OnRelayLayoutListener() {
        @Override
        public void onClick(View view, final int index) {
            playSound();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    boolean result = SendCmdUtils.CallRelayWriteSingle(voRunParameter.getRunParameterGlobal().getRelayHost(), voRunParameter.getRunParameterGlobal().getRelayPort(), index - 1, voRunParameter.getRunParameterGlobal().getRelayFlashTime());
                    if (result) {
                        Toast.makeText(MainActivity.this, "操作成功", Toast.LENGTH_LONG).show();
                        return;
                    }
                    dialog.show();
                }
            });
        }
    };


    @Event(R.id.btn_control_all)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_control_all: //总控
                playSound();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean result = SendCmdUtils.CallRelayWriteAll(voRunParameter.getRunParameterGlobal().getRelayHost(), voRunParameter.getRunParameterGlobal().getRelayPort(), voRunParameter.getRunParameterGlobal().getRelayFlashTime());
                        if (result) {
                            Toast.makeText(MainActivity.this, "操作成功", Toast.LENGTH_LONG).show();
                            return;
                        }
                        dialog.show();
                    }
                });
        }
    }

    private void playSound() {
        soundPool.play(soundId, 1, 1, 0, 0, 1);
    }
}
