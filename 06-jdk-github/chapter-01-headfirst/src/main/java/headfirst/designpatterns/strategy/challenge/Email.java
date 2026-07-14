package headfirst.designpatterns.strategy.challenge;

/**
 * 邮件分享
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Email implements ShareStrategy {

    public void share() {
        System.out.println("I'm emailing the photo");
    }
}
