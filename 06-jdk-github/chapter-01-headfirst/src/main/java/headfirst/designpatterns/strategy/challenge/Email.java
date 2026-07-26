package headfirst.designpatterns.strategy.challenge;

/**
 * 邮件分享
 *
 * @author lingwh
 * @date 2023/12/7 16:01
 */
public class Email implements ShareStrategy {

    @Override
    public void share() {
        System.out.println("I'm emailing the photo");
    }
}
