package org.bluebridge.constructor.required_args_constructor_spring;

import org.bluebridge.constructor.required_args_constructor_spring.service.IStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @RequiredArgsConstructor用法
 *
 * 1. 基本用法
 *    使用@RequiredArgsConstructor添加有参构造方法，只针对被final修饰或者@NonNull修饰的属性生成构造函数
 * 2. 经典用法
 *    当在Spring Bean中注入多个依赖时，使用@RequiredArgsConstructor + final 替代@Autowired注解、@Resource注解，可以提高代码简介度
 *
 * @author lingwh
 * @date 2025/11/10 11:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RequiredArgsConstructorAnnoTest {

    @Autowired private IStorageService storageService;

    @Test
    public void testIoCAnno() {
        storageService.save();
    }
}
