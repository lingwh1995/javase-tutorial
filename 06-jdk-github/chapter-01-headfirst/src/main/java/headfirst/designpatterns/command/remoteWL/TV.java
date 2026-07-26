package headfirst.designpatterns.command.remoteWL;

/**
 * 电视
 *
 * @author lingwh
 * @date 2023/12/7 21:08
 */
public class TV {

    String location;
    int channel;

    public TV(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println("TV is on");
    }

    public void off() {
        System.out.println("TV is off");
    }

    public void setInputChannel() {
        this.channel = 3;
        System.out.println("Channel is set for VCR");
    }
}
