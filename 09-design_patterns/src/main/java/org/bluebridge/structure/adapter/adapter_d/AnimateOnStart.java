package org.bluebridge.structure.adapter.adapter_d;

/**
 * 动画开始监听器
 *
 * @author lingwh
 * @date 2026/7/22 10:42
 */
public class AnimateOnStart extends AbstractAnimateListenerAdpater {

    @Override
    public void onAnimateStart() {
        System.out.println("动画开始了....");
    }
}
