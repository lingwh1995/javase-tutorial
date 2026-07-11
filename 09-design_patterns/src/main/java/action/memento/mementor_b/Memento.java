package action.memento.mementor_b;

/**
 * @author lingwh
 * @desc 备忘录对象:负责保存好状态变化的记录
 * @date 2026/7/9 00:00
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
