package org.bluebridge.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程接口
 *
 * @author lingwh
 * @date 2019/9/24 16:38
 */
public interface MyRemote extends Remote {

    String sayHello() throws RemoteException;

    String sayHello(String message) throws RemoteException;
}
