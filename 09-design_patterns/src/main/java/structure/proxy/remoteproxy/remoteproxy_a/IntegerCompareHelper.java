package structure.proxy.remoteproxy.remoteproxy_a;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author lingwh
 * @desc CompareHellper接口的其中一种实现. 用于处理Integer类型.
 * @date 2019/9/24 17:40
 */
public class IntegerCompareHelper extends UnicastRemoteObject implements CompareHelper<Integer> {
    protected IntegerCompareHelper() throws RemoteException {}

    @Override
    public boolean compare(Integer object1, Integer object2) throws RemoteException {
        return object1 - object2 > 0;
    }

    @Override
    public Integer append(Integer object1, Integer object2) throws RemoteException {
        return Integer.valueOf(object1.toString() + object2.toString());
    }
}
