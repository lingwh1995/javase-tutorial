package org.bluebridge.action.mediator.mediator_f;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Es 数据库，维护了 Mysql 数据库的引用
 *
 * @author lingwh
 * @date 2019/8/14 14:00
 */
public class EsDatabase extends AbstractDatabase {

    private List<String> dataset = new CopyOnWriteArrayList<String>();

    /**
     * Mysql 数据库
     */
    private MysqlDatabase mysqlDatabase;

    public void setMysqlDatabase(MysqlDatabase mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    /**
     * 给 ES 数据库添加数据
     *
     * @param data
     */
    @Override
    public void addData(String data) {
        System.out.println("ES 添加数据：" + data);
        this.dataset.add(data);
    }

    /**
     * 把刚才添加的数据同步维护到 Mysql 数据库中
     *
     * @param data
     */
    @Override
    public void add(String data) {
        addData(data);
        // 维护同步到 MySQL 的同步作业
        this.mysqlDatabase.addData(data);
    }

    /**
     * ES 数据库独有的操作，统计总数据量
     */
    public void count() {
        int count = this.dataset.size();
        System.out.println("- Elasticsearch 统计，目前有 " + count + " 条数据，数据：" + this.dataset.toString());
    }
}
