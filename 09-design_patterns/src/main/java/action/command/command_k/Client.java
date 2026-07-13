package action.command.command_k;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/9/10 16:33
 */
public class Client {

    public static void main(String[] args) {
        // 创建一个计算器对象
        Calculator calculator = new Calculator();
        // 创建算法对象
        ConcreteOperationApi concreteOperationApi = new ConcreteOperationApi();
        // 加法命令
        Command addCommand = new AddCommand(concreteOperationApi, 1);
        // 减法命令
        Command substractCommand = new SubstractCommand(concreteOperationApi, 2);

        calculator.setAddCommand(addCommand);
        calculator.setSubstractCommand(substractCommand);
        // 执行一次加法运算:0+1=1
        calculator.addButtonWasPressed();
        System.out.println(concreteOperationApi.getResult());
        // 执行一次减法运算:1-2=-1
        calculator.subButtonWasPressed();
        System.out.println(concreteOperationApi.getResult());
        // 执行一次撤销:-1+2=1
        calculator.undoButtonWasPressed();
        System.out.println(concreteOperationApi.getResult());
        // 再执行一次撤销:1-1=0
        calculator.undoButtonWasPressed();
        System.out.println(concreteOperationApi.getResult());
    }
}
