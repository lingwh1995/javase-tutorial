package headfirst.designpatterns.proxy.gumballmonitor;

/**
 * @author lingwh
 * @desc 售罄状态
 * @date 2026/7/9 00:00
 */
public class SoldOutState implements State {
    private static final long serialVersionUID = 2L;
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("You can't insert a quarter, the machine is sold out");
    }

    public void ejectQuarter() {
        System.out.println("You can't eject, you haven't inserted a quarter yet");
    }

    public void turnCrank() {
        System.out.println("You turned, but there are no gumballs");
    }

    public void dispense() {
        System.out.println("No gumball dispensed");
    }

    public String toString() {
        return "sold out";
    }
}
