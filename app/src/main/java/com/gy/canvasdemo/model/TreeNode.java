package com.gy.canvasdemo.model;

import android.support.annotation.NonNull;

/**
 * 说明：模型层双链表的单体
 */
public class TreeNode<T extends Comparable<T>> extends Viewable implements Comparable<TreeNode<T>> {

    public T data;//链表结构承载的核心数据

    @Override
    public boolean equals(Object obj) {
        return ((TreeNode) obj).data.equals(data);
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(float x, float y) {
        super(x, y);
    }


    @Override
    public int compareTo(@NonNull TreeNode<T> o) {
        return data.compareTo(o.data);
    }

}
