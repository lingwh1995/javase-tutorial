package headfirst.designpatterns.proxy.gumballmonitor;

/**
 * @author lingwh
 * @desc 糖果机监控器
 * @date 2026/7/9 00:00
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
