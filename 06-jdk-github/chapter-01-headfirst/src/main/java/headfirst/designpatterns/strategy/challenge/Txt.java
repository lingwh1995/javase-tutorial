package headfirst.designpatterns.strategy.challenge;

/**
 * @author lingwh
 * @desc 短信分享
 * @date 2026/7/9 00:00
 */
public class Txt implements ShareStrategy {
    public void share() {
        System.out.println("I'm txting the photo");
    }
}
