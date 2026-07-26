package headfirst.designpatterns.strategy.challenge;

/**
 * 社交媒体分享
 *
 * @author lingwh
 * @date 2023/12/7 14:47
 */
public class Social implements ShareStrategy {

    @Override
    public void share() {
        System.out.println("I'm posting the photo on social media");
    }
}
