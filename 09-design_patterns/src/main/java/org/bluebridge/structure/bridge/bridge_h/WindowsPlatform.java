package org.bluebridge.structure.bridge.bridge_h;

/**
 * Windows平台
 *
 * @author lingwh
 * @date 2026/7/113 8:57
 */
public class WindowsPlatform extends SystemPlatform {

    @Override
    void playVedio() {
        System.out.print("windows play ");
        videoFomat.decodingAndPlay();
    }
}
