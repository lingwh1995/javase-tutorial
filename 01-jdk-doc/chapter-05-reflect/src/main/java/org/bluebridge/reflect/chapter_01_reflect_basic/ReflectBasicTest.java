package org.bluebridge.reflect.chapter_01_reflect_basic;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试反射操作Person类
 * @date 2019/03/16 10:00
 */

/**
 * 注意:
 *    反射必调用被操作对象的无参构造方法
 */
@Slf4j
public class ReflectBasicTest {

    /**
     * 获取反射对象的四种方式
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    @Test
    public void testReflectGetJavaBeanClass() throws ClassNotFoundException, NoSuchFieldException {
        // 方式1: 该对象自身的.class属性，注意:该方式只能获取到当前类的.class属性，不能获取到父类的.class属性
        Class<Person> class1 = Person.class;
        // 打印 class 类信息
        printClassInfo(class1);

        // 方式2: 根据运行时的类的对象
        Person person = new Person();
        Class<? extends Object> class2 = person.getClass();
        // 打印 class 类信息
        printClassInfo(class2);

        // 方式3: Class的静态方法.forName()获取:体现反射的动态性
        Class<? extends Object> class3 = Class.forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 打印 class 类信息
        printClassInfo(class3);

        // 方式4: 通过类加载器获取
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<? extends Object> clazz4 = classLoader.loadClass("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 打印 class 类信息
        printClassInfo(clazz4);
    }

    /**
     * 测试反射调用无参构造方法创建对象
     * @throws Exception
     */
    @Test
    public void testReflectCreateInstanceByNoArgsConstructor () throws Exception{
        // 1.获取class对象
        Class<? extends Object> clazz = getClass().forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        log.info("包名 + 类名: {}", clazz);
        log.info("类名: {}", clazz.getSimpleName());

        // 2.得到Person实例，通过反射操作无参构造方法
            // 通过反射操作无参构造器实例化对象方式1: 不推荐，从java9开始已经被标记为 废弃方法
        Person person = (Person)clazz.newInstance();
        log.info("反射操作无参构造实例化对象方式一 person: {}", person);
            // 通过反射操作无参构造器实例化对象方式2: 推荐
        person = (Person)clazz.getDeclaredConstructor().newInstance();
        log.info("反射操作无参构造实例化对象方式二 person: {}", person);
    }

    /**
     * 测试反射调用有参构造方法创建对象
     * @throws Exception
     */
    @Test
    public void testReflectCreateInstanceByRequiredArgsConstructor() throws Exception{
        // 1.获取class对象
        Class<? extends Object> clazz = Class.forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 2.得到Person实例，通过反射操作有参构造方法
            // 通过反射操作有参构造器实例化对象方式1: 直接传入两个参数的数据类型的class
        Person person = (Person)clazz.getDeclaredConstructor(String.class,String.class).newInstance("张三","18");
        log.info("反射操作有参构造实例化对象方式一 person: {}", person);
            // 通过反射操作有参构造器实例化对象方式2: 传入一个Class[]对象
        person = (Person)clazz.getDeclaredConstructor(new Class[]{String.class,String.class}).newInstance("李四","20");
        log.info("反射操作有参构造实例化对象方式二 person: {}", person);
    }

    /**
     * 测试反射操作属性(私有/公有) 获取属性信息 + 为属性设置值
     * @throws Exception
     */
    @Test
    public void testReflectGetFieldInfoAndSetFieldInfo() throws Exception{
        // 1.获取class对象
        Class clazz = Class.forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 2.得到Person对象
        Person person = (Person)clazz.getDeclaredConstructor().newInstance();
        // 3.获取属性
            // getDeclaredFields(): 得到所有的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        log.info("所有属性: {}", Arrays.toString(declaredFields));
            // 获取单个属性
        Field field = clazz.getDeclaredField("name");
        log.info("name属性: {}", field);

        // 4.操作属性
            // 设置可以操作私有属性
        field.setAccessible(true);
        // 第一个参数: 上面获取的类的实例 第二个参数:属性的具体值
        field.set(person, "张三");
        log.info("通过反射写入属性值: {}", person);

            //通过反射读取属性
        String name = (String)field.get(person);
        log.info("通过反射读取属性值: {}", name);
    }

    /**
     * 测试反射执行公有方法
     * @throws Exception
     */
    @Test
    public void testReflectInvokePublicMethod() throws Exception{
        // 1.获取class对象
        Class clazz = Class.forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 2.得到Person对象
        Person person = (Person)clazz.getDeclaredConstructor().newInstance();
        // 3.操作普通方法
        // 两个参数:1.方法名 2.参数数据类型
        Method method = clazz.getDeclaredMethod("setName", String.class);
        //4.使用反射执行该方法
        method.invoke(person, "zhangsan");
        log.info("通过反射执行方法给属性设置值: {}", person);
    }

