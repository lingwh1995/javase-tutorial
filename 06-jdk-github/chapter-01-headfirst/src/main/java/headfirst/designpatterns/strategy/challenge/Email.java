package headfirst.designpatterns.strategy.challenge;

/**
 * @author lingwh
 * @desc 邮件分享
 * @date 2026/7/9 00:00
 */
public class Email implements ShareStrategy {
    public void share() {
        System.out.println("I'm emailing the photo");
    }
}
