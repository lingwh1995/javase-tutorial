package action.iterator.iterator_c;

/**
 * 结构接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Structure {

    String getName();

    void addElement(String string);

    Iterator iterator();
}
