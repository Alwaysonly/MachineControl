package com.huige.mc.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.huige.mc.utils.ACache;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-6-22 16:57
 */
public class BaseActivity extends AppCompatActivity {
    protected ACache mACache;
    protected Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
        }

        mACache = ACache.get(this);
        gson = new Gson();
    }
}
