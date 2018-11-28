package com.gy.canvasdemo.ds.itf;

/**
 *  * 说明：线性表结构接口
 */
public interface IChart<T>  {
    /**
     * 定点添加
     *
     * @param index 索引
     * @param el    数据元素
     */
    void add(int index,T el);

    /**
     * 添加尾
     *
     * @param el 数据元素
     */
    void add(T el);

    /**
     * 定位删除
     *
     * @param index 索引
     * @return 删除的元素
     */
    T remove(int index);

    /**
     * 删除尾位
     *
     * @return 删除的元素
     */
    T remove();

    /**
     * 删除指定元素的第一次出现时
     *
     * @param el 数据元素
     * @return 元素位置
     */
    int removeEl(T el);

    /**
     * 删除所有指定元素
     *
     * @param el 数据元素
     */
    boolean removeEls(T el);

    /**
     * 清空集合
     */
    void clear();

    /**
     * 设置某位置的元素新值
     *
     * @param index 索引
     * @param el    新值
     * @return 旧值
     */
    T set(int index,T el);

    /**
     * 根据指定位置获取元素
     *
     * @param index 索引
     * @return 数据元素
     */
    T get(int index);

    /**
     * 根据指定元素获取匹配索引
     *
     * @param el 数据元素
     * @return 索引集
     */
    int[] getIndex(T el);

    /**
     * 集合是否包含某元素
     *
     * @param el 数据元素
     * @return 是否包含
     */
    public  boolean contains(T el);

    /**
     * 连接两个集合
     *
     * @param iChart 插入集合
     * @return 合并后的集合
     */
    public IChart<T> contact(IChart<T> iChart);

    /**
     * 定点连接两个集合
     *
     * @param index  索引
     * @param iChart 插入集合
     * @return 合并后的集合
     */
    IChart<T> contact(int index,IChart<T> iChart);

    /**
     * 是否为空
     *
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 返回集合大小
     *
     * @return 大小
     */
    int size();

    /**
     * 获取数组容量
     * @return 数组容量
     */
    int capacity();
}
