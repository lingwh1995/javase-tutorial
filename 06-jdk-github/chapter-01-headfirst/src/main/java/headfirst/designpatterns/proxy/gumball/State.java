package headfirst.designpatterns.proxy.gumball;

import java.io.*;

/**
 * 状态接口
 *
 * @author lingwh
 * @date 2023/12/7 17:00
 */
public interface State extends Serializable {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
