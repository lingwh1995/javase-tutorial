package headfirst.designpatterns.strategy.challenge;

/**
 * 短信分享
 *
 * @author lingwh
 * @date 2023/12/7 08:37
 */
public class Txt implements ShareStrategy {

    @Override
    public void share() {
        System.out.println("I'm txting the photo");
    }
}
