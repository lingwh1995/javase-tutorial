package org.bluebridge.beanutils.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwh
 * @desc Person实体
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
