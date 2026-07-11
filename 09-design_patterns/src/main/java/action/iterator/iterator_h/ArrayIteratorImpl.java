package action.iterator.iterator_h;

/**
 * @author lingwh
 * @desc 用来实现访问数组的双向迭代接口
 * @date 2019/8/20 13:17
 */
public class ArrayIteratorImpl implements Iterator {

    private SalaryManager aggregate = null;

    private int index = -1;

    public ArrayIteratorImpl(SalaryManager aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if (index < this.aggregate.size()) {
            index = index + 1;
        }
    }

    @Override
    public boolean isDone() {
        if (index == this.aggregate.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Object currentItem() {
        return this.aggregate.get(index);
    }

    @Override
    public boolean isFirst() {
        if (index == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void previous() {
        if (index > 0) {
            index = index - 1;
        }
    }
}
