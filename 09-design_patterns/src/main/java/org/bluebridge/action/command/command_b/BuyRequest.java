package org.bluebridge.action.command.command_b;

/**
 * 具体的购买请求
 *
 * @author lingwh
 * @date 2019/8/2 9:00
 */
public class BuyRequest implements Order {

    private Request request;

    public BuyRequest(Request request) {
        this.request = request;
    }

    /**
     * 执行具体的购买请求
     */
    @Override
    public void execute() {
        request.buy();
    }
}
