package create.simplefactory.simplefactory_c;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        Operation add = OperationFactory.createOperation("+");
        System.out.println(add.getReslt(1.0, 3.5));
    }
}
