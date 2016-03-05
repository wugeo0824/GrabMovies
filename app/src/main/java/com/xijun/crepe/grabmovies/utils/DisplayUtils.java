package com.xijun.crepe.grabmovies.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.xijun.crepe.grabmovies.R;

/**
 * Created by Xijun on 5/3/16.
 */
public class DisplayUtils {

    private static int displayWidth = -1;
    private static int posterWidth = -1;
    private static int posterHeight =-1;

    public static int getDisplayWidth(Context context) {
        if (displayWidth < 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();

            Point size = new Point();
            display.getSize(size);
            displayWidth = size.x;
        }

        return displayWidth;
    }

    public static int getPosterWidth(Context context) {
        if (posterWidth <0){
            int fullScreen = getDisplayWidth(context);
            int padding = context.getResources().getDimensionPixelOffset(R.dimen.grid_padding);
            // left edge + right edge + two spacing between columns = 4
            posterWidth = (fullScreen - padding*4) / 3;
        }
        return posterWidth;
    }

    public static int getPosterHeight(Context context) {
        if (posterHeight <0){
            int width = getPosterWidth(context);
            // make it easy, assume all poster height is 1.5 times of its width
            posterHeight = width * 3 / 2;
        }
        return posterHeight;
    }


}
