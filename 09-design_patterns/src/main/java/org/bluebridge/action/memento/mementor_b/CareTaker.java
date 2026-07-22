package org.bluebridge.action.memento.mementor_b;

import java.util.ArrayList;
import java.util.List;

/**
 * 守护者对象:负责保存多个备忘录对象，使用集合管理吗，提高效率
 *
 * @author lingwh
 * @date 2026/7/9 16:35
 */
public class CareTaker {

    // 保存一次状态
    // private Memento Memento;
    // 保存多次状态:使用List
    private List<Memento> mementoList = new ArrayList<Memento>();

    // 保存多个角色的多次状态:使用Map
    // private Map<String,ArrayList<Memento>> mementoMap= new HashMap<>();
    /**
     * 添加备忘录状态对象
     *
     * @param memento
     */
    public void add(Memento memento) {
        mementoList.add(memento);
    }

    /**
     * 获取备忘录状态对象
     *
     * @param index
     * @return
     */
    public Memento getMemento(int index) {
        return mementoList.get(index);
    }
}
