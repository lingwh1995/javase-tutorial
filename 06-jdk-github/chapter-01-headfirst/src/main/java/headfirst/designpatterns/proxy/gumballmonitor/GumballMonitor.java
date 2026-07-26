package headfirst.designpatterns.proxy.gumballmonitor;

/**
 * 糖果机监控器
 *
 * @author lingwh
 * @date 2023/12/7 15:52
 */
public class GumballMonitor {

    GumballMachine machine;

    public GumballMonitor(GumballMachine machine) {
        this.machine = machine;
    }

    public void report() {
        System.out.println("Gumball Machine: " + machine.getLocation());
        System.out.println("Current inventory: " + machine.getCount() + " gumballs");
        System.out.println("Current state: " + machine.getState());
    }
}
