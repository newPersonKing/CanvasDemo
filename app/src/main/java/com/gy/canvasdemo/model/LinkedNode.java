package com.gy.canvasdemo.model;

/**
 * 说明：模型层双链表的单体
 */
public class LinkedNode<T> extends Viewable {

    public int index;//链表单体序号
    public T data;//链表结构承载的核心数据

    @Override
    public boolean equals(Object obj) {
        return ((LinkedNode) obj).data.equals(data);
    }

    public LinkedNode(T data) {
        this.data = data;
    }

    public LinkedNode(float x, float y) {
        super(x, y);
    }
}
