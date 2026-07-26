package org.bluebridge.structure.adapter.adapter_c;

/**
 * 动画监听器
 *
 * @author lingwh
 * @date 2026/7/22 11:27
 */
public interface AnimateListener {

    void onAnimateStart();

    void onAnimatePause();

    void onAnimateRepeat();

    void onAnimateEnd();
}
