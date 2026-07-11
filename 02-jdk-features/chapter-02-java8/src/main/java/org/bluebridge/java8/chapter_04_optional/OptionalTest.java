package org.bluebridge.java8.chapter_04_optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

/**
 * Optional容器测试
 *
 * 1. Optional是一个容器
 * 2. Optional不能作为方法参数,通常作为返回值,来规避空指针异常
 * 3. Optional类常用方法
 *    - 创建Optional类的方法
 *      Optional.of(T t)                                         创建一个Option实例，t必须为空,t为空，报 java.lang.NullPointerException 异常
 *      Optional.empty()                                         创建一个空的Option实例
 *      Optional.ofNullable(T t)                                 创建一个Option实例，t可以不为空，不会报任何异常
 *    - 判断Optional容器是否包含对象
 *      boolean Optional.isPresent()                             判断Optional容器中是否包含对象，包含对象返回true，不包含对象返回fasle
 *      void Optional.ifPresent(Consumer<? super T> action)      判断Optional容器中是否包含对象，如果包含对象，就执行 Consumer<? super T> action 类型的动作
 *    - 获取Optional容器中的对象
 *      T get()                                                  直接调用时如果Optional容器中包含对象，则返回该值，否则抛出异常，一般配合 Optional.isPresent() 或 ifPresent(Consumer<? super T> action) 使用
 *      T orElse(T other)                                        如果Optional容器中有值将其返回，否则返回指定的 Other 对象
 *      T orElseGet(Supplier<? extends T> supplier)              如果Optional容器中有值将其返回，否则返回由 Supplier 接口提供的对象
 *      T orElseThrow(Supplier<? extends X> exceptionSupplier)   如果Optional容器中有值将其返回，否则抛出由 Supplier 接口实现提供的异常
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class OptionalTest {

    /**
     * 创建一个Option实例，t必须为空,t为空，报 java.lang.NullPointerException 异常
     */
    @Test
    public void testOptionalOf() {
        Girl girl = new Girl();
        // 如果t为空，报 java.lang.NullPointerException 异常
        // girl = null;
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl);
    }

    /**
     * 创建一个空的Option实例
     */
    @Test
    public void testOptionalEmpty() {
        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);
    }

    /**
     * 创建一个Option实例，t可以不为空，不会报任何异常
     */
    @Test
    public void testOptionalOfNullable() {
        Girl girl = new Girl();
        // 如果t为空，报 java.lang.NullPointerException 异常
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
    }

    /**
     * 判断Optional容器中是否包含对象，包含对象返回true，不包含对象返回fasle
     */
    @Test
    public void testOptionalIsPresent() {
        Optional<String> optionalStr = Optional.of("hello world!");
        System.out.println(optionalStr.isPresent());
        System.out.println("----------------------------");

        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl.isPresent());
        System.out.println("----------------------------");
    }

    /**
     * 判断Optional容器中是否包含对象，如果包含对象，就执行 Consumer<? super T> action 类型的动作
     */
    @Test
    public void testOptionalIfPresent() {
        Optional<String> optionalStr = Optional.of("hello world!");
        optionalStr.ifPresent(System.out::println);
        System.out.println("----------------------------");

        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
        optionalGirl.ifPresent(System.out::println);
        System.out.println("----------------------------");

        // 如果是空的就不执行 ifPresent() 中的Consumer<? super T> action 类型的动作
        Optional.empty().ifPresent(System.out::println);
        System.out.println("----------------------------");
    }

    /**
     * 直接调用时如果Optional容器中包含对象，则返回该值，否则抛出异常，一般配合 Optional.isPresent() 或 ifPresent(Consumer<? super T>
     * action) 使用
     */
    @Test
    public void testOptionalGet() {
        // 创建一个非空的Optional
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl.get());
        System.out.println("----------------------------");

        // 创建一个空的Optional
        Optional<Object> emptyOptional = Optional.empty();
        // 直接调用
        // System.out.println(emptyOptional.get());

        // 配合 Optional.isPresent() 使用
        //        if(emptyOptional.isPresent()) {
        //            System.out.println(emptyOptional.get());
        //        }

        // 配合 Optional.ifPresent(Consumer<? super T> action) 使用
        emptyOptional.ifPresent(System.out::println);
        System.out.println("----------------------------");
    }

    /**
     * 如果Optional容器中有值将其返回，否则返回指定的 Other 对象 如果Optional为空返回 "hello world!" 字符串
     */
    @Test
    public void testOptionalOrElse() {
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElse("hello world!"));
        System.out.println("----------------------------");

        List<String> list = Arrays.asList("a", "b", "c");
        list = null;
        System.out.println(Optional.ofNullable(list).orElse(Arrays.asList("1", "2", "3")));
        System.out.println("----------------------------");
    }

    /**
     * 如果Optional容器中有值将其返回，否则返回由 Supplier 接口提供的对象 如果Optional容器从Supplier中获取值并打印
     */
    @Test
    public void testOptionalOrElseGet() {
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElseGet(() -> "get from Supplier......"));
    }

    /**
     * 如果Optional容器中有值将其返回，否则抛出由 Supplier 接口实现提供的异常
     */
    @Test
    public void testOptionalOrElseThrow() {
        Optional<String> emptyOptional = Optional.empty();
        emptyOptional.ifPresent(System.out::println);
    }
}
