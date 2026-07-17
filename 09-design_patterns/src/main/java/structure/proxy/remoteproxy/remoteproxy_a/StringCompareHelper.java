package structure.proxy.remoteproxy.remoteproxy_a;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * CompareHellper接口的其中一种实现. 用于处理String类型.
 *
 * @author lingwh
 * @date 2019/9/24 17:41
 */
public class StringCompareHelper extends UnicastRemoteObject implements CompareHelper<String> {

    protected StringCompareHelper() throws RemoteException {}

    @Override
    public boolean compare(String object1, String object2) throws RemoteException {
        return object1.compareTo(object2) > 0;
    }

    @Override
    public String append(String object1, String object2) throws RemoteException {
        return object1 + object2;
    }
}
