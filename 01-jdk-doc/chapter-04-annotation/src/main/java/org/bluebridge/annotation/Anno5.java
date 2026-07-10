package org.bluebridge.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author lingwh
 * @desc 测试注解的作用目标限定以及保留策略限定
 *       源代码文件（SOURCE）：注解只在源代码中存在，当编译时就被忽略了
 *       字节码文件（CLASS）：注解在源代码中存在，然后编译时会把注解信息放到了class文件，但JVM在加载类时，会忽略注解！
 *       JVM中（RUNTIME）：注解在源代码、字节码文件中存在，并且在JVM加载类时，会把注解加载到JVM内存中（它是唯一可反射注解！）
 * @date 2026/7/9 00:00
 */
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface Anno5 {

}
