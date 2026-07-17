package org.bluebridge.constructor.required_args_constructor_spring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * SqlServer存储Dao
 *
 * @author lingwh
 * @date 2025/11/10 11:50
 */
@Slf4j
@Repository
public class SqlServerStorageDao {

    public void save() {
        log.info("执行 sqlserver 存储操作......");
    }
}
