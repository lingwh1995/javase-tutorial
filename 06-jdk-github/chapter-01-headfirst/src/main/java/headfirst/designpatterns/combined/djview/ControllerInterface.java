package headfirst.designpatterns.combined.djview;

/**
 * 控制器接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface ControllerInterface {

    void start();

    void stop();

    void increaseBPM();

    void decreaseBPM();

    void setBPM(int bpm);
}
