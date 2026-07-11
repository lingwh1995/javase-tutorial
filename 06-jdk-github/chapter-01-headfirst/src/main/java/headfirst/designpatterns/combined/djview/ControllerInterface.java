package headfirst.designpatterns.combined.djview;

/**
 * @author lingwh
 * @desc 控制器接口
 * @date 2026/7/9 00:00
 */
public interface ControllerInterface {
    void start();

    void stop();

    void increaseBPM();

    void decreaseBPM();

    void setBPM(int bpm);
}
