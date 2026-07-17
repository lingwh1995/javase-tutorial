package structure.proxy.staticproxy.staticproxy_a;

/**
 * 歌手的代理人:除了不能唱歌，其他的什么事儿都可以做
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ProxyStar implements Star {

    private RealStar realStar;

    public ProxyStar(RealStar realStar) {
        super();
        this.realStar = realStar;
    }

    @Override
    public void confer() {
        System.out.println("ProxyStar(周杰伦经纪人)......confer()");
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar(周杰伦经纪人)......signContract()");
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar(周杰伦经纪人)......bookTicket()");
    }

    @Override
    public void sing() {
        realStar.sing();
    }

    @Override
    public void clollectMoney() {
        System.out.println("ProxyStar(周杰伦经纪人)......clollectMoney()");
    }
}
