package headfirst.designpatterns.iterator.dinermergeri;

import java.util.Iterator;

/**
 * 菜单接口
 *
 * @author lingwh
 * @date 2023/12/7 10:25
 */
public interface Menu {

    Iterator<MenuItem> createIterator();
}
