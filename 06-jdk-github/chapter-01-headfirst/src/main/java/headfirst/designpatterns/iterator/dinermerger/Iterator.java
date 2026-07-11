package headfirst.designpatterns.iterator.dinermerger;

/**
 * @author lingwh
 * @desc 迭代器接口
 * @date 2026/7/9 00:00
 */
public interface Iterator {
    boolean hasNext();

    MenuItem next();
}
