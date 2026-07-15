package org.bluebridge.javassist.test;

import javassist.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 测试javassist功能
 *
 * @author lingwh
 * @date 2025/8/18 14:33
 */
@Slf4j
public class JavassistTest {

    /**
     * 测试使用Javassist生成class文件（在d:/myjava下生成 com/bluebridge/javassist/EmployCt.class这个文件）
     *
     * @throws Exception
     */
    @Test
    public void testJavassistGenerateClass() throws Exception {
        // 1.获取创建器
        ClassPool pool = ClassPool.getDefault();
        // 2.创建类的class文件
        CtClass ctClass = pool.makeClass("org.bluebridge.javassist.EmployCt");
        // 3.创建属性
        CtField idField = CtField.make("private int id;", ctClass);
        CtField nameField = CtField.make("private String name;", ctClass);
        // 把属性加入到类中
        ctClass.addField(idField);
        ctClass.addField(nameField);

        // 4.创建方法
        // 创建id属性的setter()/getter()方法
        CtMethod idSetMethod = CtMethod.make("public void setId(int id){this.id = id;}", ctClass);
        CtMethod idGetMethod = CtMethod.make("public int getId(){return this.id;}", ctClass);
        // 创建name属性的setter()/getter()方法
        CtMethod nameSetMethod =
                CtMethod.make("public void setName(String name){this.name = name;}", ctClass);
        CtMethod nameGetMethod = CtMethod.make("public String getName(){return this.name;}", ctClass);
        // 把方法添加到类中
        ctClass.addMethod(idSetMethod);
        ctClass.addMethod(idGetMethod);
        ctClass.addMethod(nameSetMethod);
        ctClass.addMethod(nameGetMethod);

        // 5.创建有参构造方法(添加有参构造器)
        CtConstructor ctConstructorHasParam =
                new CtConstructor(new CtClass[] {CtClass.intType, pool.get("java.lang.String")}, ctClass);
        // 创建构造方法的方法体
        ctConstructorHasParam.setBody("{this.id = id; this.name = name;}");

        // 6.创建无参构造方法(添加无参构造器)
        CtConstructor ctConstructorNoParam = new CtConstructor(null, ctClass);
        // 创建构造方法的方法体
        ctConstructorNoParam.setBody("{}");

        // 7.生成该java文件
        ctClass.writeFile("d:/myjava");
    }

    /**
     * 测试JavaSist API获取类的信息
     *
     * @throws NotFoundException
     * @throws IOException
     * @throws CannotCompileException
     */
    @Test
    public void testJavassistGetClassInfo() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = null;
        try {
            ctClass = pool.get("org.bluebridge.javassist.domain.Employee");
            byte[] bytecode = ctClass.toBytecode();
            log.info("bytecode: {}", bytecode);

            // 获取类名
            log.info("类的全限定名: {}", ctClass.getName());
            log.info("类名简单形式: {}", ctClass.getSimpleName());
            log.info("类的父接口的Class对象: {}", ctClass.getInterfaces());
            log.info("类的父类的Class对象: {}", ctClass.getSuperclass());
        } catch (NotFoundException | IOException | CannotCompileException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试使用Javassist修改类，给类添加方法
     */
    @Test
    public void testJavassistAddMethodInClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = null;
        try {
            ctClass = pool.get("org.bluebridge.javassist.domain.Employee");
            // 添加方法方式1
            // CtMethod ctMethod = CtMethod.make("public int add(int a,int b){return a+b;}", cc);

            // 添加方法方式2
            CtMethod ctMethod =
                    new CtMethod(
                            CtClass.intType, "add", new CtClass[] {CtClass.intType, CtClass.intType}, ctClass);
            ctMethod.setModifiers(java.lang.reflect.Modifier.PUBLIC);
            ctMethod.setBody(
                    "{System.out.println(\"Hello World!\");System.out.println(\"Hello World!\");return $1+$2;}");
            ctClass.addMethod(ctMethod);

            // 通过反射调用添加的方法
            Class<? extends Object> employClass = ctClass.toClass();
            Object object = employClass.newInstance();

            // 使用反射获取指定名称的方法，使用javassist也可以获取到指定名称的方法，只不过参数传递的方式不同
            Method method = employClass.getDeclaredMethod("add", int.class, int.class);
            Object result = method.invoke(object, 1, 2);
            log.info("result: {}", result);
        } catch (NotFoundException
                | CannotCompileException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试使用javassit获取方法，并在方法调用之前对方法进行增强，实现在字节码层面对方法进行增强
     * 注意:javassit可以在字节码层面修改class文件的内容，假如修改了某个方法，但是并不能直接调用该方法，只能通过反射去调用
     */
    @Test
    public void testJavassistEnhanceMethodInClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = null;
        try {
            ctClass = pool.get("org.bluebridge.javassist.domain.Employee");
            // 获取到CtMethod对象
            CtMethod ctMethod =
                    ctClass.getDeclaredMethod("sayHello", new CtClass[] {pool.get("java.lang.String")});
            // 在方法执行前添加操作
            ctMethod.insertBefore("System.out.println(\"Before......\");");
            // 在方法执行后添加操作
            ctMethod.insertAfter("System.out.println(\"After......\");");
            // 在com.dragonsoft.javassist.Employ中第41行添加Java代码(操作)
            ctMethod.insertAt(41, "int b = 5; System.out.println(\"b的值是:\" + b);");

            // 通过反射调用生成的方法
            Class<? extends Object> employClass = ctClass.toClass();
            Object object = employClass.newInstance();
            Method method = employClass.getDeclaredMethod("sayHello", String.class);
            Object result = method.invoke(object, "I am massage!");
            log.info("result: {}", result);
        } catch (NotFoundException
                | CannotCompileException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试使用Javassist修改类，给类添加属性
     */
    @Test
    public void testJavassistAddFieldInClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = null;
        try {
            ctClass = pool.get("org.bluebridge.javassist.domain.Employee");
            // 添加属性方式1
            // CtField schoolField = CtField.make("private String school", ctClass);
            // 添加属性方式2
            CtField schoolField = new CtField(pool.get("java.lang.String"), "school", ctClass);
            schoolField.setModifiers(Modifier.PRIVATE);
            ctClass.addField(schoolField);

            // javassist中提供了相关的api为创建的属性增加setter和getter方法
            ctClass.addMethod(CtNewMethod.getter("getSchool", schoolField));
            ctClass.addMethod(CtNewMethod.setter("setSchool", schoolField));

            // 通过反射调用生成的方法
            Class<? extends Object> employClass = ctClass.toClass();
            Object object = employClass.newInstance();
            Method setMethod = employClass.getDeclaredMethod("setSchool", String.class);
            Object result = setMethod.invoke(object, "ufe");
            Method getMethod = employClass.getDeclaredMethod("getSchool");
            result = getMethod.invoke(object);
            log.info("result: {}", result);
        } catch (NotFoundException
                | CannotCompileException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
