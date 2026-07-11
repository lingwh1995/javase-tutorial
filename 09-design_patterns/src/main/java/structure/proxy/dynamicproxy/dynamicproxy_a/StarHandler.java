package structure.proxy.dynamicproxy.dynamicproxy_a;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 要代理的类对应的处理器
 *
 * @author lingwh
 * @date 2019/3/23 23:22
 */
public class StarHandler implements InvocationHandler {

    private Object target;

    public StarHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始调用" + method.getName() + "方法....");
        Object object = method.invoke(target, args);
        System.out.println("结束调用" + method.getName() + "方法....");
        return object;
    }
}
