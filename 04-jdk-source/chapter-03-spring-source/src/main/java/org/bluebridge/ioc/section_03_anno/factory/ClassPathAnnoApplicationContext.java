package org.bluebridge.ioc.section_03_anno.factory;

import org.bluebridge.ioc.section_03_anno.parse.ConfigManagerAnno;
import org.bluebridge.ioc.section_03_anno.utils.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 类路径注解应用上下文
 *
 * @author lingwh
 * @date 2019/3/20 19:02
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
         * 2.获取这个包下所有类的 class 文件
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
