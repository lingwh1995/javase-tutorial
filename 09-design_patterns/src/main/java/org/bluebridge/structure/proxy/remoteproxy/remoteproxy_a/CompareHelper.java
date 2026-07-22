package org.bluebridge.structure.proxy.remoteproxy.remoteproxy_a;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 定义一个远程接口，必须继承Remote接口，其中需要远程调用的方法须抛出RemoteException异常
 *
 * @author lingwh
 * @date 2019/9/24 17:40
 */
public interface CompareHelper<T> extends Remote {

    /**
     * 比较 object1 和 object2 的大小, 如果object1大, 那么返回true 如果object2大, 那么返回false
     *
     * @param object1
     * @param object2
     * @return
     * @throws RemoteException
     */
    boolean compare(T object1, T object2) throws RemoteException;

    /**
     * 将object2的值连接到object1的后面
     *
     * @param object1
     * @param object2
     * @return
     * @throws RemoteException
     */
    T append(T object1, T object2) throws RemoteException;
}
