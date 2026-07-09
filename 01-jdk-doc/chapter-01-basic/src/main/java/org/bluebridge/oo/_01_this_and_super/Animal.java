package org.bluebridge.oo._01_this_and_super;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc
 * @date 2025/11/12 10:00
 */
@Slf4j
public abstract class Animal {

    /**
     * 吃东西 - 子类必须重写这个方法
     */
    abstract void eat();

    /**
     * 喝水 - 子类可以不重写这个方法
     */
    public void drink() {
        log.info("动物正在喝水......");
    }

    /**
     * 飞行 - 子类没有权限重写这个方法
     */
    public void sleep() {
        log.info("动物正在睡觉......");
    }
}
