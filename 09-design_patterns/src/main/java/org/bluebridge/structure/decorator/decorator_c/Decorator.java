package org.bluebridge.structure.decorator.decorator_c;

/**
 * 装饰器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class Decorator implements Component {

    protected Component component;

    public abstract void operation();
}
