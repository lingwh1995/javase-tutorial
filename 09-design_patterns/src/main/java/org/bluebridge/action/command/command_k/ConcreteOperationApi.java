package org.bluebridge.action.command.command_k;

/**
 * 操作接口实现类
 *
 * @author lingwh
 * @date 2019/9/10 16:11
 */
public class ConcreteOperationApi implements OperationApi {

    private int result;

    /**
     * 获取计算完成后的结果
     *
     * @return
     */
    @Override
    public int getResult() {
        return this.result;
    }

    /**
     * 设置计算开始时的初始值
     */
    @Override
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 执行加法
     *
     * @param num 被加数
     */
    @Override
    public void add(int num) {
        result += num;
    }

    /**
     * 执行减法
     *
     * @param num 被减数
     */
    @Override
    public void subStract(int num) {
        result -= num;
    }
}
