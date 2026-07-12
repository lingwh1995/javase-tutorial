package structure.adapter.adapter_d;

/**
 * 动画监听器接口
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
