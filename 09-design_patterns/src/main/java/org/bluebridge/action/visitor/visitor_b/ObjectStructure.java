package org.bluebridge.action.visitor.visitor_b;

import java.util.LinkedList;
import java.util.List;

/**
 * 对象结构
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class ObjectStructure {

    /**
     * 维护了一个集合
     */
    private List<Person> persons = new LinkedList<Person>();

    /**
     * 增加到 list 中
     *
     * @param person
     */
    public void attach(Person person) {
        persons.add(person);
    }

    /**
     * 从 list 中移除
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
