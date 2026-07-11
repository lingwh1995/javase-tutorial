package headfirst.designpatterns.combined.djview;

/**
 * @author lingwh
 * @desc DJ测试驱动类
 * @date 2026/7/9 00:00
 */
public class DJTestDrive {

    public static void main(String[] args) {
        BeatModelInterface model = new BeatModel();
        ControllerInterface controller = new BeatController(model);
    }
}
