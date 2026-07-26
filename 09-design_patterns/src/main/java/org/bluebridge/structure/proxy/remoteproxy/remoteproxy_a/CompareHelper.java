package org.bluebridge.structure.proxy.remoteproxy.remoteproxy_a;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 定义一个远程接口，必须继承 Remote 接口，其中需要远程调用的方法须抛出 RemoteException 异常
 *
 * @author lingwh
 * @date 2019/9/24 17:40
 */
public interface CompareHelper<T> extends Remote {

    /**
     * 比较 object1 和 object2 的大小，如果 object1 大，那么返回 true 如果 object2 大，那么返回 false
     *
     * @param object1
     * @param object2
     * @return
     * @throws RemoteException
     */
    boolean compare(T object1, T object2) throws RemoteException;

    /**
     * 将 object2 的值连接到 object1 的后面
     *
     * @param object1
     * @param object2
     * @return
     * @throws RemoteException
     */
    T append(T object1, T object2) throws RemoteException;
}
