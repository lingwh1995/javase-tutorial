package headfirst.designpatterns.collections.iterator_builtin;

import java.util.Iterator;

/**
 * 菜单接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Menu {

    Iterator<String> createIterator();
}
