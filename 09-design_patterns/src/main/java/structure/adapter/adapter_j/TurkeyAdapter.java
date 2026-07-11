package structure.adapter.adapter_j;

/**
 * @author lingwh
 * @desc 使用火鸡冒充鸭子
 * @date 2019/9/11 8:58
 */
public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        // 火鸡连续飞行五次的距离相当于鸭子飞行一次的距离
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
