package headfirst.designpatterns.collections.iterator_builtin;

import java.util.Iterator;

/**
 * 菜单接口
 *
 * @author lingwh
 * @date 2023/12/7 19:06
 */
public interface Menu {

    Iterator<String> createIterator();
}
