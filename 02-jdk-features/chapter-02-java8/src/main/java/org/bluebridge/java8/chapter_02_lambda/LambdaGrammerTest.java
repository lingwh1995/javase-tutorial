package org.bluebridge.java8.chapter_02_lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Lambda表达式语法(有六种语法格式:包括完整写法和简化写法)
 *
 * 1. 举例 (o1, o2) -> Integer.compare(o1, o2)
 * 2. 格式
 *    - 左边		lambda形参列表(实际上就是接口中的抽象方法的形参列表)
 *    - 中间		lambda操作符 或 箭头操作符
 *    - 右边		lambda体(其实就是重写的抽象方法的方法体)
 *  3. lambda使用总结
 *    - 左边		lambda形参列表的参数类型可以省略，如果lambda形参列表只有一个参数，包裹这个参数的小括号也可以省略
 *    - 右边		lambda体应该使用一对{}包裹，如果lambda体只有一条执行语句(可能是return语句)，可以省略return和这一对大括号
 *  4. lambda表达式的本质	作为函数式接口的实例，lambda表达式要想使用，必然依赖于函数式接口
 *  5. 函数式接口	只包含一个抽象方法的接口，称为函数式接口
 *
 * @author lingwh
 * @date 2025/12/2 17:12
 */
public class LambdaGrammerTest {

	/**
	 * 语法格式一：无参，无返回值
	 */
	@Test
	public void testLambdaGrammer_1() {
		/**
		 * 使用匿名内部类
		 */
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello lambda...");
			}
		};
		r.run();

		/**
		 * 使用lambda表达式
		 */
		r = () -> System.out.println("hello lambda...");
		r.run();
	}

	/**
	 * 语法格式二：需要一个参数，但是没有返回值
	 */
	@Test
	public void testLambdaGrammer_2() {
		/**
		 * 使用匿名内部类
		 */
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		consumer.accept("使用匿名内部类...");
		System.out.println("--------------------------------------");

		/**
		 * 使用lambda表达式
		 */
		consumer = (String s) -> {
			System.out.println(s);
		};
		consumer.accept("使用lambda表达式...");
	}

	/**
	 * 语法格式三：数据类型可以省略，可以由编译器推导出来，称为类型推断
	 */
	@Test
	public void testLambdaGrammer_3() {
		/**
		 * 使用匿名内部类
		 */
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		consumer.accept("使用匿名内部类...");
		System.out.println("--------------------------------------");

		/**
		 * 使用lambda表达式(省略参数，由编译器推导类型)
		 */
		consumer = (s) -> System.out.println(s);
		consumer.accept("使用lambda表达式(省略参数，由编译器推导类型，称为类型推断)...");

		/**
		 * 补充：java8之前的类型推断
		 */
		// 不使用类型推断
		List<String> list1 = new ArrayList<String>();
		// 使用类型推断
		List<String> list2 = new ArrayList<>();

		// 不使用类型推断
		Integer[] arr1 = new Integer[] {1, 2, 3};
		// 使用类型推断
		Integer[] arr2 = {1, 2, 3};
	}

	/**
	 * 语法格式四：如果只有一个参数，参数的小括号也可以省略
	 */
	@Test
	public void testLambdaGrammer_4() {
		/**
		 * 使用匿名内部类
		 */
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		consumer.accept("使用匿名内部类...");
		System.out.println("--------------------------------------");

		/**
		 * 使用lambda表达式(省略参数，由编译器推导类型) + 省略参数的小括号
		 */
		consumer = s -> System.out.println(s);
		consumer.accept("使用lambda表达式(省略参数，由编译器推导类型) + 省略参数的小括号...");
	}

	/**
	 * 语法格式五：需要两个或以上参数，多条语句执行，并且可以有多条返回值，这种情况一般只能简写到lambda形式+省略参数类型
	 */
	@Test
	public void testLambdaGrammer_5() {
		/**
		 * 使用匿名内部类调用
		 */
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				System.out.println(o1);
				System.out.println(o2);
				return Integer.compare(o1, o2);
			}
		};
		int result = comparator.compare(10, 20);
		System.out.println(result);
		System.out.println("--------------------------------------");

		/**
		 * 使用lambda方式调用
		 */
		comparator = (o1, o2) -> {
			System.out.println(o1);
			System.out.println(o2);
			return Integer.compare(o1, o2);
		};
		result = comparator.compare(20, 10);
		System.out.println(result);
	}

	/**
	 * 语法格式六：当lambda体只有一条语句时，如果有return和括号，都可以省略
	 */
	@Test
	public void testLambdaGrammer_6() {
		/**
		 * 使用匿名内部类调用
		 */
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		int result = comparator.compare(10, 20);
		System.out.println(result);
		System.out.println("--------------------------------------");

		/**
		 * 使用lambda方式调用
		 */
		comparator = (o1, o2) -> Integer.compare(o1, o2);
		result = comparator.compare(20, 10);
		System.out.println(result);
	}

}
