package headfirst.designpatterns.combined.djview;

/**
 * DJ测试驱动类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DJTestDrive {

    public static void main(String[] args) {
        BeatModelInterface model = new BeatModel();
        ControllerInterface controller = new BeatController(model);
    }
}
