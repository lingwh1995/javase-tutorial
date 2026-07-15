package action.strategy.strategy_e.service;

/**
 * 文件日志服务
 *
 * @author lingwh
 * @date 2019/8/5 9:27
 */
public class LogServiceFile implements ILogService {

    /**
     * 记录日志的方法
     */
    @Override
    public void log() {
        System.out.println("记录日志到文件......");
    }
}
