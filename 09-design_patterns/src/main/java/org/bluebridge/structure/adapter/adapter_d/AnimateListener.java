package org.bluebridge.structure.adapter.adapter_d;

/**
 * 动画监听器接口
 *
 * @author lingwh
 * @date 2026/7/22 14:19
 */
public interface AnimateListener {

    void onAnimateStart();

    void onAnimatePause();

    void onAnimateRepeat();

    void onAnimateEnd();
}
