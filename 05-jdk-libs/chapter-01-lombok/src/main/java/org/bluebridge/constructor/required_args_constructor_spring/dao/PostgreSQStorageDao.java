package org.bluebridge.constructor.required_args_constructor_spring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * PostgreSQ存储Dao
 *
 * @author lingwh
 * @date 2025/11/10 11:49
 */
@Slf4j
@Repository
public class PostgreSQStorageDao {

    public void save() {
        log.info("执行 postgresql 存储操作......");
    }
}
