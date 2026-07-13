package action.strategy.strategy_a;

import action.command.command_k.OperationApi;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/8/27 11:07
 */
public class Client {

    public static void main(String[] args) {
        Addtion addtion = new Addtion();
        Subtraction subtraction = new Subtraction();
        // OperationApi calculator = new OperationApi(addtion);
        Calculator calculator = new Calculator(subtraction);
        Integer calculate = calculator.calculate(1, 5);
        System.out.println(calculate);

        /**
         * 下面代码不是策略模式，只是当使用接口作为参数的时候,匿名内部类的实现并重写接口中的方法
         */
        /*
        OperationApi calculator = new OperationApi(new ICalucatorStrategy() {
            @Override
            public Integer calucate(int a, int b) {
                return a + b;
            }
        });
        Integer calculate = calculator.calculate(1, 8);
        System.out.println(calculate);
        */
    }
}
