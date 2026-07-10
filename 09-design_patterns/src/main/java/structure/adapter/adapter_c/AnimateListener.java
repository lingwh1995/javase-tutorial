package structure.adapter.adapter_c;

/**
 * @author lingwh
 * @desc 动画监听器
 * @date 2026/7/9 00:00
 */
public interface AnimateListener {

    void onAnimateStart();

    void onAnimatePause();

    void onAnimateRepeat();

    void onAnimateEnd();
}
