package org.bluebridge.section_17_jdk17.unit_01_sealed;

import org.junit.Test;

import java.util.Arrays;

/**
 * JDK 17 Sealed 类测试(STANDARD 正式特性)
 *
 * Sealed 类(JEP 409) 在 JDK 17 中转正为 STANDARD 正式特性, 不再需要 --enable-preview 参数。
 * sealed(密封)类/接口通过 permits 子句明确声明允许哪些类继承/实现它, 严格控制继承层级:
 *   1. sealed 接口: 只能被 permits 中声明的类实现
 *   2. 允许的子类必须是 final、sealed 或 non-sealed 之一
 *      - final: 继承树终止, 不允许再被继承
 *      - sealed: 继续密封, 通过 permits 限制下一代子类
 *      - non-sealed: 解除密封, 允许任意类继承
 *
 * 本类直接在测试类内部定义嵌套 sealed 层级(sealed 类及子类必须是顶层类或嵌套类):
 *   sealed interface Shape permits Circle, Square, Triangle
 *   ├── final class Circle           implements Shape
 *   ├── non-sealed class Square      implements Shape
 *   └── sealed class Triangle        implements Shape permits EquilateralTriangle
 *       └── final class EquilateralTriangle extends Triangle
 *
 * 演化历程: Sealed 类 JDK 15(1st PREVIEW) → JDK 16(2nd PREVIEW) → JDK 17(JEP 409, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 18:45
 */
public class SealedClassTest {

    /**
     * sealed 接口: Shape, 只允许 Circle、Square、Triangle 三个类实现
     * permits 子句声明的允许类必须与 sealed 接口位于同一模块/编译单元
     */
    sealed interface Shape permits Circle, Square, Triangle { }

    /**
     * final 类: 继承树终止, 不允许再被任何类继承
     */
    static final class Circle implements Shape { }

    /**
     * non-sealed 类: 解除密封, 允许任意类继承 Square
     */
    static non-sealed class Square implements Shape { }

    /**
     * sealed 类: 继续密封, 只允许 EquilateralTriangle 一个类继承
     */
    static sealed class Triangle implements Shape permits EquilateralTriangle { }

    /**
     * final 类: 作为 Triangle 的唯一允许子类
     */
    static final class EquilateralTriangle extends Triangle { }

    /**
     * 测试 sealed 接口的基本用法与层级(STANDARD)
     * permits 中的三个允许类: final 类 Circle、non-sealed 类 Square、sealed 类 Triangle
     */
    @Test
    public void testSealedInterfaceHierarchy() {
        Shape circle = new Circle();
        Shape square = new Square();
        Shape triangle = new EquilateralTriangle();
        System.out.println("Circle 的简单类名: " + circle.getClass().getSimpleName());
        System.out.println("Square 的简单类名: " + square.getClass().getSimpleName());
        System.out.println("EquilateralTriangle 的父类: " + triangle.getClass().getSuperclass().getSimpleName());
    }

    /**
     * 测试 sealed 层级中的 final / non-sealed / sealed 三种子类(STANDARD)
     * 允许子类只能是 final、sealed、non-sealed 之一, 否则编译报错
     */
    @Test
    public void testSealedSubclassKinds() {
        // final 类: 继承树终止
        System.out.println("Circle 是 final 类, 无法再被继承");
        // non-sealed 类: 解除密封, 允许任意类继承
        System.out.println("Square 是 non-sealed 类, 可以被任意类继承");
        // sealed 类: 继续密封, 通过 permits 限制下一代子类
        System.out.println("Triangle 是 sealed 类, 只允许 " +
                Arrays.stream(Triangle.class.getPermittedSubclasses())
                        .map(Class::getSimpleName)
                        .toList() + " 继承");
    }

    /**
     * 测试通过反射查看 sealed 信息(STANDARD)
     * JDK 17 为 Class 提供了 isSealed() 与 getPermittedSubclasses() 两个反射方法
     */
    @Test
    public void testSealedReflection() {
        // isSealed(): 判断类/接口是否被密封
        System.out.println("Shape.isSealed() = " + Shape.class.isSealed());
        System.out.println("Circle.isSealed() = " + Circle.class.isSealed());
        System.out.println("Square.isSealed() = " + Square.class.isSealed());
        System.out.println("Triangle.isSealed() = " + Triangle.class.isSealed());
        System.out.println("--------------------------------------");
        // getPermittedSubclasses(): 获取 permits 子句允许的子类, 非密封类返回 null
        Class<?>[] permittedSubclasses = Shape.class.getPermittedSubclasses();
        System.out.println("Shape 允许的子类数量: " + permittedSubclasses.length);
        for (Class<?> clazz : permittedSubclasses) {
            System.out.println("Shape 允许的子类: " + clazz.getSimpleName());
        }
        System.out.println("Triangle 允许的子类: " + Arrays.toString(Triangle.class.getPermittedSubclasses()));
        System.out.println("Square 是非密封类, getPermittedSubclasses() = " + Arrays.toString(Square.class.getPermittedSubclasses()));
    }
}
