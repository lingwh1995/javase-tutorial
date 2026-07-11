package action.iterator.iterator_b;

/**
 * @author lingwh
 * @desc 学院接口
 * @date 2026/7/9 00:00
 */
public interface College {
    String getName();

    /**
     * 增加系的方法
     */
    void addDepartment(String name, String desc);

    /**
     * 返回一个迭代器,遍历
     */
    Iterator ceateIterator();
}
