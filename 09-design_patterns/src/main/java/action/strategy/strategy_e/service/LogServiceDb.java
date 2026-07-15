package action.strategy.strategy_e.service;

/**
 * 数据库日志服务
 *
 * @author lingwh
 * @date 2019/8/5 9:26
 */
public class LogServiceDb implements ILogService {

    /**
     * 记录日志的方法
     */
    @Override
    public void log() {
        System.out.println("记录日志到数据库......");
    }
}
