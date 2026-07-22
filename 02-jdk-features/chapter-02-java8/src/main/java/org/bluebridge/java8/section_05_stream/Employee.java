package org.bluebridge.java8.section_05_stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 员工实体类
 *
 * @author lingwh
 * @date 2026/6/22 15:10
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
