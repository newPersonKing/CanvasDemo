package com.gy.canvasdemo.view.other;


import com.gy.canvasdemo.view.BinarySearchView;

/**
 * 说明：控操作接口
 */
public interface OnBSTClickListener {
    /**
     * 添加时回调
     * @param view
     */
    void onAdd(BinarySearchView view);
    /**
     * 删除元素
     * @param view
     */
    void onRemove(BinarySearchView view);
    /**
     * 删除最大值
     * @param view
     */
    void onRemoveMax(BinarySearchView view);
    /**
     * 删除最小值
     * @param view
     */
    void onRemoveMin(BinarySearchView view);
    /**
     * 获取最大值
     * @param view
     */
    void onGetMax(BinarySearchView view);
    /**
     * 获取最小值
     * @param view
     */
    void onGetMin(BinarySearchView view);
    /**
     * 定值查询时回调
     * @param view
     */
    void onOrder(BinarySearchView view);
    /**
     * 清空时回调
     * @param view
     */
    void onContains(BinarySearchView view);
}
