package org.bluebridge.java8.section_03_method_reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * 方法引用测试
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class MethodReferenceTest {

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例一: Function<String, Integer> function = Integer :: parseInt
	 */
	@Test
	public void testStaticMethodReference_1() {
		// 匿名内部类
		Function<String, Integer> function = new Function<String, Integer>() {
			@Override
			public Integer apply(String t) {
				return Integer.parseInt(t);
			}
		};
		Integer apply = function.apply("10");
		System.out.println(apply.getClass().getSimpleName());
		System.out.println("------------------------------");

		// lambda表达式
		function = (t) -> Integer.parseInt(t);
		apply = function.apply("20");
		System.out.println(apply.getClass().getSimpleName());
		System.out.println("------------------------------");

		// 方法引用
		function = Integer::parseInt;
		int number = function.apply("30");
		System.out.println(number);
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例一: Function<Integer, String> function = String :: valueOf
	 */
	@Test
	public void testStaticMethodReference_2() {
		// 匿名内部类
		Function<Integer, String> function = new Function<Integer, String>() {
			@Override
			public String apply(Integer integer) {
				return String.valueOf(integer);
			}
		};
		String apply = function.apply(10);
		System.out.println("apply = " + apply);
		System.out.println(apply.getClass().getSimpleName());
		System.out.println("------------------------------");

		// lambda表达式
		function = (i) -> String.valueOf(i);
		apply = function.apply(20);
		System.out.println("apply = " + apply);
		System.out.println(apply.getClass().getSimpleName());
		System.out.println("------------------------------");

		// 方法引用
		function = String::valueOf;
		apply = function.apply(30);
		System.out.println("apply = " + apply);
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例二: Printer :: printMessage
	 */
	@Test
	public void testStaticMethodReference_3() {
		// 匿名内部类
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {
				Printer.printMessage(s);
			}
		};
		consumer.accept("10");
		System.out.println("------------------------------");

		// lambda表达式
		consumer = s -> Printer.printMessage(s);
		consumer.accept("20");
		System.out.println("------------------------------");

		// 方法引用
		consumer = Printer::printMessage;
		consumer.accept("30");
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例三: Comparator<Integer> comparator = Integer :: compare;
	 */
	@Test
	public void testStaticMethodReference_4() {
		// 匿名内部类
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		System.out.println(comparator.compare(10, 20));
		System.out.println("------------------------------");

		// lambda表达式
		comparator = (t1, t2) -> Integer.compare(t1, t2);
		System.out.println(comparator.compare(20, 10));
		System.out.println("------------------------------");

		// 方法引用
		comparator = Integer::compare;
		System.out.println(comparator.compare(10, 20));
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例四: Function<Double, Long> function = Math :: round;
	 */
	@Test
	public void testStaticMethodReference_5() {
		// 匿名内部类
		Function<Double, Long> function = new Function<Double, Long>() {
			@Override
			public Long apply(Double d) {
				return Math.round(d);
			}
		};
		System.out.println(function.apply(12.3));
		System.out.println("------------------------------");

		// lambda表达式
		function = d -> Math.round(d);
		System.out.println(function.apply(15.6));
		System.out.println("------------------------------");

		// 方法引用
		function = Math::round;
		System.out.println(function.apply(18.3));
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例五: Supplier<Double> supplier = Math :: random;
	 */
	@Test
	public void testStaticMethodReference_6() {
		// 匿名内部类
		Supplier<Double> supplier = new Supplier<>() {
			@Override
			public Double get() {
				return Math.random();
			}
		};
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		// lambda表达式
		supplier = () -> Math.random();
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		// 方法引用
		supplier = Math::random;
		System.out.println(supplier.get());
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例六: Supplier<Double> supplier = Math :: random;
	 */
	@Test
	public void testStaticMethodReference_7() {
		// 匿名内部类
		Function<Double, Double> function = new Function<>() {
			@Override
			public Double apply(Double d) {
				return Math.sqrt(d);
			}
		};
		System.out.println(function.apply(4.0));
		System.out.println("------------------------------");

		// lambda表达式
		function = (d) -> Math.sqrt(d);
		System.out.println(function.apply(16.0));
		System.out.println("------------------------------");

		// 方法引用
		function = Math::sqrt;
		System.out.println(function.apply(64.0));
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例七: Function<Double, Long> function = Math :: max;
	 */
	@Test
	public void testStaticMethodReference_8() {
		// 匿名内部类
		BiFunction<Integer, Integer, Integer> biFunction = new BiFunction<Integer, Integer, Integer>() {
			@Override
			public Integer apply(Integer a, Integer b) {
				return Math.max(a, b);
			}
		};
		System.out.println(biFunction.apply(10, 20));
		System.out.println("------------------------------");

		// lambda表达式
		biFunction = (a, b) -> Math.max(a, b);
		System.out.println(biFunction.apply(20, 30));
		System.out.println("------------------------------");

		// 方法引用
		biFunction = Math::max;
		System.out.println(biFunction.apply(30, 40));
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例八: Function<String, String> function = String :: toUpperCase;
	 */
	@Test
	public void testStaticMethodReference_9() {
		// 匿名内部类
		Function<String, String> function = new Function<String, String>() {
			@Override
			public String apply(String s) {
				return s.toUpperCase();
			}
		};
		System.out.println(function.apply("abc"));
		System.out.println("------------------------------");

		// lambda表达式
		function = s -> s.toUpperCase();
		System.out.println(function.apply("abc"));
		System.out.println("------------------------------");

		// 方法引用
		function = String::toUpperCase;
		System.out.println(function.apply("abc"));
		System.out.println("------------------------------");
	}

	/**
	 * 情况一: 静态方法引用(类名 :: 静态方法名)
     *
	 * 案例九: System.out :: println
	 */
	@Test
	public void testStaticMethodReference_10() {
		// lambda表达式
		List<String> list = Arrays.asList("a", "b", "c", "d", "e");
		list.forEach(item -> System.out.println(item));
		System.out.println("------------------------------------");

		// 方法引用
		list.forEach(System.out::println);
		System.out.println("------------------------------------");

		// 方法引用
		list.forEach(MethodReferenceTest::print);
		System.out.println("------------------------------------");

		// 方法引用
		list.stream().filter(MethodReferenceTest::isCharC).forEach(System.out::println);
		System.out.println("------------------------------------");
	}

	/**
	 * 打印字符s
	 *
	 * @param s
	 */
	public static void print(String s) {
		System.out.println(s);
	}

	/**
	 * 判断字符s是不是小写 c
	 *
	 * @param s
	 * @return
	 */
	public static boolean isCharC(String s) {
		return s.equals("c");
	}

	/**
	 * 情况二: 实例方法引用(格式一 对象 :: 非静态方法名)
     *
	 * 案例一: String s = "method reference";
	 * Supplier<Boolean> supplier = s :: isEmpty
	 */
	@Test
	public void testInstanceReference_1() {
		String s = "method reference";
		// 匿名内部类
		Supplier<Boolean> supplier = new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				return s.isEmpty();
			}
		};
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		// lambda表达式
		supplier = () -> s.isEmpty();
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		// 方法引用
		supplier = s::isEmpty;
		System.out.println(supplier.get());
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式一 对象 :: 非静态方法名)
     *
	 * 案例二: PrintStream ps = System.out;
	 * Consumer<String> consumer = ps :: println;
	 */
	@Test
	public void testInstanceReference_2() {
		// 匿名内部类
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String i) {
				System.out.println(i);
			}
		};
		consumer.accept("10");
		System.out.println("------------------------------");

		// lambda表达式
		consumer = i -> System.out.println(i);
		consumer.accept("20");
		System.out.println("------------------------------");

		// 方法引用
		PrintStream ps = System.out;
		consumer = ps::println;
		consumer.accept("30");
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式一 对象 :: 非静态方法名)
     *
	 * 案例三: Person person = new Person("张三", 23);
	 * person :: getUsername;
	 */
	@Test
	public void testInstanceReference_3() {
		Person person = new Person("张三", 23);
		// 匿名内部类
		Supplier<String> supplier = new Supplier<String>() {
			@Override
			public String get() {
				return person.getUsername();
			}
		};
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		// lambda表达式
		supplier = () -> person.getUsername();
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		// 方法引用
		supplier = person::getUsername;
		System.out.println(supplier.get());
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式二 类名 :: 非静态方法名)
     *
	 * 案例四: Comparator<String> comparator = String :: compareTo;
	 */
	@Test
	public void testInstanceReference_4() {
		// 匿名内部类
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};

		// lambda
		comparator = (s1, s2) -> s1.compareTo(s2);
		System.out.println(comparator.compare("abc", "abd"));
		System.out.println("------------------------------");

		// 方法引用
		comparator = String::compareTo;
		System.out.println(comparator.compare("abc", "abc"));
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式二 类名 :: 非静态方法名)
     *
	 * 案例五: Comparator<String> comparator = String :: compareTo;
	 */
	@Test
	public void testInstanceReference_5() {
		// 方法引用第三种形式的第二个例子
		List<String> cities = Arrays.asList("shanghai", "beijing", "tianjin");

		// 匿名内部类
		Collections.sort(cities, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		cities.forEach(item -> System.out.println(item));
		System.out.println("------------------------------");

		// lambda表达式
		Collections.sort(cities, (city1, city2) -> city1.compareTo(city2));
		cities.forEach(item -> System.out.println(item));
		System.out.println("------------------------------");

		// 方法引用
		Collections.sort(cities, String::compareTo);
		cities.forEach(item -> System.out.println(item));
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式二 类名 :: 非静态方法名)
     *
	 * 案例六: Comparator<String> comparator = String :: compareTo;
	 */
	@Test
	public void testMethodReference_6() {
		// 匿名内部类
		BiPredicate<String, String> biPredicate = new BiPredicate<String, String>() {
			@Override
			public boolean test(String a, String b) {
				return a.equals(b);
			}
		};
		System.out.println(biPredicate.test("aaa", "aaa"));
		System.out.println("--------------------------------------");

		// lambda表达式
		biPredicate = (a, b) -> a.equals(b);
		System.out.println(biPredicate.test("bbb", "bbb"));
		System.out.println("--------------------------------------");

		// 方法引用
		biPredicate = String::equals;
		System.out.println(biPredicate.test("ccc", "ccc"));
		System.out.println("--------------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式二 类名 :: 非静态方法名)
     *
	 * 案例七: Function<Person, String> function = Person :: getUsername;
	 */
	@Test
	public void testMethodReference_7() {
		// 匿名内部类
		Person person = new Person("张三", 128);
		Function<Person, String> function = new Function<Person, String>() {
			@Override
			public String apply(Person t) {
				return t.getUsername();
			}
		};
		System.out.println(function.apply(person));
		System.out.println("------------------------------");

		// lambda表达式
		function = p -> p.getUsername();
		System.out.println(function.apply(person));
		System.out.println("------------------------------");

		// 方法引用
		function = Person::getUsername;
		System.out.println(function.apply(person));
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式二 类名 :: 非静态方法名)
     *
	 * 案例七: BiConsumer<Person, String> = (Person p, String username) -> p.setUsername(username);
	 */
	@Test
	public void testMethodReference_8() {
		Person person = new Person();
		// 匿名内部类
		BiConsumer<Person, String> biConsumer = new BiConsumer<>() {
			@Override
			public void accept(Person person, String username) {
				person.setUsername(username);
			}
		};
		biConsumer.accept(person, "张三");
		System.out.println("person = " + person);
		System.out.println("------------------------------");

		// lambda表达式
		biConsumer = (Person p, String username) -> p.setUsername(username);
		biConsumer.accept(person, "李四");
		System.out.println("person = " + person);
		System.out.println("------------------------------");

		// 方法引用
		biConsumer = Person::setUsername;
		biConsumer.accept(person, "王五");
		System.out.println("person = " + person);
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式二 类名 :: 非静态方法名)
     *
	 * 案例七: Function<Person, Integer> function = Person::hashCode;
	 */
	@Test
	public void testMethodReference_9() {
		Person person = new Person();
		// 匿名内部类
		Function<Person, Integer> function = new Function<>() {
			@Override
			public Integer apply(Person person) {
				return person.hashCode();
			}
		};
		System.out.println(function.apply(person));
		System.out.println("------------------------------");

		// lambda表达式
		function = (Person p) -> p.hashCode();
		System.out.println(function.apply(person));
		System.out.println("------------------------------");

		// 方法引用
		function = Person::hashCode;
		System.out.println(function.apply(person));
		System.out.println("------------------------------");
	}

	/**
	 * 情况二: 实例方法引用(格式二 类名 :: 非静态方法名)
     *
	 * 案例七: Function<Person, String> function = Person :: getUsername;
	 */
	@Test
	public void testMethodReference_10() {
		Person p1 = new Person("张三", 10);
		Person p2 = new Person("李四", 20);
		// 匿名内部类
		BiFunction<Person, Person, Boolean> biFunction = new BiFunction<>() {
			@Override
			public Boolean apply(Person o1, Person o2) {
				return o1.equals(o2);
			}
		};
		System.out.println(biFunction.apply(p1, p2));
		System.out.println("------------------------------");

		// lambda表达式
		biFunction = (Person o1, Person o2) -> o1.equals(o2);
		System.out.println(biFunction.apply(p1, p2));
		System.out.println("------------------------------");

		// 方法引用
		biFunction = Person::equals;
		System.out.println(biFunction.apply(p1, p2));
		System.out.println("------------------------------");
	}

	/**
	 * 情况三: 构造方法引用(类名::new)
     *
	 * 案例一: ArrayList::new
	 */
	@Test
	public void testConstructorReference_1() {
		// 不设置ArrayList初始容量
		Supplier<List<Integer>> supplier = ArrayList::new;
		List<Integer> list = supplier.get();
		list.add(10);
		System.out.println(list);

		// 设置ArrayList初始容量为32
		Function<Integer, List<Integer>> function = ArrayList::new;
		list = function.apply(32);
		list.add(20);
		System.out.println(list);
	}

	/**
	 * 情况三: 构造方法引用(类名::new)
     *
	 * 案例二: Person::new
	 */
	@Test
	public void testConstructorReference_2() {
		// 匿名内部类
		Supplier<Person> supplier = new Supplier<Person>() {
			@Override
			public Person get() {
				return new Person();
			}
		};
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		// lambda表达式
		supplier = () -> new Person();
		System.out.println(supplier.get());
		System.out.println("------------------------------");

		supplier = Person::new;
		System.out.println(supplier.get());
		System.out.println("------------------------------");
	}

	/**
	 * 情况三: 构造方法引用(类名::new)
     *
	 * 案例二: Person::new
	 */
	@Test
	public void testConstructorReference_3() {
		// 匿名内部类
		Function<Integer, Person> function = new Function<Integer, Person>() {
			@Override
			public Person apply(Integer age) {
				return new Person(age);
			}
		};
		System.out.println(function.apply(10));
		System.out.println("------------------------------");

		// lambda表达式
		function = age -> new Person(age);
		System.out.println(function.apply(20));
		System.out.println("------------------------------");

		function = Person::new;
		System.out.println(function.apply(30));
		System.out.println("------------------------------");
	}

	/**
	 * 情况三: 构造方法引用(类名::new)
     *
	 * 案例二: Person::new
	 */
	@Test
	public void testConstructorReference_4() {
		// 匿名内部类
		BiFunction<String, Integer, Person> biFunction = new BiFunction<String, Integer, Person>() {
			@Override
			public Person apply(String username, Integer age) {
				return new Person(username, age);
			}
		};
		System.out.println(biFunction.apply("张三", 10));
		System.out.println("------------------------------");

		// lambda表达式
		biFunction = (username, age) -> new Person(username, age);
		System.out.println(biFunction.apply("李四", 20));
		System.out.println("------------------------------");

		biFunction = Person::new;
		System.out.println(biFunction.apply("王五", 30));
		System.out.println("------------------------------");
	}

	/**
	 * 情况三: 构造方法引用(类名::new)
     *
	 * 案例三: Person::new
	 */
	@Test
	public void testConstructorReference_5() {
		// 匿名内部类
		Function<Integer, Person> function = new Function<Integer, Person>() {
			@Override
			public Person apply(Integer id) {
				return new Person(id);
			}
		};
		System.out.println(function.apply(10));
		System.out.println("------------------------------");

		// lambda表达式
		function = (id) -> new Person(id);
		System.out.println(function.apply(20));
		System.out.println("------------------------------");

		function = Person::new;
		System.out.println(function.apply(30));
		System.out.println("------------------------------");
	}

	/**
	 * 情况三: 构造方法引用(类名::new)
     *
	 * 案例三: Person::new
	 */
	@Test
	public void testConstructorReference_6() {
		// 匿名内部类
		BiFunction<String, Integer, Person> biFunction = new BiFunction<String, Integer, Person>() {
			@Override
			public Person apply(String username, Integer id) {
				return new Person(username, id);
			}
		};
		System.out.println(biFunction.apply("张三", 10));
		System.out.println("------------------------------");

		// lambda表达式
		biFunction = (username, id) -> new Person(username, id);
		System.out.println(biFunction.apply("李四", 20));
		System.out.println("------------------------------");

		biFunction = Person::new;
		System.out.println(biFunction.apply("王五", 30));
		System.out.println("------------------------------");
	}

	/**
	 * 情况四: 数组构造方法引用 数据类型[]::new
     *
	 * 案例一: 使用方法引用创建空的String类型数组
	 */
	@Test
	public void testArrayConstructorReference_1() {
		// lambda表达式(初始化一个长度为10的String类型数组)
		Function<Integer, String[]> function = l -> new String[l];
		String[] apply = function.apply(10);
		System.out.println(apply);

		// 数组构造引用(初始化一个长度为10的String类型数组)
		function = String[]::new;
		apply = function.apply(10);
		System.out.println(apply);
	}

	/**
	 * 情况四: 数组构造方法引用 数据类型[] :: new
     *
	 * 案例二: 使用方法引用创建空的int类型数组
	 */
	@Test
	public void testArrayConstructorReference_2() {
		// lambda表达式(初始化一个长度为10的int类型数组)
		Function<Integer, int[]> function = n -> new int[n];
		int[] apply = function.apply(10);
		System.out.println(Arrays.toString(apply));

		// 数组构造引用(初始化一个长度为10的int类型数组)
		function = int[]::new;
		apply = function.apply(10);
		System.out.println(Arrays.toString(apply));
	}

	/**
	 * 情况四: 数组构造方法引用 数据类型[] :: new
	 */
	@Test
	public void testArrayConstructorReference_3() {
		// 1. 创建集合并添加元素
		ArrayList<Integer> list = new ArrayList<>();
		Collections.addAll(list, 1, 2, 3, 4, 5);
		// 2. 收集到数组当中
		Integer[] nums = list.stream().toArray(new IntFunction<Integer[]>() {
			@Override
			public Integer[] apply(int value) {
				return new Integer[value];
			}
		});

		// 方法引用
		Integer[] arr2 = list.stream()
				// 此时它会去创建一个Integer类型的数组，长度和流里面数组的个数是一样的，并把流里面的数据放到数组中
				.toArray(Integer[]::new);
		System.out.println(Arrays.toString(arr2));
	}

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
	private String username;
	private Integer age;

	public Person(Integer age) {
		this.age = age;
	}
}

class Printer {
	static void printMessage(String message) {
		System.out.println(message);
	}
}
