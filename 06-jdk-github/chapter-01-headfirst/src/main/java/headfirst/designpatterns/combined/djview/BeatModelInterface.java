package headfirst.designpatterns.combined.djview;

/**
 * @author lingwh
 * @desc 节拍模型接口
 * @date 2026/7/9 00:00
 */
public interface BeatModelInterface {

    void initialize();

    void on();

    void off();

    void setBPM(int bpm);

    int getBPM();

    void registerObserver(BeatObserver o);

    void removeObserver(BeatObserver o);

    void registerObserver(BPMObserver o);

    void removeObserver(BPMObserver o);
}
