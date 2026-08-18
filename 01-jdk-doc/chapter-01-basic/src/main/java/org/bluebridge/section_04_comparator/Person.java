package org.bluebridge.section_04_comparator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Person 实体类，实现 Comparable 接口
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
@AllArgsConstructor
@Data
public class Person implements Comparable<Person> {

    private String name;
    private int age;

    /**
     * 比较两个 Person 是否相等：若它们的 name 和 age 都相等，则认为它们相等
     */
    boolean equals(Person person) {
        if (this.age == person.age && this.name == person.name) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}
