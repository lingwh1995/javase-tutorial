package org.bluebridge.constructor.required_args_constructor_spring.service;

import lombok.RequiredArgsConstructor;
import org.bluebridge.constructor.required_args_constructor_spring.dao.*;
import org.springframework.stereotype.Service;

/**
 * 存储服务实现类
 *
 * @author lingwh
 * @date 2025/11/10 11:50
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements IStorageService {

    private final MongoDBStorageDao mongoDBStorageDao;
    private final OracleStorageDao oracleStorageDao;
    private final MysqlStorageDao mysqlStorageDao;
    private final PostgreSQStorageDao postgreSQStorageDao;
    private final RedisStorageDao redisStorageDao;
    private final SqlServerStorageDao sqlServerStorageDao;

    @Override
    public void save() {
        mongoDBStorageDao.save();
        oracleStorageDao.save();
        mysqlStorageDao.save();
        postgreSQStorageDao.save();
        redisStorageDao.save();
        sqlServerStorageDao.save();
    }
}
