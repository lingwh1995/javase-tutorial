package org.bluebridge.java8.chapter_01_interface.chapter_04_jdk_functional_interface;

import org.junit.Test;

import java.util.function.*;

/**
 * Java8四大基本的函数式接口
 *
 * 1. 消费型接口	Consumer<T>			void accept(T t)
 *    功能：接收一个参数，不返回结果（消费数据）
 * 	  抽象方法：void accept(T t)
 *    常用场景：打印输出、保存数据等副作用操作
 * 2. 供给型接口	Supplier<T>			T get()
 *    功能：不接收参数，返回一个结果（生产数据）
 *    抽象方法：T get()
 *    常用场景：工厂方法、延迟初始化、生成随机数
 * 3. 函数型接口	Function<T,R>		R apply(T t)
 * 	  功能：接收一个参数，返回一个结果
 *    抽象方法：R apply(T t)
 *    常用场景：数据转换、映射操作
 * 4. 断定型接口	Predicate<T>		boolean test(T t)
 *    功能：接收一个参数，返回布尔值
 *    抽象方法：boolean test(T t)
 *    常用场景：条件判断、过滤操作
 *
 * @author lingwh
 * @date 2025/12/2 16:50
 */
public class JDKFourFunctionInterfaceTest {

    /**
     * 测试Consumer函数式接口
     */
    @Test
    public void testConsumer() {
        // 匿名内部类方式使用Consumer接口
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
        };
        consumer.accept(10);
        System.out.println("--------------------------------------");

        // lambda方式使用Consumer接口
        consumer = t -> System.out.println(t);
        consumer.accept(20);
        System.out.println("--------------------------------------");

        // 方法引用方式使用Consumer接口
        consumer = System.out :: println;
        consumer.accept(30);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试Supplier函数式接口
     */
    @Test
    public void testSupplier() {
        // 匿名内部类方式使用Supplier接口
        Supplier<String> supplier = new Supplier<>() {
            @Override
            public String get() {
                return "Hello World~";
            }
        };
        System.out.println(supplier.get());
        System.out.println("--------------------------------------");

        // lambda方式使用Supplier接口
        supplier = () -> "Hello World~";
        System.out.println(supplier.get());
        System.out.println("--------------------------------------");

        // 方法引用方式使用Supplier接口，打印结果为 Hello World~
        supplier = () -> String.valueOf("Hello World~");
        System.out.println(supplier.get());
        System.out.println("--------------------------------------");

        // 方法引用方式使用Supplier接口，打印结果为 Hello World~
        String template = "Hello World~";
        supplier = template::toString;
        System.out.println(supplier.get());
        System.out.println("--------------------------------------");
    }

    /**
     * 测试Function函数式接口
     */
    @Test
    public void testFunction() {
        // 匿名内部类方式使用Function接口
        Function<String, Integer> function = new Function<>() {
            @Override
            public Integer apply(String str) {
                return Integer.parseInt(str);
            }
        };
        System.out.println(function.apply("10"));
        System.out.println("--------------------------------------");

        // lambda方式使用Predicate接口
        function = str -> Integer.parseInt(str);
        System.out.println(function.apply("20"));
        System.out.println("--------------------------------------");

        // 方法引用方式使用Predicate接口
        function = Integer :: parseInt;
        System.out.println(function.apply("30"));
        System.out.println("--------------------------------------");
    }

    /**
     * 测试Predicate函数式接口
     */
    @Test
    public void testPredicate() {
        // 匿名内部类方式使用Predicate接口
        Predicate<String> predicate = new Predicate<>() {
            @Override
            public boolean test(String str) {
                return str.contains("周");
            }
        };
        System.out.println(predicate.test("周杰伦"));
        System.out.println("--------------------------------------");

        // lambda方式使用Predicate接口
        predicate = str -> str.contains("周");
        System.out.println(predicate.test("周润发"));
        System.out.println("--------------------------------------");

        // 方法引用方式使用Predicate接口
        BiFunction<String, CharSequence, Boolean> biFunction = String::contains;
        System.out.println(biFunction.apply("周邦彦", "周"));
        System.out.println("--------------------------------------");
    }
}
