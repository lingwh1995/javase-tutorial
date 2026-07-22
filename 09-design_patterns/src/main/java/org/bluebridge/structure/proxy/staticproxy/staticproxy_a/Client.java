package org.bluebridge.structure.proxy.staticproxy.staticproxy_a;

/**
 * 客户端 - 测试静态代理
 *
 * @author lingwh
 * @date 2019/3/23 12:22
 */
public class Client {

    public static void main(String[] args) {
        RealStar realStar = new RealStar();
        ProxyStar proxyStar = new ProxyStar(realStar);

        proxyStar.confer();
        proxyStar.signContract();
        proxyStar.bookTicket();
        proxyStar.sing();
        proxyStar.clollectMoney();
    }
}
