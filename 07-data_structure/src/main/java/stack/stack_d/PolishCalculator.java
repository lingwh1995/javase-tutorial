package stack.stack_d;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 将中缀表达式转为后缀表达式
 * 2*(8+16)/4 ==> 2 8 16 + * 4
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PolishCalculator {

    public static void main(String[] args) {
        // 初始化两个栈，一个数栈一个符号栈
        Transformation numstack = new Transformation(20);
        Transformation operstack = new Transformation(20);
        // 创建中缀表达式
        String middleExpression = "2*(8+16)/4";
        String keepnum = "";
        for (int index = 0; index < middleExpression.length(); index++) {
            String str = middleExpression.substring(index, index + 1);
            while (true) {
                // 这里使用正则表达式来取出数
                if (str.matches("\\d+") || str.equals(".")) { // 匹配到多位数
                    // 处理多位数
                    keepnum += str;
                    // 如果str是最后一位，则直接入栈
                    if (index == middleExpression.length() - 1) {
                        numstack.push(keepnum);
                    } else {
                        // 判断下一个字符是不是数字
                        // 只是往后看一位，index的值不改变
                        if (operstack.isOper(middleExpression.substring(index + 1, index + 2).charAt(0))) {
                            numstack.push(keepnum);
                            // 清空keepnum
                            keepnum = "";
                        }
                    }
                    break;
                } else {
                    // 进行条件判断，符号栈为空，或str为“（”，或优先级大于符号栈栈顶的运算符，则直接压入符号栈中
                    if (operstack.isEmpty()
                            || str.equals("(")
                            || operstack.priority(operstack.peek()) < operstack.priority(str)) {
                        operstack.push(str);
                        break;
                    } else if (str.equals(")")) {
                        // 如果是右括号“)”，则依次弹出符号栈顶的运算符，并压入数栈，直到遇到左括号为止
                        while (!operstack.peek().equals("(")) {
                            String temp = operstack.pop();
                            numstack.push(temp);
                        }
                        // 将左括号丢出
                        operstack.pop();
                        break;
                    } else {
                        String temp = operstack.pop();
                        numstack.push(temp);
                    }
                }
            }
        }
        System.out.println("--------------");
        numstack.list();
        System.out.println("--------------");
        while (!operstack.isEmpty()) {
            String temp = operstack.pop();
            numstack.push(temp);
        }
        // 将数据存储到数组中
        List<String> list = new ArrayList<>();

        List<String> reverselist = new ArrayList<>();
        while (!numstack.isEmpty()) {
            list.add(numstack.pop());
        }
        // 将数组进行颠倒
        for (int i = list.size() - 1; i >= 0; i--) {
            reverselist.add(list.get(i));
        }
        System.out.println(reverselist);
        double calculate = calculate(reverselist);
        System.out.println("中缀表达式" + middleExpression + "运算的结果是：" + calculate);
    }

    // 完成对逆波兰表达式的计算
    public static double calculate(List<String> list) {
        // 创建一个栈，一个栈即可
        Stack<String> stack = new Stack();
        // 遍历list
        for (String str : list) {
            // 这里使用正则表达式来取出数（多位数或小数皆可）
            if (str.matches("\\d+") || str.matches("\\d+\\.\\d+")) {
                stack.push(str); // 加入栈中
            } else {
                // pop出两个数并运算，运算结束后再入栈
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                double result = 0;
                switch (str) {
                    case "+":
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                // 将result转为String，入栈
                stack.push(result + "");
            }
        }
        // 最后留在stack中的就是结果
        return Double.parseDouble(stack.pop());
    }
}

class Transformation {
    private int maxSize; // 栈的大小
    private String[] stack; // 数组，数组模拟栈，数据放在数组中
    private int top = -1; // top表示栈底，初始化为-1（表示没有数据）

    public Transformation(int maxSize) {
        this.maxSize = maxSize;
        stack = new String[maxSize]; // 创建大小为maxSize的数组
    }

    // 增加一个方法，可以返回当前栈顶的值，但不是出栈
    public String peek() {
        return stack[top];
    }

    // 返回运算符的优先级，优先级由程序员所定，优先级使用数字表示，数字越大，优先级越高
    public int priority(String oper) {
        if (oper.equals("*") || oper.equals("/")) {
            return 1;
        } else if (oper.equals("+") || oper.equals("-")) {
            return 0;
        } else {
            return -1; // 假定当前的计算式中只有加减乘除
        }
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int result = 0; // 存放结果
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                // num2后取，num1先取
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '*' || val == '-' || val == '/' || val == '(' || val == ')';
    }

    // 判断栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈--push
    public void push(String value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈--pop
    public String pop() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空");
        }
        String value = stack[top];
        top--;
        return value;
    }

    // 遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}
