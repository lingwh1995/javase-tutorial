package create.abstractfactory.abstractfactory_b;

/**
 * 发动机接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
