package action.command.command_b;

/**
 * @author lingwh
 * @desc 请求类
 * @date 2019/8/2 8:59
 */
public class Request {

    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Request [ Name: " + name + ",Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Request [ Name: " + name + ",Quantity: " + quantity + " ] sold");
    }
}
