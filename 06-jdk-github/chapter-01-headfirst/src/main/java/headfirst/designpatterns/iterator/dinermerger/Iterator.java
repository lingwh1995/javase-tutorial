package headfirst.designpatterns.iterator.dinermerger;

/**
 * 迭代器接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Iterator {

    boolean hasNext();

    MenuItem next();
}
