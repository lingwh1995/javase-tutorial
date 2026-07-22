package org.bluebridge.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据类型转换（数据类型映射）
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Slf4j
public class Case_05_DataTypeConvertTest {

    @Test
    public void testDataTypeConvert() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // 使用stream实现数据类型转换
        List<String> collectList = list.stream().map(i -> String.valueOf(i)).collect(Collectors.toList());
        log.debug("collectList: {}", collectList);
        // 使用stream集合lambda进行数据类型转换，此应用场景十分之经典
        collectList = list.stream().map(String::valueOf).collect(Collectors.toList());
        log.debug("collectList: {}", collectList);

        // 下面也是十分经典的应用场景，把泛型为对象的集合转换为泛型为String或者Integer的集合
        List<Person> personList = Arrays.asList(new Person("张三", 18), new Person("李四", 19),
                        new Person("王五", 20));
        List<String> nameList = personList.stream().map(Person::getName).collect(Collectors.toList());
        log.debug("nameList: {}", nameList);
        List<Integer> ageListInteger = personList.stream().map(Person::getAge).collect(Collectors.toList());
        log.debug("ageListInteger: {}", ageListInteger);
        List<String> ageListString = personList.stream().map(Person::getAge).map(String::valueOf)
                        .collect(Collectors.toList());
        log.debug("ageListString: {}", ageListString);
    }
}

@Data
@AllArgsConstructor
class Person {

    private String name;
    private Integer age;
}
