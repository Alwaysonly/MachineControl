package com.huige.mcapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.huige.mcapp.utils.ACache;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-6-22 16:57
 */
public class BaseActivity extends AutoLayoutActivity {
    protected ACache mACache;
    protected Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        mACache = ACache.get(this);
        gson = new Gson();
    }
}
