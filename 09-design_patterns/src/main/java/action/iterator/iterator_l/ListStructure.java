package action.iterator.iterator_l;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lingwh
 * @desc 列表结构
 * @date 2019/9/23 10:41
 */
public class ListStructure implements Structure {
    private List<Person> personList;

    public ListStructure() {
        this.personList = new ArrayList<>();
    }

    @Override
    public void addElement(Person person) {
        personList.add(person);
    }

    @Override
    public Iterator iterator() {
        return new ListStructureIterator(this);
    }

    @Override
    public Object getElement(int index) {
        return personList.get(index);
    }

    @Override
    public int size() {
        return personList.size();
    }
}
