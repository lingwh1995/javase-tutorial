package action.strategy.strategy_h;

/**
 * @author lingwh
 * @desc 把日志记录到数据库
 * @date 2019/8/29 9:06
 */
public class FileLog extends LogStrategyTemplate {

    @Override
    public void doLog(String msg) {
        System.out.println("现在把 '" + msg + "' 记录到文件中");
    }
}
