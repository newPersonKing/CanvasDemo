package com.gy.canvasdemo.ds.impl.task;


import com.gy.canvasdemo.ds.impl.chart.SingleLinkedChart;
import com.gy.canvasdemo.ds.itf.IStack;

/**
 * 说明：栈的链表式集合实现
 */
public class SingleLinkedStack<E> implements IStack<E> {

    private SingleLinkedChart<E> mSingleLinkedChart;

    public SingleLinkedStack() {
        mSingleLinkedChart = new SingleLinkedChart<>();
    }


    @Override
    public int size() {
        return mSingleLinkedChart.size();
    }

    @Override
    public int capacity() {
        return mSingleLinkedChart.size();
    }

    @Override
    public boolean isEmpty() {
        return mSingleLinkedChart.isEmpty();
    }

    @Override
    public void push(E el) {
        mSingleLinkedChart.add(el);
    }

    @Override
    public E pop() {
        return mSingleLinkedChart.remove();
    }

    @Override
    public E peek() {
        return mSingleLinkedChart.get(0);
    }
}
