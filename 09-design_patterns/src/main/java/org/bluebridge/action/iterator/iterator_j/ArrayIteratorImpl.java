package org.bluebridge.action.iterator.iterator_j;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用来实现随机翻页访问聚合元素的迭代接口
 *
 * @author lingwh
 * @date 2019/8/20 13:33
 */
public class ArrayIteratorImpl implements AggregationIterator {

    /**
     * 用来存放被迭代的数组
     */
    private PayModel[] pms = null;

    /**
     * 用来记录当前迭代到的位置索引
     */
    private int index = 0;

    public ArrayIteratorImpl(SalaryManager aggregate) {
        this.pms = aggregate.getPays();
    }

    @Override
    public boolean hasNext() {
        // 判断是否还有下一个元素
        if (pms != null && index <= (pms.length - 1)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrevious() {
        if (pms != null && index > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Collection getPage(int pageNum, int pageShow) {
        Collection col = new ArrayList();
        // 需要在这里先计算需要获取的数据的开始条数和结束条数
        int start = (pageNum - 1) * pageShow;
        int end = start + pageShow - 1;
        // 控制 start 的边界，最小是 0
        if (start < 0) {
            start = 0;
        }

        // 控制 end 的边界，最大是数组的最大索引
        if (end > this.pms.length - 1) {
            end = this.pms.length - 1;
        }

        // 每次取值都是从头开始循环，所以设置 index 为 0
        index = 0;
        while (hasNext() && index <= end) {
            if (index >= start) {
                col.add(pms[index]);
            }
            // 把已访问索引加 1
            index++;
        }
        return col;
    }
}
