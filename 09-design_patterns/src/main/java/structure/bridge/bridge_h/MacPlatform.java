package structure.bridge.bridge_h;

public class MacPlatform extends SystemPlatform {

    @Override
    void playVedio() {
        System.out.print("mac play ");
        videoFomat.decodingAndPlay();
    }
}
