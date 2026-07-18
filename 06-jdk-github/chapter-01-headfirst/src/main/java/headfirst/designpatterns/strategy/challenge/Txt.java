package headfirst.designpatterns.strategy.challenge;

/**
 * 短信分享
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class Txt implements ShareStrategy {

    public void share() {
        System.out.println("I'm txting the photo");
    }
}
