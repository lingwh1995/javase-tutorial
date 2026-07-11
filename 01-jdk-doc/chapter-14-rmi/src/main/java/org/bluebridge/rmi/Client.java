package org.bluebridge.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2019/9/24 16:54
 */
public class Client {
    public static void main(String[] args)
            throws RemoteException, NotBoundException, MalformedURLException {
        MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1:8888/RemoteHello");
        System.out.println(service.sayHello());
        System.out.println(service.sayHello("我是message"));
    }
}
