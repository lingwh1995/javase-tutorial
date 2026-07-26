package org.bluebridge.thread.section_03_thread_designpattern.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不对外提供 setter() 方法，注意：此类中 getList() 方法对局部变量 list 的处理
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
