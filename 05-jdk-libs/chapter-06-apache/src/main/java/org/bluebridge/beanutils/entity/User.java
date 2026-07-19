package org.bluebridge.beanutils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User实体
 *
 * @author lingwh
 * @date 2019/6/20 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String name;
    private Integer age;
    private String school;
}
