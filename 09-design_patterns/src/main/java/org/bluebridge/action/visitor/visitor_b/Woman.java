package org.bluebridge.action.visitor.visitor_b;

/**
 * 女人
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class Woman extends Person {

    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
