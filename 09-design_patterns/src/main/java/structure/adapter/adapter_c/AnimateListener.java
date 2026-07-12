package structure.adapter.adapter_c;

/**
 * 动画监听器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface AnimateListener {

    void onAnimateStart();

    void onAnimatePause();

    void onAnimateRepeat();

    void onAnimateEnd();
}
