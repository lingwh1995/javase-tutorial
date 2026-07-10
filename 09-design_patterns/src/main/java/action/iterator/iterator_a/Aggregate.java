package action.iterator.iterator_a;

/**
 * @author lingwh
 * @desc 聚合对象的接口，定义创建相应迭代器对象的接口
 * @date 2019/8/20 9:07
 */
public abstract class Aggregate {

    /**
     * 工厂方法，创建相应迭代器对象的接口
     *
     * @return 相应迭代器对象的接口
     */
    public abstract Iterator createIterator();
}
