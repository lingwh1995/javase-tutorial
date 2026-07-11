package headfirst.designpatterns.proxy.gumball;

import java.rmi.*;

/**
 * @author lingwh
 * @desc 糖果机远程接口
 * @date 2026/7/9 00:00
 */
public interface GumballMachineRemote extends Remote {
    public int getCount() throws RemoteException;

    public String getLocation() throws RemoteException;

    public State getState() throws RemoteException;
}
