package headfirst.designpatterns.combined.djview;

/**
 * @author lingwh
 * @desc 心跳模型接口
 * @date 2026/7/9 00:00
 */
public interface HeartModelInterface {
    int getHeartRate();

    void registerObserver(BeatObserver o);

    void removeObserver(BeatObserver o);

    void registerObserver(BPMObserver o);

    void removeObserver(BPMObserver o);
}
