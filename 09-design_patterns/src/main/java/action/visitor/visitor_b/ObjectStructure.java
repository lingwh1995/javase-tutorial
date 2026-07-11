package action.visitor.visitor_b;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lingwh
 * @desc 对象结构
 * @date 2026/7/9 00:00
 */
public class ObjectStructure {
    /**
     * 维护了一个集合
     */
    private List<Person> persons = new LinkedList<Person>();

    /**
     * 增加到list中
     *
     * @param person
     */
    public void attach(Person person) {
        persons.add(person);
    }

    /**
     * 从list中移除
     *
     * @param person
     */
    public void detach(Person person) {
        persons.remove(person);
    }

    /**
     * 显示测评情况
     *
     * @param action
     */
    public void disPlay(Action action) {
        for (Person person : persons) {
            person.accept(action);
        }
    }
}
