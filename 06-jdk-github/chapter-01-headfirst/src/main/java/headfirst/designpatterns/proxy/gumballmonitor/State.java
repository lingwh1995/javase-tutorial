package headfirst.designpatterns.proxy.gumballmonitor;

import java.io.*;

/**
 * 状态接口
 *
 * @author lingwh
 * @date 2023/12/7 14:44
 */
public interface State extends Serializable {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
