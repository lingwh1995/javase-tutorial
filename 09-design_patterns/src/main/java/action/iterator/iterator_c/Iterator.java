package action.iterator.iterator_c;

/**
 * 迭代器接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Iterator {

    boolean hasNext();

    Object next();

    boolean remove();
}
