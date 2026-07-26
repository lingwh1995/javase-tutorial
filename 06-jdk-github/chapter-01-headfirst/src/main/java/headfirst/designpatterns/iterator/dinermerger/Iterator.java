package headfirst.designpatterns.iterator.dinermerger;

/**
 * 迭代器接口
 *
 * @author lingwh
 * @date 2023/12/7 11:22
 */
public interface Iterator {

    boolean hasNext();

    MenuItem next();
}
