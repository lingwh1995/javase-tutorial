package headfirst.designpatterns.combined.djview;

/**
 * 心跳测试驱动类
 *
 * @author lingwh
 * @date 2023/12/7 21:36
 */
public class HeartTestDrive {

    public static void main(String[] args) {
        HeartModel heartModel = new HeartModel();
        ControllerInterface model = new HeartController(heartModel);
    }
}
