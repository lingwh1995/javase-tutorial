package org.bluebridge.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author lingwh
 * @desc 远程接口实现
 * @date 2019/9/24 16:46
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public MyRemoteImpl() throws RemoteException {}

    @Override
    public String sayHello() throws RemoteException {
        return "hello";
    }

    @Override
    public String sayHello(String message) throws RemoteException {
        return message;
    }

    public static void main(String[] args) {
        try {
            MyRemoteImpl service = new MyRemoteImpl();
            LocateRegistry.createRegistry(8888);
            Naming.rebind("rmi://127.0.0.1:8888/RemoteHello", service);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
