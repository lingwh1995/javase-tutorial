package org.bluebridge.constructor.required_args_constructor_spring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author lingwh
 * @desc Redis存储Dao
 * @date 2025/11/10 11:41
 */
@Slf4j
@Repository
public class RedisStorageDao {

    public void save() {
        log.info("执行 redis 存储操作......");
    }
}
