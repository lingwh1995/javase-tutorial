package action.iterator.iterator_d;

/**
 * @author lingwh
 * @desc 聚合抽象类
 * @date 2019/8/20 9:16
 */
public abstract class Aggregate {

    /**
     * 工厂方法，创建相应迭代器对象的接口
     *
     * @return 相应迭代器对象的接口
     */
    public abstract Iterator createIterator();
}
