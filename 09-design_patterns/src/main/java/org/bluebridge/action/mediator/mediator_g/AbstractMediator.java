package org.bluebridge.action.mediator.mediator_g;

/**
 * 抽象中介者
 *
 * @author lingwh
 * @date 2019/8/15 8:39
 */
public abstract class AbstractMediator {

    protected MysqlDatabase mysqlDatabase;
    protected RedisDatabase redisDatabase;
    protected EsDatabase esDatabase;

    public abstract void sync(String databaseName, String data);

    public void setMysqlDatabase(MysqlDatabase mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    public void setRedisDatabase(RedisDatabase redisDatabase) {
        this.redisDatabase = redisDatabase;
    }

    public void setEsDatabase(EsDatabase esDatabase) {
        this.esDatabase = esDatabase;
    }
}
