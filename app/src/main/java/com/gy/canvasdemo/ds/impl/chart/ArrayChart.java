package com.gy.canvasdemo.ds.impl.chart;

import com.gy.canvasdemo.ds.itf.IChart;

/*对数组的操作*/
public class ArrayChart<T> implements IChart<T> {

    private int size;//表中数据的个数
    private T[] data;//数据核心承载体

    private static final int DEFAULT_CAPACITY = 10;//默认数组容量
    private static final float GROW_RATE = 1.5f;//扩容增长率
    public ArrayChart(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayChart(int capacity){
        data = (T[]) new Object[capacity];//新创建[数组表]时初始化数组
    }

    @Override
    public void add(int index, T el) {
        if (size==data.length){//篮子装不下了---
            grow((int) (GROW_RATE*data.length));
        }

        if(index<0||index>size){
            throw new IllegalArgumentException("Add failed! Make sure index < 0 || index > size");
        }
        //从最后一个元素开始,到定点位置元素,元素都后移一位  参数介绍 data 数据源 index 开始复制的索引  data 目的数组 index+1 目标数组 开始索引 size-inde 复制长度
        //可简写：System.arraycopy(data, index, data, index + 1, size - index);
        for (int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index] = el;
        size++;
    }

    /**
     * 扩容/缩容
     *
     * @param newCapacity 新容量
     */
    private void grow(int newCapacity){
        T[] newData = (T[]) new Object[newCapacity];//创建个大篮子
        for (int i=0;i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public void add(T el) {
        add(size,el);
    }

    @Override
    public T remove(int index) {
        if (index<0 || index>size){
            throw new IllegalArgumentException("Remove failed! Make sure index < 0 || index > size");
        }

        T temp = get(index);
        //从删除元素索引的下一位开始到结尾，依次左移
        // 可简写: System.arraycopy(data, index + 1, data, index + 1 - 1, size - index + 1);
        for (int i=index+1;i<size;i++){
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;

        //缩容----此处限定是为了避免反复出现扩容缩容
        if (size == data.length / 4 && data.length / 2 != 0 && data.length > 5) {
            grow(data.length / 2);
        }

        return temp;
    }

    @Override
    public T remove() {

        return  remove(size-1);
    }

    @Override
    public int removeEl(T el) {
        int[] indexes = getIndex(el);//查找元素的索引集合，删除首个
        int index = -1;
        if(indexes.length>0){
            index = indexes[0];
            remove(index);
        }
        return index;
    }

    @Override
    public boolean removeEls(T el) {
        int[] indexes = getIndex(el);

        if (indexes.length>0){
            for (int i=0;i<indexes.length;i++){
                remove(indexes[i]-i);// 注意-i 每一处一次 相应的后面的未移除的元素 索引减一
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        grow(DEFAULT_CAPACITY);
    }

    @Override
    public T set(int index, T el) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed! Make sure index < 0 || index > size");
        }

        T oldEl = get(index);
        data[index] = el;
        return oldEl;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed! Make sure index < 0 || index > size");
        }
        return data[index];//查询数组的对应索引处
    }

    @Override
    public int[] getIndex(T el) {
        int[] tempArray = new int[size];//临时数组
        int count = 0;//重复个数
        for (int i= 0;i<size;i++){
            if (data[i].equals(el)){
                tempArray[count]=i;
                count++;
            }
        }

        //将临时数组压缩---排除空位
        int[] indexArray = new int[count];
        for (int i = 0; i < count; i++) {
            indexArray[i] = tempArray[i];
        }
        return indexArray;
    }

    @Override
    public boolean contains(T el) {
        return getIndex(el).length!=0;
    }

    @Override
    public IChart<T> contact(IChart<T> iChart) {

        return  contact(size-1,iChart);
    }

    @Override
    public IChart<T> contact(int index, IChart<T> iChart) {
        if (!(iChart instanceof ArrayChart)){
            return null;
        }
        //从index处遍历本数组，将待插入数据一个一个插入
        for (int i = index; i < index + iChart.size(); i++) {
            add(i + 1, iChart.get(i - index));
        }
        return this;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        if (data != null) {
            return data.length;
        }
        return 0;
    }
}
