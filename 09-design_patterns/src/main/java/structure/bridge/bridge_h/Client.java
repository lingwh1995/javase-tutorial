package structure.bridge.bridge_h;

/**
 * 客户端 - 桥接模式
 *
 * @author lingwh
 * @date 2019/7/28 15:07
 */
public class Client {
    public static void main(String[] args) {
        // 使用Mac播放AVI
        VideoFomat aviFormat = new AVIFormat();
        SystemPlatform macPlatform = new MacPlatform();
        macPlatform.setVideoFomat(aviFormat);
        macPlatform.playVedio();

        // 使用windos 播放MPEG
        VideoFomat mpegFormat = new MPEGFormat();
        SystemPlatform windowsPlatform = new WindowsPlatform();
        windowsPlatform.setVideoFomat(mpegFormat);
        windowsPlatform.playVedio();
    }
}
