package action.iterator.iterator_l;

import java.util.Iterator;

/**
 * 列表结构迭代器
 *
 * @author lingwh
 * @date 2019/9/23 10:40
 */
public class ListStructureIterator implements Iterator {

    private ListStructure listStructure;
    private int index;

    public ListStructureIterator(ListStructure listStructure) {
        this.listStructure = listStructure;
    }

    @Override
    public boolean hasNext() {
        boolean flag = false;
        if (index < listStructure.size()) {
            index = index + 1;
            flag = true;
        }
        return flag;
    }

    @Override
    public Object next() {
        return listStructure.getElement(index - 1);
    }

    @Override
    public void remove() {}
}
