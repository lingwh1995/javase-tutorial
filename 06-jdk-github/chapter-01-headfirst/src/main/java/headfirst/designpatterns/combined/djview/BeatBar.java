package headfirst.designpatterns.combined.djview;

import javax.swing.*;

/**
 * 节拍进度条
 *
 * @author lingwh
 * @date 2023/12/7 09:41
 */
public class BeatBar extends JProgressBar implements Runnable {

    private static final long serialVersionUID = 2L;
    JProgressBar progressBar;
    Thread thread;

    public BeatBar() {
        thread = new Thread(this);
        setMaximum(100);
        thread.start();
    }

    public void run() {
        for (;;) {
            int value = getValue();
            value = (int) (value * .75);
            setValue(value);
            repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
            ;
        }
    }
}
