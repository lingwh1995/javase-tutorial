package org.bluebridge.structure.adapter.adapter_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    public static void main(String[] args) {
        /**
         * 直接对接口中的方法进行适配
         */
        AnimateListener animateListener = new AbstractAnimateListenerAdpater() {
            @Override
            public void onAnimateStart() {
                System.out.println("动画开始了....");
            }
        };
        /**
         * 加入了一个缓冲层AbstractAnimateListenerAdpater，对AbstractAnimateListenerAdpater中的方法进行适配
         */
        animateListener.onAnimateStart();
        AbstractAnimateListenerAdpater listener = new AbstractAnimateListenerAdpater() {
            @Override
            public void onAnimateStart() {
                System.out.println("动画开始了....");
            }
        };
        listener.onAnimateStart();
    }
}
