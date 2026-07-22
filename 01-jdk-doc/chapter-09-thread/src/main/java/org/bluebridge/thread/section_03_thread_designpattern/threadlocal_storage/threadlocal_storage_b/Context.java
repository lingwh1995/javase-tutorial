package org.bluebridge.thread.section_03_thread_designpattern.threadlocal_storage.threadlocal_storage_b;

/**
 * 上下文
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class Context {

    private String name;
    private String idCard;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
