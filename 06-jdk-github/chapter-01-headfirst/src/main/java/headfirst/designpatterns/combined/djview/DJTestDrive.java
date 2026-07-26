package headfirst.designpatterns.combined.djview;

/**
 * DJ 测试驱动类
 *
 * @author lingwh
 * @date 2023/12/7 15:04
 */
public class DJTestDrive {

    public static void main(String[] args) {
        BeatModelInterface model = new BeatModel();
        ControllerInterface controller = new BeatController(model);
    }
}
