package com.huige.mcapp.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class STKAITITextView extends android.support.v7.widget.AppCompatTextView {

    public STKAITITextView(Context context) {
        super(context);
        //设置字体
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/STKAITI.TTF"));
    }

    public STKAITITextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置字体
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/STKAITI.TTF"));
    }

    public STKAITITextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置字体
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/STKAITI.TTF"));
    }
}
