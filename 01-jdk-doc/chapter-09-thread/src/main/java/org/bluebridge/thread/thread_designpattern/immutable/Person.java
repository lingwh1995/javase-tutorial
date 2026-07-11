package org.bluebridge.thread.thread_designpattern.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lingwh
 * @desc 不对外提供setter()方法, 注意:此类中getList()方法对局部变量list的处理
 * @date 2026/7/9 00:00
 */
public final class Person {
    private final String name;
    private final String address;
    private final List<String> list;

    public Person(final String name, final String address) {
        this.name = name;
        this.address = address;
        list = new ArrayList<>();
        list.add("ufe");
        list.add("peihua");
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", address='" + address + '\'' + '}';
    }
}
