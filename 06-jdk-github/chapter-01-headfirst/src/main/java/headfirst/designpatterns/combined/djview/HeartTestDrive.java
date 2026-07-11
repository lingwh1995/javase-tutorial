package headfirst.designpatterns.combined.djview;

/**
 * @author lingwh
 * @desc 心跳测试驱动类
 * @date 2026/7/9 00:00
 */
public class HeartTestDrive {

    public static void main(String[] args) {
        HeartModel heartModel = new HeartModel();
        ControllerInterface model = new HeartController(heartModel);
    }
}
