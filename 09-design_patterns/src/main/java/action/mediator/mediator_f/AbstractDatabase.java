package action.mediator.mediator_f;

/**
 * @author lingwh
 * @desc 抽象数据库
 * @date 2019/8/14 13:59
 */
public abstract class AbstractDatabase {

  /**
   * 添加数据
   *
   * @param data
   */
  public abstract void addData(String data);

  /**
   * 将添加的数据同步维护到其他数据库
   *
   * @param data
   */
  public abstract void add(String data);
}
