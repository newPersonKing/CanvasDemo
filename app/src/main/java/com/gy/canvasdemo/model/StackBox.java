package com.gy.canvasdemo.model;

/**
 * 说明：模型层数组的单体
 */
public class StackBox<T> extends Viewable {

    public int index;//数组单体索引
    public T data;//数据结构承载的核心数据

    @Override
    public boolean equals(Object obj) {
        return ((StackBox) obj).data == data;
    }

    public StackBox(T data) {
        this.data = data;
    }

    public StackBox(float x, float y) {
        super(x, y);
    }
}
