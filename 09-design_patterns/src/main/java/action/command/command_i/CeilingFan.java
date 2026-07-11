package action.command.command_i;

/**
 * @author lingwh
 * @desc 吊扇类
 * @date 2019/9/4 16:51
 */
public class CeilingFan {

    protected static final int HIGH = 3;
    protected static final int MEDIUM = 2;
    protected static final int LOW = 1;
    protected static final int OFF = 0;
    String location;
    int speed;

    public CeilingFan(String location) {
        this.location = location;
        speed = OFF;
    }

    /**
     * 设置高转速
     */
    public void high() {
        speed = HIGH;
    }

    /**
     * 设置中转速
     */
    public void medium() {
        speed = MEDIUM;
    }

    /**
     * 设置低转速
     */
    public void low() {
        speed = LOW;
    }

    /**
     * 关闭吊扇
     */
    public void off() {
        speed = OFF;
    }

    /**
     * 获取吊扇当前速度
     *
     * @return
     */
    public int getSpeed() {
        return speed;
    }
}
