package com.shuniuyun.refresh.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 转换手机分辨率的工具类
 *
 * @author wyb
 */
public class DisplayUtil {
    private static final String TAG = "DisplayUtil";

    //dip转px
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    //px转dip
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //获取屏幕长宽比
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }

    //获取屏幕宽度和高度，单位为px
    private static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    //获取本地的dp值
    public static int getXmlDef(Context context, int id) {
        TypedValue value = new TypedValue();
        context.getResources().getValue(id, value, true);
        return (int) TypedValue.complexToFloat(value.data);
    }

}