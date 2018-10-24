package com.huige.mcapp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.google.zxing.WriterException;
import com.huige.mcapp.R;
import com.huige.mcapp.utils.ZipUtils;
import com.huige.mcapp.vo.VoRelayParameter;
import com.huige.mcapp.vo.VoRs232Parameter;
import com.huige.mcapp.vo.VoRunParameter;
import com.huige.mcapp.vo.VoRunParameterGlobal;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;
import com.yzq.zxinglibrary.encode.CodeCreator;

import org.apache.commons.lang3.StringUtils;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    public static final String TAG = SettingActivity.class.getSimpleName();

    private static final int REQUEST_CODE_SCAN = 0x01;

    private VoRunParameter runParameter;
    @ViewInject(R.id.et_relay_host)
    private EditText et_relay_host;
    @ViewInject(R.id.et_relay_port)
    private EditText et_relay_port;
    @ViewInject(R.id.et_relay_flash_time)
    private EditText et_relay_flash_time;
    @ViewInject(R.id.et_rs232_host)
    private EditText et_rs232_host;
    @ViewInject(R.id.cb_relay_enable)
    private CheckBox cb_relay_enable;
    @ViewInject(R.id.cb_rs232_enable)
    private CheckBox cb_rs232_enable;
    @ViewInject(R.id.et_bank_name)
    private EditText et_bank_name;


    @ViewInject(R.id.et_rs232_1_title)
    private EditText et_rs232_1_title;
    @ViewInject(R.id.et_rs232_1_on_cmd)
    private EditText et_rs232_1_on_cmd;
    @ViewInject(R.id.et_rs232_1_off_cmd)
    private EditText et_rs232_1_off_cmd;
    @ViewInject(R.id.cb_rs232_1_enable)
    private CheckBox cb_rs232_1_enable;
    @ViewInject(R.id.et_rs232_1_port)
    private EditText et_rs232_1_port;

    @ViewInject(R.id.et_rs232_2_title)
    private EditText et_rs232_2_title;
    @ViewInject(R.id.et_rs232_2_on_cmd)
    private EditText et_rs232_2_on_cmd;
    @ViewInject(R.id.et_rs232_2_off_cmd)
    private EditText et_rs232_2_off_cmd;
    @ViewInject(R.id.cb_rs232_2_enable)
    private CheckBox cb_rs232_2_enable;
    @ViewInject(R.id.et_rs232_2_port)
    private EditText et_rs232_2_port;

    @ViewInject(R.id.et_rs232_3_title)
    private EditText et_rs232_3_title;
    @ViewInject(R.id.et_rs232_3_on_cmd)
    private EditText et_rs232_3_on_cmd;
    @ViewInject(R.id.et_rs232_3_off_cmd)
    private EditText et_rs232_3_off_cmd;
    @ViewInject(R.id.cb_rs232_3_enable)
    private CheckBox cb_rs232_3_enable;
    @ViewInject(R.id.et_rs232_3_port)
    private EditText et_rs232_3_port;


    @ViewInject(R.id.et_relay_1_title)
    private EditText et_relay_1_title;
    @ViewInject(R.id.cb_relay_1_enable)
    private CheckBox cb_relay_1_enable;
    @ViewInject(R.id.sn_relay_1_icon)
    private Spinner sn_relay_1_icon;

    @ViewInject(R.id.et_relay_2_title)
    private EditText et_relay_2_title;
    @ViewInject(R.id.cb_relay_2_enable)
    private CheckBox cb_relay_2_enable;
    @ViewInject(R.id.sn_relay_2_icon)
    private Spinner sn_relay_2_icon;

    @ViewInject(R.id.et_relay_3_title)
    private EditText et_relay_3_title;
    @ViewInject(R.id.cb_relay_3_enable)
    private CheckBox cb_relay_3_enable;
    @ViewInject(R.id.sn_relay_3_icon)
    private Spinner sn_relay_3_icon;

    @ViewInject(R.id.et_relay_4_title)
    private EditText et_relay_4_title;
    @ViewInject(R.id.cb_relay_4_enable)
    private CheckBox cb_relay_4_enable;
    @ViewInject(R.id.sn_relay_4_icon)
    private Spinner sn_relay_4_icon;

    @ViewInject(R.id.et_relay_5_title)
    private EditText et_relay_5_title;
    @ViewInject(R.id.cb_relay_5_enable)
    private CheckBox cb_relay_5_enable;
    @ViewInject(R.id.sn_relay_5_icon)
    private Spinner sn_relay_5_icon;

    @ViewInject(R.id.et_relay_6_title)
    private EditText et_relay_6_title;
    @ViewInject(R.id.cb_relay_6_enable)
    private CheckBox cb_relay_6_enable;
    @ViewInject(R.id.sn_relay_6_icon)
    private Spinner sn_relay_6_icon;

    @ViewInject(R.id.et_relay_7_title)
    private EditText et_relay_7_title;
    @ViewInject(R.id.cb_relay_7_enable)
    private CheckBox cb_relay_7_enable;
    @ViewInject(R.id.sn_relay_7_icon)
    private Spinner sn_relay_7_icon;

    @ViewInject(R.id.et_relay_8_title)
    private EditText et_relay_8_title;
    @ViewInject(R.id.cb_relay_8_enable)
    private CheckBox cb_relay_8_enable;
    @ViewInject(R.id.sn_relay_8_icon)
    private Spinner sn_relay_8_icon;

    @ViewInject(R.id.et_relay_9_title)
    private EditText et_relay_9_title;
    @ViewInject(R.id.cb_relay_9_enable)
    private CheckBox cb_relay_9_enable;
    @ViewInject(R.id.sn_relay_9_icon)
    private Spinner sn_relay_9_icon;

    @ViewInject(R.id.et_relay_10_title)
    private EditText et_relay_10_title;
    @ViewInject(R.id.cb_relay_10_enable)
    private CheckBox cb_relay_10_enable;
    @ViewInject(R.id.sn_relay_10_icon)
    private Spinner sn_relay_10_icon;

    @ViewInject(R.id.et_relay_11_title)
    private EditText et_relay_11_title;
    @ViewInject(R.id.cb_relay_11_enable)
    private CheckBox cb_relay_11_enable;
    @ViewInject(R.id.sn_relay_11_icon)
    private Spinner sn_relay_11_icon;

    @ViewInject(R.id.et_relay_12_title)
    private EditText et_relay_12_title;
    @ViewInject(R.id.cb_relay_12_enable)
    private CheckBox cb_relay_12_enable;
    @ViewInject(R.id.sn_relay_12_icon)
    private Spinner sn_relay_12_icon;

    @ViewInject(R.id.et_relay_13_title)
    private EditText et_relay_13_title;
    @ViewInject(R.id.cb_relay_13_enable)
    private CheckBox cb_relay_13_enable;
    @ViewInject(R.id.sn_relay_13_icon)
    private Spinner sn_relay_13_icon;

    @ViewInject(R.id.et_relay_14_title)
    private EditText et_relay_14_title;
    @ViewInject(R.id.cb_relay_14_enable)
    private CheckBox cb_relay_14_enable;
    @ViewInject(R.id.sn_relay_14_icon)
    private Spinner sn_relay_14_icon;

    @ViewInject(R.id.et_relay_15_title)
    private EditText et_relay_15_title;
    @ViewInject(R.id.cb_relay_15_enable)
    private CheckBox cb_relay_15_enable;
    @ViewInject(R.id.sn_relay_15_icon)
    private Spinner sn_relay_15_icon;

    @ViewInject(R.id.et_relay_16_title)
    private EditText et_relay_16_title;
    @ViewInject(R.id.cb_relay_16_enable)
    private CheckBox cb_relay_16_enable;
    @ViewInject(R.id.sn_relay_16_icon)
    private Spinner sn_relay_16_icon;

    private List<EditText> relayTitles = new ArrayList<>();
    private List<CheckBox> relayEnable = new ArrayList<>();
    private List<Spinner> relayIcon = new ArrayList<>();
    private String[] icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        icons = getResources().getStringArray(R.array.relay_port_icon);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_authorization, null);
        final EditText et_dialog_authorization = (EditText) view.findViewById(R.id.et_dialog_authorization);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("权限认证")
                .setIcon(R.mipmap.app_ico)
                .setView(view)
                .setPositiveButton("确定", null).show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_dialog_authorization.getText().toString().equals("huige666")) {
                    dialog.dismiss();
                    init();
                } else {
                    Toast.makeText(getApplicationContext(), "鉴权失败", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void init() {
        relayTitles.add(et_relay_1_title);
        relayTitles.add(et_relay_2_title);
        relayTitles.add(et_relay_3_title);
        relayTitles.add(et_relay_4_title);
        relayTitles.add(et_relay_5_title);
        relayTitles.add(et_relay_6_title);
        relayTitles.add(et_relay_7_title);
        relayTitles.add(et_relay_8_title);
        relayTitles.add(et_relay_9_title);
        relayTitles.add(et_relay_10_title);
        relayTitles.add(et_relay_11_title);
        relayTitles.add(et_relay_12_title);
        relayTitles.add(et_relay_13_title);
        relayTitles.add(et_relay_14_title);
        relayTitles.add(et_relay_15_title);
        relayTitles.add(et_relay_16_title);

        relayEnable.add(cb_relay_1_enable);
        relayEnable.add(cb_relay_2_enable);
        relayEnable.add(cb_relay_3_enable);
        relayEnable.add(cb_relay_4_enable);
        relayEnable.add(cb_relay_5_enable);
        relayEnable.add(cb_relay_6_enable);
        relayEnable.add(cb_relay_7_enable);
        relayEnable.add(cb_relay_8_enable);
        relayEnable.add(cb_relay_9_enable);
        relayEnable.add(cb_relay_10_enable);
        relayEnable.add(cb_relay_11_enable);
        relayEnable.add(cb_relay_12_enable);
        relayEnable.add(cb_relay_13_enable);
        relayEnable.add(cb_relay_14_enable);
        relayEnable.add(cb_relay_15_enable);
        relayEnable.add(cb_relay_16_enable);

        relayIcon.add(sn_relay_1_icon);
        relayIcon.add(sn_relay_2_icon);
        relayIcon.add(sn_relay_3_icon);
        relayIcon.add(sn_relay_4_icon);
        relayIcon.add(sn_relay_5_icon);
        relayIcon.add(sn_relay_6_icon);
        relayIcon.add(sn_relay_7_icon);
        relayIcon.add(sn_relay_8_icon);
        relayIcon.add(sn_relay_9_icon);
        relayIcon.add(sn_relay_10_icon);
        relayIcon.add(sn_relay_11_icon);
        relayIcon.add(sn_relay_12_icon);
        relayIcon.add(sn_relay_13_icon);
        relayIcon.add(sn_relay_14_icon);
        relayIcon.add(sn_relay_15_icon);
        relayIcon.add(sn_relay_16_icon);

        runParameter = (VoRunParameter) mACache.getAsObject(VoRunParameter.RUN_PARAMETER);
        if (runParameter == null) {
            runParameter = new VoRunParameter();
            runParameter.setRunParameterGlobal(new VoRunParameterGlobal());
        }
        initViewData(runParameter);
    }

    private void initViewData(VoRunParameter parameter) {
        // 公共配置赋值
        VoRunParameterGlobal runParameterGlobal = parameter.getRunParameterGlobal();
        if (runParameterGlobal != null) {
            if (StringUtils.isNoneBlank(runParameterGlobal.getRelayHost())) {
                et_relay_host.setText(runParameterGlobal.getRelayHost());
            }
            if (runParameterGlobal.getRelayPort() != null) {
                et_relay_port.setText(runParameterGlobal.getRelayPort().toString());
            }
            if (runParameterGlobal.getRelayFlashTime() != null) {
                et_relay_flash_time.setText(runParameterGlobal.getRelayFlashTime().toString());
            }
            if (StringUtils.isNoneBlank(runParameterGlobal.getRs232Host())) {
                et_rs232_host.setText(runParameterGlobal.getRs232Host());
            }
            if (StringUtils.isNoneBlank(runParameterGlobal.getBankName())) {
                et_bank_name.setText(runParameterGlobal.getBankName());
            }
            cb_relay_enable.setChecked(runParameterGlobal.getRelayEnable());
            cb_rs232_enable.setChecked(runParameterGlobal.getRs232Enable());
        }

        //rs232端口赋值
        List<VoRs232Parameter> rs232Parameters = parameter.getRs232Parameters();
        if (rs232Parameters != null && rs232Parameters.size() == 3) {
            if (rs232Parameters.get(0) != null) {
                if (rs232Parameters.get(0).getRs232Port() != null) {
                    et_rs232_1_port.setText(rs232Parameters.get(0).getRs232Port().toString());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(0).getOnCmd())) {
                    et_rs232_1_on_cmd.setText(rs232Parameters.get(0).getOnCmd());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(0).getOffCmd())) {
                    et_rs232_1_off_cmd.setText(rs232Parameters.get(0).getOffCmd());
                }
                if (rs232Parameters.get(0).getRs232Enable() != null) {
                    cb_rs232_1_enable.setChecked(rs232Parameters.get(0).getRs232Enable());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(0).getTitle())) {
                    et_rs232_1_title.setText(rs232Parameters.get(0).getTitle());
                }
            }
            if (rs232Parameters.get(1) != null) {
                if (rs232Parameters.get(1).getRs232Port() != null) {
                    et_rs232_2_port.setText(rs232Parameters.get(1).getRs232Port().toString());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(1).getOnCmd())) {
                    et_rs232_2_on_cmd.setText(rs232Parameters.get(1).getOnCmd());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(1).getOffCmd())) {
                    et_rs232_2_off_cmd.setText(rs232Parameters.get(1).getOffCmd());
                }
                if (rs232Parameters.get(1).getRs232Enable() != null) {
                    cb_rs232_2_enable.setChecked(rs232Parameters.get(1).getRs232Enable());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(1).getTitle())) {
                    et_rs232_2_title.setText(rs232Parameters.get(1).getTitle());
                }
            }
            if (rs232Parameters.get(2) != null) {
                if (rs232Parameters.get(2).getRs232Port() != null) {
                    et_rs232_3_port.setText(rs232Parameters.get(2).getRs232Port().toString());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(2).getOnCmd())) {
                    et_rs232_3_on_cmd.setText(rs232Parameters.get(2).getOnCmd());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(2).getOffCmd())) {
                    et_rs232_3_off_cmd.setText(rs232Parameters.get(2).getOffCmd());
                }
                if (rs232Parameters.get(2).getRs232Enable() != null) {
                    cb_rs232_3_enable.setChecked(rs232Parameters.get(2).getRs232Enable());
                }
                if (StringUtils.isNoneBlank(rs232Parameters.get(2).getTitle())) {
                    et_rs232_3_title.setText(rs232Parameters.get(2).getTitle());
                }
            }
        }

        //继电线圈赋值
        List<VoRelayParameter> relayParameters = parameter.getRelayParameters();
        if (relayParameters != null && relayParameters.size() == 16) {
            for (int i = 0; i < relayTitles.size(); i++) {
                VoRelayParameter relayParameter = relayParameters.get(i);
                if (relayParameter != null) {
                    if (StringUtils.isNoneBlank(relayParameter.getRelayTitle())) {
                        relayTitles.get(i).setText(relayParameter.getRelayTitle());
                    }
                    if (relayParameter.getRelayEnable() != null) {
                        relayEnable.get(i).setChecked(relayParameter.getRelayEnable());
                    }
                    if (StringUtils.isNoneBlank(relayParameter.getRelayIcon())) {
                        for (int j = 0; j < icons.length; j++) {
                            if (relayParameter.getRelayIcon().equals(icons[j])) {
                                relayIcon.get(i).setSelection(j, true);
                            }
                        }
                    }
                }
            }
        }
    }

    @Event(value = {R.id.btn_all_save, R.id.btn_qrcode_scanning, R.id.btn_qrcode_save})
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_all_save:
                save();
                Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_qrcode_scanning:
                Intent intent = new Intent(SettingActivity.this, CaptureActivity.class);
                ZxingConfig config = new ZxingConfig();
                config.setPlayBeep(true);//是否播放扫描声音 默认为true
                config.setShake(false);//是否震动  默认为true
                config.setDecodeBarCode(false);//是否扫描条形码 默认为true
                config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为淡蓝色
                // config.setFrameLineColor(R.color.colorAccent); //设置扫描框边框颜色 默认无色
                config.setFullScreenScan(true);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                break;
            case R.id.btn_qrcode_save:
                save();
                String resultData = gson.toJson(runParameter);
                Log.i(TAG,"二维码生成数据：\n" + resultData);
                try {
                    Log.d(TAG, ZipUtils.zip(resultData));
                    Bitmap bitmap = CodeCreator.createQRCode(ZipUtils.zip(resultData), 600, 600, null);
                    if (saveImageToGallery(this, bitmap)) {
                        Toast.makeText(this, "导出成功！", Toast.LENGTH_SHORT).show();
                        break;
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "导出失败！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public boolean saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "huige";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
//            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                try {
                    Log.d(TAG, ZipUtils.unzip(content));
                    VoRunParameter parameter = gson.fromJson(ZipUtils.unzip(content), VoRunParameter.class);
                    if (parameter != null) {
                        initViewData(parameter);
                    } else {
                        Toast.makeText(this, "抱歉，数据解析错误。", Toast.LENGTH_LONG).show();
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "抱歉，数据解析错误。", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    private void save() {
        // 保存

        // 保存公共配置
        VoRunParameterGlobal runParameterGlobal = runParameter.getRunParameterGlobal();
        if (cb_relay_enable.isChecked()) {
            if (StringUtils.isNoneBlank(et_relay_host.getText().toString().trim())) {
                runParameterGlobal.setRelayHost(et_relay_host.getText().toString().trim());
            } else {
                Toast.makeText(this, "【全局配置】继电主机地址不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_relay_port.getText().toString().trim())) {
                runParameterGlobal.setRelayPort(Integer.parseInt(et_relay_port.getText().toString().trim()));
            } else {
                Toast.makeText(this, "【全局配置】继电主机端口不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_relay_flash_time.getText().toString().trim())) {
                runParameterGlobal.setRelayFlashTime(Integer.parseInt(et_relay_flash_time.getText().toString().trim()));
            } else {
                Toast.makeText(this, "【全局配置】继电主机闪断时间不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_bank_name.getText().toString().trim())) {
                runParameterGlobal.setBankName(et_bank_name.getText().toString().trim());
            } else {
                Toast.makeText(this, "【全局配置】银行名称不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (StringUtils.isNoneBlank(et_relay_host.getText().toString().trim())) {
                runParameterGlobal.setRelayHost(et_relay_host.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_relay_port.getText().toString().trim())) {
                runParameterGlobal.setRelayPort(Integer.parseInt(et_relay_port.getText().toString().trim()));
            }
            if (StringUtils.isNoneBlank(et_relay_flash_time.getText().toString().trim())) {
                runParameterGlobal.setRelayFlashTime(Integer.parseInt(et_relay_flash_time.getText().toString().trim()));
            }
            if (StringUtils.isNoneBlank(et_bank_name.getText().toString().trim())) {
                runParameterGlobal.setBankName(et_bank_name.getText().toString().trim());
            }
        }

        if (cb_rs232_enable.isChecked()) {
            if (StringUtils.isNoneBlank(et_rs232_host.getText().toString().trim())) {
                runParameterGlobal.setRs232Host(et_rs232_host.getText().toString().trim());
            } else {
                Toast.makeText(this, "【全局配置】协议主机地址不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (StringUtils.isNoneBlank(et_rs232_host.getText().toString().trim())) {
                runParameterGlobal.setRs232Host(et_rs232_host.getText().toString().trim());
            }
        }

        runParameterGlobal.setRelayEnable(cb_relay_enable.isChecked());
        runParameterGlobal.setRs232Enable(cb_rs232_enable.isChecked());
        runParameter.setRunParameterGlobal(runParameterGlobal);


        // 保存rs232端口
        List<VoRs232Parameter> rs232Parameters = new ArrayList<>();
        VoRs232Parameter rs232Parameter1 = new VoRs232Parameter();
        if (cb_rs232_1_enable.isChecked()) {
            if (StringUtils.isNoneBlank(et_rs232_1_title.getText().toString())) {
                rs232Parameter1.setTitle(et_rs232_1_title.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口一】端口名称不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_1_on_cmd.getText().toString().trim())) {
                rs232Parameter1.setOnCmd(et_rs232_1_on_cmd.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口一】开机指令不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_1_off_cmd.getText().toString().trim())) {
                rs232Parameter1.setOffCmd(et_rs232_1_off_cmd.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口一】关机指令不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_1_port.getText().toString())) {
                rs232Parameter1.setRs232Port(Integer.parseInt(et_rs232_1_port.getText().toString()));
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口一】端口不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (StringUtils.isNoneBlank(et_rs232_1_title.getText().toString())) {
                rs232Parameter1.setTitle(et_rs232_1_title.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_1_on_cmd.getText().toString().trim())) {
                rs232Parameter1.setOnCmd(et_rs232_1_on_cmd.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_1_off_cmd.getText().toString().trim())) {
                rs232Parameter1.setOffCmd(et_rs232_1_off_cmd.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_1_port.getText().toString())) {
                rs232Parameter1.setRs232Port(Integer.parseInt(et_rs232_1_port.getText().toString()));
            }
        }
        rs232Parameter1.setRs232Enable(cb_rs232_1_enable.isChecked());
        rs232Parameters.add(rs232Parameter1);

        VoRs232Parameter rs232Parameter2 = new VoRs232Parameter();
        if (cb_rs232_2_enable.isChecked()) {
            if (StringUtils.isNoneBlank(et_rs232_2_title.getText().toString())) {
                rs232Parameter2.setTitle(et_rs232_2_title.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口一】端口名称不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_2_on_cmd.getText().toString().trim())) {
                rs232Parameter2.setOnCmd(et_rs232_2_on_cmd.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口二】开机指令不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_2_off_cmd.getText().toString().trim())) {
                rs232Parameter2.setOffCmd(et_rs232_2_off_cmd.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口二】关机指令不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_2_port.getText().toString())) {
                rs232Parameter2.setRs232Port(Integer.parseInt(et_rs232_2_port.getText().toString()));
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口二】端口不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (StringUtils.isNoneBlank(et_rs232_2_title.getText().toString())) {
                rs232Parameter2.setTitle(et_rs232_2_title.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_2_on_cmd.getText().toString().trim())) {
                rs232Parameter2.setOnCmd(et_rs232_2_on_cmd.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_2_off_cmd.getText().toString().trim())) {
                rs232Parameter2.setOffCmd(et_rs232_2_off_cmd.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_2_port.getText().toString())) {
                rs232Parameter2.setRs232Port(Integer.parseInt(et_rs232_2_port.getText().toString()));
            }
        }
        rs232Parameter2.setRs232Enable(cb_rs232_2_enable.isChecked());
        rs232Parameters.add(rs232Parameter2);

        VoRs232Parameter rs232Parameter3 = new VoRs232Parameter();
        if (cb_rs232_3_enable.isChecked()) {
            if (StringUtils.isNoneBlank(et_rs232_3_title.getText().toString())) {
                rs232Parameter3.setTitle(et_rs232_3_title.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口一】端口名称不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_3_port.getText().toString())) {
                rs232Parameter3.setRs232Port(Integer.parseInt(et_rs232_3_port.getText().toString()));
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口三】端口不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_3_on_cmd.getText().toString().trim())) {
                rs232Parameter3.setOnCmd(et_rs232_3_on_cmd.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口三】开机指令不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isNoneBlank(et_rs232_3_off_cmd.getText().toString().trim())) {
                rs232Parameter3.setOffCmd(et_rs232_3_off_cmd.getText().toString().trim());
            } else {
                Toast.makeText(this, "【协议主机设置】【协议端口三】关机指令不能不空", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (StringUtils.isNoneBlank(et_rs232_3_title.getText().toString())) {
                rs232Parameter3.setTitle(et_rs232_3_title.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_3_on_cmd.getText().toString().trim())) {
                rs232Parameter3.setOnCmd(et_rs232_3_on_cmd.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_3_off_cmd.getText().toString().trim())) {
                rs232Parameter3.setOffCmd(et_rs232_3_off_cmd.getText().toString().trim());
            }
            if (StringUtils.isNoneBlank(et_rs232_3_port.getText().toString())) {
                rs232Parameter3.setRs232Port(Integer.parseInt(et_rs232_3_port.getText().toString()));
            }
        }
        rs232Parameter3.setRs232Enable(cb_rs232_3_enable.isChecked());
        rs232Parameters.add(rs232Parameter3);

        runParameter.setRs232Parameters(rs232Parameters);

        //保存继电线圈
        List<VoRelayParameter> relayParameters = new ArrayList<>();
        for (int i = 0; i < relayTitles.size(); i++) {
            VoRelayParameter relayParameter = new VoRelayParameter();
            relayParameter.setRelayEnable(relayEnable.get(i).isChecked());
            if (relayEnable.get(i).isChecked()) {
                if (StringUtils.isNoneBlank(relayTitles.get(i).getText().toString().trim())) {
                    relayParameter.setRelayTitle(relayTitles.get(i).getText().toString().trim());
                } else {
                    Toast.makeText(this, "【继电主机设置】【协议端口" + (i + 1) + "】名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isNoneBlank(relayIcon.get(i).getSelectedItem().toString())) {
                    relayParameter.setRelayIcon(relayIcon.get(i).getSelectedItem().toString().trim());
                } else {
                    Toast.makeText(this, "【继电主机设置】【协议端口" + (i + 1) + "】ICON不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                if (StringUtils.isNoneBlank(relayTitles.get(i).getText().toString().trim())) {
                    relayParameter.setRelayTitle(relayTitles.get(i).getText().toString().trim());
                }
                if (StringUtils.isNoneBlank(relayIcon.get(i).getSelectedItem().toString())) {
                    relayParameter.setRelayIcon(relayIcon.get(i).getSelectedItem().toString().trim());
                }
            }

            relayParameters.add(relayParameter);
        }
        runParameter.setRelayParameters(relayParameters);
        mACache.put(VoRunParameter.RUN_PARAMETER, runParameter);
    }
}
