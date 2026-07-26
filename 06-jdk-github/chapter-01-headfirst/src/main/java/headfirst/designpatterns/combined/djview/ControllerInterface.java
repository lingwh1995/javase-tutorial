package headfirst.designpatterns.combined.djview;

/**
 * 控制器接口
 *
 * @author lingwh
 * @date 2023/12/7 14:51
 */
public interface ControllerInterface {

    void start();

    void stop();

    void increaseBPM();

    void decreaseBPM();

    void setBPM(int bpm);
}
