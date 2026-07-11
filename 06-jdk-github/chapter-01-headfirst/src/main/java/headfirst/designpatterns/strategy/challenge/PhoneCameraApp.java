package headfirst.designpatterns.strategy.challenge;

/**
 * @author lingwh
 * @desc 手机相机应用抽象类
 * @date 2026/7/9 00:00
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
