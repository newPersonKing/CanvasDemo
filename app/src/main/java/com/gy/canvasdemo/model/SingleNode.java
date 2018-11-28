package com.gy.canvasdemo.model;

/**
 * 说明：模型层单链表的单体
 */
public class SingleNode<T> extends Viewable {

    public int index;//链表单体序号
    public T data;//链表结构承载的核心数据

    @Override
    public boolean equals(Object obj) {
        return ((SingleNode) obj).data == data;
    }

    public SingleNode(T data) {
        this.data = data;
    }

    public SingleNode(float x, float y) {
        super(x, y);
    }
}
