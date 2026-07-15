package headfirst.designpatterns.combined.djview;

/**
 * 心跳模型接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface HeartModelInterface {

    int getHeartRate();

    void registerObserver(BeatObserver o);

    void removeObserver(BeatObserver o);

    void registerObserver(BPMObserver o);

    void removeObserver(BPMObserver o);
}
