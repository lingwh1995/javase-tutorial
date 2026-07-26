package org.bluebridge.structure.bridge.bridge_h;

/**
 * Windows 平台
 *
 * @author lingwh
 * @date 2026/7/22 13:45
 */
public class WindowsPlatform extends SystemPlatform {

    @Override
    void playVedio() {
        System.out.print("windows play ");
        videoFomat.decodingAndPlay();
    }
}
