package structure.composite.composite_b;

/**
 * 抽象组件
 *
 * @author ronin
 * @date 2019/3/23 11:17
 */
public interface Component {
    void operation();
}

/**
 * 叶子组件
 *
 * @author ronin
 * @date 2019/3/23 11:25
 */
interface Leaf extends Component {
    @Override
    void operation();
}

/**
 * 容器组件
 *
 * @author ronin
 * @date 2019/3/23 11:35
 */
interface Composite extends Component {
    void add(Component c);

    void remove(Component c);

    Component getChild(int index);

    void operation();
}
