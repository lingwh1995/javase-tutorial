package org.bluebridge.ioc.anno_one.factory;

import java.lang.annotation.Annotation;
import java.util.List;

import org.bluebridge.ioc.anno_one.parse.ConfigManagerAnno;
import org.bluebridge.ioc.anno_one.utils.ClassUtils;

/**
 * @author lingwh
 * @desc 类路径注解应用上下文
 * @date 2019/3/20 00:00
 */
public class ClassPathAnnoApplicationContext implements BeanFactoryAnno {

    /**
     * 创建一个新的实例 ClassPathAnnoApplicationContext.
     */
    public ClassPathAnnoApplicationContext() {
        super();
    }

    /**
     * 创建一个新的实例 ClassPathAnnoApplicationContext.
     */
    public ClassPathAnnoApplicationContext(String path) {
        /**
         * 1.根据配置文件获取要扫描的包的路径，并进行扫描包操作
         */
        String basePackage = ConfigManagerAnno.getXmlConfig(path);
        /**
         * 2.获取这个包下所有类的class文件
         */
        List<Class<? extends Object>> classes = ClassUtils.getClasses(basePackage);
        for (Class classs : classes) {
            Annotation[] annotations = classs.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                System.out.println(annotationType);
            }
        }
    }
}
