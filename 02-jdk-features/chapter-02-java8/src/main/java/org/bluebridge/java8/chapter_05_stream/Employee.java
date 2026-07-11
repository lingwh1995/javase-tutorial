package org.bluebridge.java8.chapter_05_stream;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lingwh
 * @desc 员工实体类
 * @date 2026/7/9 00:00
 */
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@Data
public class Employee {
    private Long id;
    private String name;
    private Integer age;
    private BigDecimal salary;
}
