package com.gy.canvasdemo.analyze.gold12;

import android.graphics.Point;
import android.graphics.PointF;

public class JudgeMan {
    /**
     * 区域限定----判断出是否在某点的半径为r圆范围内
     * @param srcX 目标点X
     * @param srcY 目标点Y
     * @param dstX 主动点X
     * @param dstY 主动点Y
     * @param r    半径区域
     * @return 是否在区域内
     */
    public static boolean judgeCircleArea(float srcX, float srcY, float dstX, float dstY, float r) {
        return disPos2d(srcX, srcY, dstX, dstY) <= r;
    }

    /**
     * 区域限定----判断出是否在某点的半径为r圆范围内
     * @param src 目标点
     * @param dst 主动点
     * @param r   半径
     */
    public static boolean judgeCircleArea(Point src, Point dst, float r) {
        return judgeCircleArea(src.x, src.y, dst.x, dst.y,r);
    }

    /**
     * 区域限定----判断出是否在某点的半径为r圆范围内
     * @param src 目标点
     * @param dst 主动点
     * @param r 半径区域
     * @return 是否在区域内
     */
    public static boolean judgeCircleArea(PointF src, PointF dst, float r) {
        return disPos2d(src.x, src.y, dst.x, dst.y) <= r;
    }

    /**
     *两点间距离函数
     * @param srcX 目标点X
     * @param srcY 目标点Y
     * @param dstX 主动点X
     * @param dstY 主动点Y
     * @return 两点间距离函数
     */
    private static float disPos2d(float srcX, float srcY, float dstX, float dstY) {
        return (float) Math.sqrt((srcX - dstX) * (srcX - dstX) + (srcY - dstY) * (srcY - dstY));
    }
}
