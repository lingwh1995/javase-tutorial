package org.bluebridge.section_06_producer_consumer.case_01;

/**
 * 这里使用final修饰，将这个类设置成不可变类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
final class Message {

    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", value=" + value + '}';
    }
}
