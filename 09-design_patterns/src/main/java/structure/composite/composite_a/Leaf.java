package structure.composite.composite_a;

/**
 * 叶子对象
 *
 * @author lingwh
 * @date 2019/8/22 11:08
 */
public class Leaf extends Component {

    /**
     * 示意方法，子组件对象可能有的功能方法
     */
    @Override
    public void someOperation() {
        System.out.println(this.getClass().getName());
    }
}
