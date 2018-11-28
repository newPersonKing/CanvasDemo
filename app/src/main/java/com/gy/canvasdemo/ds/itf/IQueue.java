package com.gy.canvasdemo.ds.itf;

/**
 * 说明：队列接口
 */
public interface IQueue<T> {
    /**
     * 入队
     * @param el 元素
     */
    void enqueue(T el);

    /**
     * 出队
     * @return 元素
     */
    T dequeue();

    /**
     * 获取队首元素
     * @return 队首元素
     */
    T front();

    /**
     * 获取队列元素个数
     * @return 元素个数
     */
    int size();

    /**
     * 是否为空
     * @return 是否为空
     */
    boolean isEmpty();
}