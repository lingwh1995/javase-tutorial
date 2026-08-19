package org.bluebridge.section_05_bitwise.unit_04_struct;

/**
 * 使用 record 模拟 C 语言结构体(Struct), 相比普通类, 代码更简洁, 且天然不可变
 *
 * Java 16 起支持 record, 天生适合模拟 C 结构体:
 *   - 不可变(字段 final, 无 setter)
 *   - 自动生成构造方法、getter、equals、hashCode、toString
 *   - 无对象状态修改，更接近 C 结构体值语义
 *   - 访问属性时使用 name() 而非 getName()
 *
 * // Record
 * public record Person(String name, int age) {}
 *
 * // 等价普通class
 * public final class Person {
 *     private final String name;
 *     private final int age;
 *     // 规范构造器
 *     public Person(String name, int age){
 *         this.name = name;
 *         this.age = age;
 *     }
 *     // 访问器方法：不是getName()，是 name()
 *     public String name(){return name;}
 *     public int age(){return age;}
 *     // 自动生成 equals、hashCode、toString，基于全部组件
 *     ...
 * }
 *
 * @author lingwh
 * @date 2026/08/19 13:42
 */
public record RecordStruct(int id, String name, int age, float score) {

}
