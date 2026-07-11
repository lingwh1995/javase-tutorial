package headfirst.designpatterns.proxy.gumball;

import java.io.*;

/**
 * @author lingwh
 * @desc 状态接口
 * @date 2026/7/9 00:00
 */
public interface State extends Serializable {
    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
