package com.xijun.crepe.grabmovies.ui.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xijun.crepe.grabmovies.R;

/**
 * Created by LiXijun on 2016/3/5.
 */
public class MovieField extends RelativeLayout {

    private TextView tvLeft, tvRight;
    public MovieField(Context context, String left, String right) {
        super(context);
        init();
        this.setLeft(left);
        this.setRight(right);
    }

    private void init() {

        tvLeft = new TextView(getContext());
        tvRight = new TextView(getContext());

        tvLeft.setTextColor(getResources().getColor(R.color.secondary_text));
        tvRight.setTextColor(getResources().getColor(R.color.white));

        tvLeft.setTypeface(null, Typeface.BOLD);

        tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size_movie_field));
        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size_movie_field));

        RelativeLayout.LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        tvLeft.setLayoutParams(leftParams);


        RelativeLayout.LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        tvRight.setLayoutParams(rightParams);

        this.addView(tvLeft);
        this.addView(tvRight);
    }

    public void setLeft(String left){
        tvLeft.setText(left);
    }

    public void setRight(String right){
        tvRight.setText(right);
    }


}
