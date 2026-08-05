package org.bluebridge.section_08_jdk8_lts.unit_04_optional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Optional 容器测试
 *
 * 1. Optional 是一个容器
 * 2. Optional 不能作为方法参数，通常作为返回值，来规避空指针异常
 * 3. Optional 类常用方法
 *    - 创建 Optional 类的方法
 *      Optional.of(T t)                                         创建一个 Option 实例，t 必须为空，t 为空，报 java.lang.NullPointerException 异常
 *      Optional.empty()                                         创建一个空的 Option 实例
 *      Optional.ofNullable(T t)                                 创建一个 Option 实例，t 可以不为空，不会报任何异常
 *    - 判断 Optional 容器是否包含对象
 *      boolean Optional.isPresent()                             判断 Optional 容器中是否包含对象，包含对象返回 true，不包含对象返回 fasle
 *      void Optional.ifPresent(Consumer<? super T> action)      判断 Optional 容器中是否包含对象，如果包含对象，就执行 Consumer<? super T> action 类型的动作
 *    - 获取 Optional 容器中的对象
 *      T get()                                                  直接调用时如果 Optional 容器中包含对象，则返回该值，否则抛出异常，一般配合 Optional.isPresent() 或 ifPresent(Consumer<? super T> action) 使用
 *      T orElse(T other)                                        如果 Optional 容器中有值将其返回，否则返回指定的 Other 对象
 *      T orElseGet(Supplier<? extends T> supplier)              如果 Optional 容器中有值将其返回，否则返回由 Supplier 接口提供的对象
 *      T orElseThrow(Supplier<? extends X> exceptionSupplier)   如果 Optional 容器中有值将其返回，否则抛出由 Supplier 接口实现提供的异常
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class OptionalTest {

    /**
     * 创建一个 Option 实例，t 必须为空，t 为空，报 java.lang.NullPointerException 异常
     */
    @Test
    public void testOptionalOf() {
        Girl girl = new Girl();
        // 如果 t 为空，报 java.lang.NullPointerException 异常
        // girl = null;
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl);
    }

    /**
     * 创建一个空的 Option 实例
     */
    @Test
    public void testOptionalEmpty() {
        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);
    }

    /**
     * 创建一个 Option 实例，t 可以不为空，不会报任何异常
     */
    @Test
    public void testOptionalOfNullable() {
        Girl girl = new Girl();
        // 如果 t 为空，报 java.lang.NullPointerException 异常
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
    }

    /**
     * 判断 Optional 容器中是否包含对象，包含对象返回 true，不包含对象返回 fasle
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
     * 判断 Optional 容器中是否包含对象，如果包含对象，就执行 Consumer<? super T> action 类型的动作
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

        // 如果是空的就不执行 ifPresent() 中的 Consumer<? super T> action 类型的动作
        Optional.empty().ifPresent(System.out::println);
        System.out.println("----------------------------");
    }

    /**
     * 直接调用时如果 Optional 容器中包含对象，则返回该值，否则抛出异常，一般配合 Optional.isPresent() 或
     * ifPresent(Consumer<? super T>
     * action) 使用
     */
    @Test
    public void testOptionalGet() {
        // 创建一个非空的 Optional
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl.get());
        System.out.println("----------------------------");

        // 创建一个空的 Optional
        Optional<Object> emptyOptional = Optional.empty();
        // 直接调用
        // System.out.println(emptyOptional.get());

        // 配合 Optional.isPresent() 使用
        // if(emptyOptional.isPresent()) {
        // System.out.println(emptyOptional.get());
        // }

        // 配合 Optional.ifPresent(Consumer<? super T> action) 使用
        emptyOptional.ifPresent(System.out::println);
        System.out.println("----------------------------");
    }

    /**
     * 如果 Optional 容器中有值将其返回，否则返回指定的 Other 对象 如果 Optional 为空返回 "hello world!" 字符串
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
     * 如果 Optional 容器中有值将其返回，否则返回由 Supplier 接口提供的对象 如果 Optional 容器从 Supplier 中获取值并打印
     */
    @Test
    public void testOptionalOrElseGet() {
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElseGet(() -> "get from Supplier......"));
    }

    /**
     * 如果 Optional 容器中有值将其返回，否则抛出由 Supplier 接口实现提供的异常
     */
    @Test
    public void testOptionalOrElseThrow() {
        Optional<String> emptyOptional = Optional.empty();
        emptyOptional.ifPresent(System.out::println);
    }
}
