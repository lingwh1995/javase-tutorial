package action.command.command_b;

/**
 * 具体的销售请求
 *
 * @author lingwh
 * @date 2019/8/2 9:03
 */
public class SellRequest implements Order {

    private Request request;

    public SellRequest(Request request) {
        this.request = request;
    }

    /**
     * 执行具体的销售请求
     */
    @Override
    public void execute() {
        request.sell();
    }
}
