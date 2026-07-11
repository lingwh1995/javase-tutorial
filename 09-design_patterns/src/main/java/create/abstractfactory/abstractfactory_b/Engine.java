package create.abstractfactory.abstractfactory_b;

/**
 * @author lingwh
 * @desc 发动机接口
 * @date 2019/3/11 00:00
 */
public interface Engine {
    void run();
}

class LuxuryEngine implements Engine {

    @Override
    public void run() {
        System.out.println("高端发动机跑的快...");
    }
}

class LowEngine implements Engine {

    @Override
    public void run() {
        System.out.println("低端发动机跑的慢...");
    }
}
