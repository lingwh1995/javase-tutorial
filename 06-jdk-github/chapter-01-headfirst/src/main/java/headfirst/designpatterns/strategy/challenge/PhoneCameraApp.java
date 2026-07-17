package headfirst.designpatterns.strategy.challenge;

/**
 * 手机相机应用抽象类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class PhoneCameraApp {

    ShareStrategy shareStrategy;

    public void setShareStrategy(ShareStrategy shareStrategy) {
        this.shareStrategy = shareStrategy;
    }

    public void share() {
        shareStrategy.share();
    }

    public void take() {
        System.out.println("Taking the photo");
    }

    public void save() {
        System.out.println("Saving the photo");
    }

    public abstract void edit();
}
