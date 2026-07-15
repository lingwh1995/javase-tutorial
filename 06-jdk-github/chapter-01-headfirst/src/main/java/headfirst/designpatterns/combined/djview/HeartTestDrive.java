package headfirst.designpatterns.combined.djview;

/**
 * 心跳测试驱动类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HeartTestDrive {

    public static void main(String[] args) {
        HeartModel heartModel = new HeartModel();
        ControllerInterface model = new HeartController(heartModel);
    }
}
