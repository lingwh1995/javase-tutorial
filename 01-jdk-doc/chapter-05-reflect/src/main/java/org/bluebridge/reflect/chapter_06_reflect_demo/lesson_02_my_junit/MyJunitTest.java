package org.bluebridge.reflect.chapter_06_reflect_demo.lesson_02_my_junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MyJunitTest {

    /**
     * 反射模拟junit
     *
     * 1. 获取测试类的Class文件
     * 2. 反射获取测试类中所有public方法
     * 3. 创建集合
     * 	    创建BeforeList: 存储所有使用了@Before的方法的信息
     * 		创建AfterList : 存储所有使用了@After的方法的信息
     * 		创建TestList  : 存储所有使用了@Test的方法的信息
     * 4. 迭代所有方法
     * 	  -->使用@Before:加入BeforeList
     * 	  -->使用@After :加入AfterList
     * 	  -->使用@Test	 :加入TestList
     * 5. 根据所加的注解的不同分别执行方法
     */

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //1.获取测试类的Class文件
        Class clazz = Class.forName("org.bluebridge.reflect.chapter_06_reflect_demo.lesson_02_my_junit.MyJunit");
        MyJunit mockJunitTester = (MyJunit)clazz.newInstance();
        //2.反射获取测试类中所有public方法
        Method[] methods = clazz.getMethods();
        //3.创建集合
        ArrayList<Method> beforeList = new ArrayList<Method>();
        ArrayList<Method> testList = new ArrayList<Method>();
        ArrayList<Method> afterList = new ArrayList<Method>();
        //4.迭代所有方法
        for(Method method:methods){
            if(method.isAnnotationPresent(Before.class)){
                beforeList.add(method);
            }else if(method.isAnnotationPresent(Test.class)){
                testList.add(method);
            }else if(method.isAnnotationPresent(After.class)){
                afterList.add(method);
            }else {

            }
        }
        //5.根据所加的注解的不同分别执行方法
        for(Method methodBefore:beforeList){
            methodBefore.invoke(mockJunitTester);
        }
        for(Method methodTest:testList){
            methodTest.invoke(mockJunitTester);
        }
        for(Method methodAfter:afterList){
            methodAfter.invoke(mockJunitTester);
        }
    }

}
