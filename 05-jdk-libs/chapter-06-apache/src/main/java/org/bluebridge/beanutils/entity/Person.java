package org.bluebridge.beanutils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Person 实体
 *
 * @author lingwh
 * @date 2019/6/20 15:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String userName;
    private String password;
    private Date hireDate;
}
