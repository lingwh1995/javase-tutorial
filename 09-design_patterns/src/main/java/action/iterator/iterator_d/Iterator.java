package action.iterator.iterator_d;

/**
 * 迭代器接口
 *
 * @author lingwh
 * @date 2019/8/20 9:12
 */
public interface Iterator {

    public void first();

    public void next();

    public boolean isDone();

    public Object currentItem();
}
