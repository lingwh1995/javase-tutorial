package headfirst.designpatterns.strategy.challenge;

/**
 * 社交媒体分享
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Social implements ShareStrategy {

    public void share() {
        System.out.println("I'm posting the photo on social media");
    }
}
