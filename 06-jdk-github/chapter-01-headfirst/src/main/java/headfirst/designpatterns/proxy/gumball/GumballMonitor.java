package headfirst.designpatterns.proxy.gumball;

import java.rmi.*;

/**
 * 糖果机监控器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class GumballMonitor {

    GumballMachineRemote machine;

    public GumballMonitor(GumballMachineRemote machine) {
        this.machine = machine;
    }

    public void report() {
        try {
            System.out.println("Gumball Machine: " + machine.getLocation());
            System.out.println("Current inventory: " + machine.getCount() + " gumballs");
            System.out.println("Current state: " + machine.getState());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
