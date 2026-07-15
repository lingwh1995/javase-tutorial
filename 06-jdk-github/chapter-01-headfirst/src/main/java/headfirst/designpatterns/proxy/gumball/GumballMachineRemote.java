package headfirst.designpatterns.proxy.gumball;

import java.rmi.*;

/**
 * 糖果机远程接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface GumballMachineRemote extends Remote {

    public int getCount() throws RemoteException;

    public String getLocation() throws RemoteException;

    public State getState() throws RemoteException;
}
