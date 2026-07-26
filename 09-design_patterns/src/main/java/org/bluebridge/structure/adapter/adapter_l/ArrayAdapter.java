package org.bluebridge.structure.adapter.adapter_l;

import java.util.ArrayList;

/**
 * 数组适配器
 *
 * @author lingwh
 * @date 2026/7/22 10:32
 */
public class ArrayAdapter extends ArrayList {

    private int[] result;

    public ArrayAdapter(int[] result) {
        this.result = result;
    }

    @Override
    public Object get(int index) {
        return result[index];
    }

    /**
     * 注意：这个 size() 方法一定要重写，不然遍历的时候调用 ArrayAdapter.size() 返回的值是 0，无法进行遍历
     */
    @Override
    public int size() {
        return result.length;
    }
}
