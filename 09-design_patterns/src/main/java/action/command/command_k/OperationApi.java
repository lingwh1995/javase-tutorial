package action.command.command_k;

/**
 * 操作接口
 *
 * @author lingwh
 * @date 2019/9/10 16:08
 */
public interface OperationApi {

    /**
     * 获取计算完成后的结果
     *
     * @return
     */
    int getResult();

    /**
     * 设置计算开始时的初始值
     *
     * @param result 结果
     */
    void setResult(int result);

    /**
     * 执行加法
     *
     * @param num 被加数
     */
    void add(int num);

    /**
     * 执行减法
     *
     * @param num 被减数
     */
     void subStract(int num);
}
