package com.gy.canvasdemo.view.other;

/**
 * 说明：控操作接口
 */
public interface OnCtrlClickListener<T> {
    /**
     * 添加时回调
     * @param view
     */
    void onAdd(T view);
    /**
     * 定点添加时回调
     * @param view
     */
    void onAddByIndex(T view);
    /**
     * 移除时回调
     * @param view
     */
    void onRemove(T view);
    /**
     * 定点移除时回调
     * @param view
     */
    void onRemoveByIndex(T view);
    /**
     * 设置时回调
     * @param view
     */
    void onSet(T view);
    /**
     * 查询时回调
     * @param view
     */
    void onFind(T view);
    /**
     * 定值查询时回调
     * @param view
     */
    void onFindByData(T view);
    /**
     * 清空时回调
     * @param view
     */
    void onClear(T view);

}
