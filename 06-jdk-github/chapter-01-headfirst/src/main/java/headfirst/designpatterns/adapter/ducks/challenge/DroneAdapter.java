package headfirst.designpatterns.adapter.ducks.challenge;

import headfirst.designpatterns.adapter.ducks.Duck;

/**
 * 无人机适配器
 *
 * @author lingwh
 * @date 2023/12/7 21:39
 */
public class DroneAdapter implements Duck {

    Drone drone;

    public DroneAdapter(Drone drone) {
        this.drone = drone;
    }

    public void quack() {
        drone.beep();
    }

    public void fly() {
        drone.spin_rotors();
        drone.take_off();
    }
}
