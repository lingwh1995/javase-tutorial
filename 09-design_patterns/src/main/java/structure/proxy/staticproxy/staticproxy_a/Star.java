package structure.proxy.staticproxy.staticproxy_a;

/**
 * @author lingwh
 * @desc 抽象角色:定义代理角色和真实角色的公共对外方法
 * @date 2019/3/23 00:00
 */
public interface Star {
    /**
     * 面谈
     */
    void confer();

    /**
     * 签合同
     */
    void signContract();

    /**
     * 订票
     */
    void bookTicket();

    /**
     * 唱歌
     */
    void sing();

    /**
     * 收尾款
     */
    void clollectMoney();
}
