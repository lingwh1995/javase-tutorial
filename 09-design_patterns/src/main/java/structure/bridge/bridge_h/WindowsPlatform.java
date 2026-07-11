package structure.bridge.bridge_h;

public class WindowsPlatform extends SystemPlatform {
    @Override
    void playVedio() {
        System.out.print("windows play ");
        videoFomat.decodingAndPlay();
    }
}
