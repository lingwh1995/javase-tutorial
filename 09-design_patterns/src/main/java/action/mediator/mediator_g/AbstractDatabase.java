package action.mediator.mediator_g;

/**
 * @author lingwh
 * @desc 抽象数据库
 * @date 2019/8/15 8:37
 */
public abstract class AbstractDatabase {

    public static final String MYSQL = "mysql";
    public static final String REDIS = "redis";
    public static final String ELASTICSEARCH = "elasticsearch";

    /**
     * 中介者
     */
    protected AbstractMediator mediator;

    public AbstractDatabase(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void addData(String data);

    public abstract void add(String data);
}
