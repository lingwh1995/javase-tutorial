package action.iterator.iterator_c;

/**
 * @author lingwh
 * @desc 结构接口
 * @date 2026/7/9 00:00
 */
public interface Structure {
    String getName();

    void addElement(String string);

    Iterator iterator();
}
