package action.command.command_k;

/**
 * @author lingwh
 * @desc 减法命令
 * @date 2019/9/10 16:19
 */
public class SubstractCommand implements Command {

    /**
     * 持有具体进行减法计算的类的引用
     */
    private OperationApi operationApi;

    /**
     * 被减数
     */
    private int subNum;

    public SubstractCommand(OperationApi calculator, int subNum) {
        this.operationApi = calculator;
        this.subNum = subNum;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        operationApi.subStract(subNum);
    }

    /**
     * 撤销命令
     */
    @Override
    public void undo() {
        operationApi.add(subNum);
    }
}
