package headfirst.designpatterns.iterator.transition;

import java.util.Iterator;

/**
 * @author lingwh
 * @desc 菜单接口
 * @date 2026/7/9 00:00
 */
public interface Menu {
    Iterator<?> createIterator();
}
