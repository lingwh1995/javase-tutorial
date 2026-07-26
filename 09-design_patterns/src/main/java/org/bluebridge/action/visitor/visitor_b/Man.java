package org.bluebridge.action.visitor.visitor_b;

/**
 * 男人
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class Man extends Person {

    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
