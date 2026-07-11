package structure.proxy.staticproxy.staticproxy_a;

/**
 * @author lingwh
 * @desc 真实的歌手
 * @date 2019/3/23 00:00
 */
public class RealStar implements Star {

    @Override
    public void confer() {
        System.out.println("RealStar(周杰伦)......confer()");
    }

    @Override
    public void signContract() {
        System.out.println("RealStar(周杰伦)......signContract()");
    }

    @Override
    public void bookTicket() {
        System.out.println("RealStar(周杰伦)......bookTicket()");
    }

    @Override
    public void sing() {
        System.out.println("RealStar(周杰伦)......sing()");
    }

    @Override
    public void clollectMoney() {
        System.out.println("RealStar(周杰伦)......clollectMoney()");
    }
}
