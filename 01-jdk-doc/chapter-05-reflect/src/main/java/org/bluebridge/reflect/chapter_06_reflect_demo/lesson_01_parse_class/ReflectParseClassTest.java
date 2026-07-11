package org.bluebridge.reflect.chapter_06_reflect_demo.lesson_01_parse_class;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author lingwh
 * @desc 反射解析类
 * @date 2019/10/16 11:41
 */
public class ReflectParseClassTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("org.bluebridge.reflect.chapter_06_reflect_demo.lesson_01_parse_class.Person");
        // 类
        System.out.println("类名简称:" + clazz.getSimpleName());
        System.out.println("类上的注解:");
        Annotation[] classAnnotations = clazz.getAnnotations();
        for (Annotation classAnnotation : classAnnotations) {
            String aannotationInfo = filterAnnotation(classAnnotation);
            System.out.println(aannotationInfo);
        }
        System.out.println("-----------------------------------------");

        // 字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            for (Annotation fieldAnnotation : fieldAnnotations) {
                String aannotationInfo = filterAnnotation(fieldAnnotation);
                System.out.println(aannotationInfo);
            }
            StringBuilder fieldInfobulider = new StringBuilder();
            fieldInfobulider.append(Modifier.toString(field.getModifiers()) + " ");
            fieldInfobulider.append(field.getType().getSimpleName() + " ");
            fieldInfobulider.append(field.getName() + " ");
            System.out.println(fieldInfobulider);
        }

        System.out.println("-----------------------------------------");
        // 方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] methodAnnotations = method.getAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                String aannotationInfo = filterAnnotation(methodAnnotation);
                System.out.println(aannotationInfo);
            }
            StringBuilder methodInfobulider = new StringBuilder();
            methodInfobulider.append(Modifier.toString(method.getModifiers()) + " ");
            methodInfobulider.append(method.getReturnType().getSimpleName() + " ");
            methodInfobulider.append(method.getName() + "(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0, length = parameterTypes.length; i < length; i++) {
                methodInfobulider.append(parameterTypes[i].getSimpleName());
                methodInfobulider.append(" param" + i);
                if (i < length - 1) {
                    methodInfobulider.append(",");
                }
            }
            methodInfobulider.append("){}");
            System.out.println(methodInfobulider);
        }
    }

    /**
     * 对注解做过滤，将 @RequestMapping(headers=[], value=[wsmbDeleted], produces=[], method=[], params=[], consumes=[])
     * 转换为:@RequestMapping(value=[wsmbDeleted])
     *
     * @param annotation 注解对象，包括字段、类、方法上的注解
     */
    private static String filterAnnotation(Annotation annotation) {
        // 获取注解简称，如:RequestMapping
        String annotationSimpleName = annotation.annotationType().getSimpleName();
        // 获取注解全称，如:
        // @org.springframework.web.bind.annotation.RequestMapping(headers=[], value=[jzpt/xxfb],
        // produces=[], method=[], params=[], consumes=[])
        String annotationFullName = annotation.toString();
        // 过滤注解全称中无用部分，生成的心字符串如:
        // @RequestMapping(headers=[], value=[wsmbDeleted], produces=[], method=[], params=[],
        // consumes=[])
        String originalAnnotationName = annotationFullName.substring(annotationFullName.indexOf(annotationFullName));
        // 将上面的字符串做进一步处理,生成字符串如:
        // headers=[], value=[wsmbDeleted], produces=[], method=[], params=[], consumes=[]
        // 再将这个字符串转换为字符串数组
        String[] annotationElements = originalAnnotationName.replaceAll(annotationSimpleName, "")
                .replace("(", "").replace(")", "").split(",");
        StringBuilder annotationInfobuilder = new StringBuilder();
        for (int i = 0, length = annotationElements.length; i < length; i++) {
            annotationInfobuilder.append("@");
            annotationInfobuilder.append(annotationSimpleName);
            annotationInfobuilder.append("(");
            annotationInfobuilder.append(annotationElements[i]);
            annotationInfobuilder.append(",");
        }
        return annotationInfobuilder.append(")").toString().replace(",)", ")");
    }
}
