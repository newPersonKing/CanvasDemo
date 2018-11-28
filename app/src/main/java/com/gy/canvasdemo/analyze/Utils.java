package com.gy.canvasdemo.analyze;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {

    /**
     * 获得屏幕高度
     *
     * @param ctx 上下文
     * @param winSize 屏幕尺寸
     */
    public static void loadWinSize(Context ctx, Point winSize){
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (wm!=null){
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        }
        winSize.x = displayMetrics.widthPixels;
        winSize.y = displayMetrics.heightPixels;
    }

}
