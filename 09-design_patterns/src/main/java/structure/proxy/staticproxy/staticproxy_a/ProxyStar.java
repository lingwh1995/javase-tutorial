package structure.proxy.staticproxy.staticproxy_a;

/**
 * @author lingwh
 * @desc 歌手的代理人:除了不能唱歌，其他的什么事儿都可以做
 * @date 2019/3/23 00:00
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
