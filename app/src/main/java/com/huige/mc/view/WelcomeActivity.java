package com.huige.mc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;

import com.huige.mc.MainActivity;
import com.huige.mc.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

    private Handler handler;
    private Runnable runnable2Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    private void init() {
        handler = new Handler();

        runnable2Main = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        };
        handler.postDelayed(runnable2Main, 3000);
    }

    @Event(R.id.tv_bg)
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_bg:
                onDisplaySettingButton();
                break;
        }
    }


    long[] mHints = new long[8];

    public void onDisplaySettingButton() {
        System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);//把从第二位至最后一位之间的数字复制到第一位至倒数第一位
        mHints[mHints.length - 1] = SystemClock.uptimeMillis();//从开机到现在的时间毫秒数
        if (SystemClock.uptimeMillis() - mHints[0] <= 3000) {//连续点击之间间隔小于一秒，有效
            handler.removeCallbacks(runnable2Main);
            startActivity(new Intent(this, SettingActivity.class));
            finish();
        }
    }
}
