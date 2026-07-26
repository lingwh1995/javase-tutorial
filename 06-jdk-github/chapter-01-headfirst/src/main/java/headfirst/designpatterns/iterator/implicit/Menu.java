package headfirst.designpatterns.iterator.implicit;

import java.util.Iterator;

/**
 * 菜单接口
 *
 * @author lingwh
 * @date 2023/12/7 10:04
 */
public interface Menu {

    Iterator<MenuItem> createIterator();
}
