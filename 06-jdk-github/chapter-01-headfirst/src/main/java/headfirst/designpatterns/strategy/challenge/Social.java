package headfirst.designpatterns.strategy.challenge;

/**
 * @author lingwh
 * @desc 社交媒体分享
 * @date 2026/7/9 00:00
 */
public class Social implements ShareStrategy {
    public void share() {
        System.out.println("I'm posting the photo on social media");
    }
}
