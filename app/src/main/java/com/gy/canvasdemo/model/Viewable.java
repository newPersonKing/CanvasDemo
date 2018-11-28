package com.gy.canvasdemo.model;

/**
 * 说明：可显示出来的基本条件
 */
public class Viewable {

    public float x;
    public float y;
    public int color = 0xff43A3FA;//单体的颜色

    public float vX;//单体的水平速度
    public float vY;//单体的数值速度

    public Viewable(){

    }

    public Viewable(float x, float y){
        this.x = x;
        this.y = y;
    }
}
