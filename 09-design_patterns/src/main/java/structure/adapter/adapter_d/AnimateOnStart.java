package structure.adapter.adapter_d;

/**
 * 动画开始监听器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AnimateOnStart extends AbstractAnimateListenerAdpater {

    @Override
    public void onAnimateStart() {
        System.out.println("动画开始了....");
    }
}
