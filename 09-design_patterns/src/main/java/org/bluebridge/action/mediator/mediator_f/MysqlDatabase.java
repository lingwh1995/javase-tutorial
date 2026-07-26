package org.bluebridge.action.mediator.mediator_f;

import java.util.ArrayList;
import java.util.List;

/**
 * Mysql 数据库，维护了 Redis 和 Es 数据库的引用
 *
 * @author lingwh
 * @date 2019/8/14 13:59
 */
public class MysqlDatabase extends AbstractDatabase {

    private List<String> dataset = new ArrayList<String>();

    /**
     * Redis 数据库
     */
    private RedisDatabase redisDatabase;

    /**
     * Es 数据库
     */
    private EsDatabase esDatabase;

    public void setRedisDatabase(RedisDatabase redisDatabase) {
        this.redisDatabase = redisDatabase;
    }

    public void setEsDatabase(EsDatabase esDatabase) {
        this.esDatabase = esDatabase;
    }

    /**
     * 给 Mysql 数据库添加数据
     *
     * @param data
     */
    @Override
    public void addData(String data) {
        System.out.println("Mysql 添加数据：" + data);
        this.dataset.add(data);
    }

    /**
     * 把刚才添加的数据同步维护到 Redis 数据库中 把刚才添加的数据同步维护到 Elasticsearch 中
     *
     * @param data
     */
    @Override
    public void add(String data) {
        addData(data);
        // 维护同步到 Redis 的同步作业
        this.redisDatabase.addData(data);
        // 维护同步到 Elasticsearch 的同步作业
        this.esDatabase.addData(data);
    }

    /**
     * Mysql 数据库独有的查询功能
     */
    public void select() {
        System.out.println("- Mysql 查询，数据：" + this.dataset.toString());
    }
}
