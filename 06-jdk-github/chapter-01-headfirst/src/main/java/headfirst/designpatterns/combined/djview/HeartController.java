package headfirst.designpatterns.combined.djview;

/**
 * 心跳控制器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HeartController implements ControllerInterface {

    HeartModelInterface model;
    DJView view;

    public HeartController(HeartModelInterface model) {
        this.model = model;
        view = new DJView(this, new HeartAdapter(model));
        view.createView();
        view.createControls();
        view.disableStopMenuItem();
        view.disableStartMenuItem();
    }

    public void start() {
    }

    public void stop() {
    }

    public void increaseBPM() {
    }

    public void decreaseBPM() {
    }

    public void setBPM(int bpm) {
    }
}
