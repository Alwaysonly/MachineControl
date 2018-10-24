package com.huige.mcapp;

import android.app.Application;
import android.graphics.Typeface;

import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-6-22 16:56
 */
public class McApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
        x.Ext.init(this); // 这一步之后, 我们就可以在任何地方使用x.app()来获取Application的实例了.
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