    /**
     * 测试反射调用私有方法
     * @throws Exception
     */
    @Test
    public void testReflectInvokePrivateMethod() throws Exception{
        // 1.获取class对象
        Class clazz = getClass().forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 2.获取Person实例
        Person person = (Person)clazz.newInstance();
        // 3.使用反射操作私有方法
        Method method = clazz.getDeclaredMethod("drink", String.class);
        // 4.设置可以操作私有方法
        method.setAccessible(true);
        // 5.使用反射操作该私有方法
        method.invoke(person, "橙汁饮料......");
    }

    /**
     * 测试反射调用静态方法
     * @throws Exception
     */
    @Test
    public void testReflectInvokeStaticMethod() throws Exception{
        // 1.获取class对象
        Class clazz = getClass().forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 2.获取方法操作器
        Method method = clazz.getDeclaredMethod("sleep", String.class);
        // 3.设置可以操作私有方法
        method.setAccessible(true);
        // 4.使用反射操作该静态方法，注意：因为操作的方法是静态方法，所以不需要传入Person的实例对象
        int invokeResult = (int)method.invoke(null, "席梦思床");
        log.info("通过反射执行静态方法返回结果: {}", invokeResult);
    }

    /**
     * 使用反射获取属性修饰符 public/private
     * @throws Exception
     */
    @Test
    public void testReflectGetFieldModifiy() throws Exception{
        // 1.获取class对象
        Class clazz = getClass().forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 2.获取属性
        Field filed = clazz.getDeclaredField("name");
        // 3.获取属性的修饰符
        int modifiers = filed.getModifiers();
        log.info("{} 属性的修饰符: {}", filed.getName(), modifiers);
    }

    /**
     * 使用反射获取方法修饰符 public/private
     * @throws Exception
     */
    @Test
    public void testReflectGetMethodModifiy() throws Exception{
        // 1.获取class对象
        Class clazz = getClass().forName("org.bluebridge.reflect.chapter_01_reflect_basic.Person");
        // 2.获取方法
        Method method = clazz.getDeclaredMethod("sleep", String.class);
        // 3.获取方法的修饰符
        int modifiers = method.getModifiers();
        // 最终的打印结果是 10 ，private = 0x00000002, static = 0x00000008， private + static = 2 + 8 = 10
        log.info("{} 方法的修饰符: {}", method.getName(), modifiers);
    }

    /**
     * 获取class对象/构造器加强:直接可以获取到指定类型的Class对象和构造器，不用再强行转换了
     * @throws NoSuchMethodException
     */
    @Test
    public void testReflectCreateInstanceUseGeneric() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 1.获取指定类型的Class对象
        Class<Person> clazz = Person.class;

        // 2.获取指定类型的无参构造器
        Constructor<Person> constructor = clazz.getConstructor();
        Person person = constructor.newInstance();
        log.info("通过无参构造创建的实例: {}", person);

        // 3.获取指定类型的有参构造器
        constructor = clazz.getDeclaredConstructor(String.class, String.class);
        person = constructor.newInstance("张三", "18");
        log.info("通过有参构造创建的实例: {}", person);
    }

    /**
     * 打印类的class信息
     * @param clazz
     */
    private void printClassInfo(Class<?> clazz) {
        // 打印类的基本信息
        log.info("类名: {}", clazz.getSimpleName());
        log.info("完整类名: {}", clazz.getName());
        log.info("包名: {}", clazz.getPackage().getName());
        log.info("是否为接口: {}", clazz.isInterface());
        log.info("是否为枚举: {}", clazz.isEnum());
        log.info("父类: {}", (clazz.getSuperclass() != null ? clazz.getSuperclass().getSimpleName() : "无"));

        // 打印实现的接口
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            StringBuilder interfacesStr = new StringBuilder();
            for (int i = 0; i < interfaces.length; i++) {
                interfacesStr.append(interfaces[i].getSimpleName());
                if (i < interfaces.length - 1) interfacesStr.append(", ");
            }
            log.info("实现的接口: {}", interfacesStr.toString());
        }

        // 打印字段信息
        Field[] fields = clazz.getDeclaredFields();
        log.info("\n字段信息 ({}个):", fields.length);
        for (Field field : fields) {
            log.info("  {}", field.toString());
        }

        // 打印构造函数信息
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        log.info("\n构造函数 ({}个):", constructors.length);
        for (Constructor<?> constructor : constructors) {
            log.info("  {}", constructor.toString());
        }

        // 打印方法信息
        Method[] methods = clazz.getDeclaredMethods();
        log.info("\n方法 ({}个):", methods.length);
        for (Method method : methods) {
            log.info("  {}", method.toString());
        }
    }

}
