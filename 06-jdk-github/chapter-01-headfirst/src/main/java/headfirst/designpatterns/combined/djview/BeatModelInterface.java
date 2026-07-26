package headfirst.designpatterns.combined.djview;

/**
 * 节拍模型接口
 *
 * @author lingwh
 * @date 2023/12/7 12:27
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
