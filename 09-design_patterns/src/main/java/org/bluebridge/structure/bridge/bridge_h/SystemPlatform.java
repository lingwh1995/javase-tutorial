package org.bluebridge.structure.bridge.bridge_h;

public abstract class SystemPlatform {

    VideoFomat videoFomat;

    /**
     * 视频播放
     */
    abstract void playVedio();

    public void setVideoFomat(VideoFomat videoFomat) {
        this.videoFomat = videoFomat;
    }
}
