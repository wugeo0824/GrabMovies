package com.xijun.crepe.grabmovies.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by LiXijun on 2016/3/5.
 */
public class movieField extends RelativeLayout {

    private TextView tvLeft, tvRight;
    public movieField(Context context) {
        super(context);
        init();
    }

    public movieField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public movieField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        tvLeft = new TextView(getContext());
        tvRight = new TextView(getContext());

    }


}
