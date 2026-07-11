package headfirst.designpatterns.combined.djview;

import javax.swing.*;

/**
 * @author lingwh
 * @desc 节拍进度条
 * @date 2026/7/9 00:00
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
        for (; ; ) {
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
