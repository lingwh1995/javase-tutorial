package org.bluebridge.constructor.required_args_constructor_spring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author lingwh
 * @desc MongoDB存储Dao
 * @date 2025/11/10 11:48
 */
@Slf4j
@Repository
public class MongoDBStorageDao {

    public void save() {
        log.info("执行 mongodb 存储操作......");
    }
}
