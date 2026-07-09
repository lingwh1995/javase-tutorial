package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author lingwh
 * @desc 标准输入流测试
 * @date 2019/3/12 16:58
 */
@Slf4j
public class ScannerTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        log.info("请输入：");

        //scanner.next() 输入 你 好，输出 你，不能获取用户输入的空格
//      if(scanner.hasNext()){
//      	String input = scanner.next();
//          log.info("input：{}", input);
//      }

        //scanner.next() 输入 你 好，输出 你好，可以获取用户输入的空格
//      if(scanner.hasNextLine()){
//          String input = scanner.nextLine();
//          log.info("input：{}", input);
//      }

        //scanner.hasNextXXX()的作用：外层不调用scanner.hasNextInt(),内层直接调用scanner.nextInt();
        //如果输入了浮点类型数据，则会直接抛出java.util.InputMismatchException异常
        if(scanner.hasNextInt()){
            int input1 = scanner.nextInt();
            int input2 = scanner.nextInt();
            log.info("input1：{}", input1);
            log.info("input2：{}", input2);
        }
        scanner.close();
    }
}


