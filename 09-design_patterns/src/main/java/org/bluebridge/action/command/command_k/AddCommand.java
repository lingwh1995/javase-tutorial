package org.bluebridge.action.command.command_k;

/**
 * 加法命令
 *
 * @author lingwh
 * @date 2019/9/10 16:15
 */
public class AddCommand implements Command {

    /**
     * 持有具体执行计算的类的引用
     */
    private OperationApi operationApi;

    /**
     * 被加数
     */
    private int addNum;

    public AddCommand(OperationApi operationApi, int addNum) {
        this.operationApi = operationApi;
        this.addNum = addNum;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        operationApi.add(addNum);
    }

    /**
     * 撤销命令
     */
    @Override
    public void undo() {
        operationApi.subStract(addNum);
    }
}
