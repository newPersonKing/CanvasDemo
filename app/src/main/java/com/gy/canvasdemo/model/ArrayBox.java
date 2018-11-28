package com.gy.canvasdemo.model;

/**
 *  说明：模型层数组的单体
 */
public class ArrayBox<T> extends Viewable {

    public int index;//数组单体索引
    public T data;//数据结构承载的核心数据

    @Override
    public boolean equals(Object obj) {
        return ((ArrayBox) obj).data == data;
    }

    public ArrayBox(T data) {
        this.data = data;
    }

    public ArrayBox(float x, float y) {
        super(x, y);
    }
}
