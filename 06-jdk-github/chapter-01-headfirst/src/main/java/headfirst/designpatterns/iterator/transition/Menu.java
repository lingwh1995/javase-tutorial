package headfirst.designpatterns.iterator.transition;

import java.util.Iterator;

/**
 * 菜单接口
 *
 * @author lingwh
 * @date 2023/12/7 21:49
 */
public interface Menu {

    Iterator<?> createIterator();
}
