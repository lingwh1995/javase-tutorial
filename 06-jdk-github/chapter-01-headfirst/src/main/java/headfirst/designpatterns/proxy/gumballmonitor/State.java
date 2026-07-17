package headfirst.designpatterns.proxy.gumballmonitor;

import java.io.*;

/**
 * 状态接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface State extends Serializable {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
