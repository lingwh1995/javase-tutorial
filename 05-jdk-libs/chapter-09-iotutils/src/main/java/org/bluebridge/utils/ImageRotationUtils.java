package org.bluebridge.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 图片旋转工具类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ImageRotationUtils {

    /**
     * 通用的图片旋转方法
     *
     * @param imageData 图像数据
     * @param width 图像宽度
     * @param height 图像高度
     * @param degrees 旋转角度（90, 180, 270）
     * @return 旋转后的图像数据
     */
    public static byte[] rotateImage(byte[] imageData, int width, int height, int degrees) {
        switch (degrees) {
            case 90:
                return rotate90Clockwise(imageData, width, height);
            case 180:
                return rotate180(imageData, width, height);
            case 270:
                return rotate90CounterClockwise(imageData, width, height);
            default:
                throw new IllegalArgumentException("只支持90, 180, 270度旋转");
        }
    }

    /**
     * 顺时针旋转90度
     */
    private static byte[] rotate90Clockwise(byte[] imageData, int width, int height) {
        int newWidth = height;
        int newHeight = width;
        byte[] rotatedData = new byte[imageData.length];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalIndex = y * width + x;
                int newX = y;
                int newY = width - 1 - x;
                int rotatedIndex = newY * newWidth + newX;
                rotatedData[rotatedIndex] = imageData[originalIndex];
            }
        }
        return rotatedData;
    }

    /**
     * 逆时针旋转90度（等同于顺时针旋转270度）
     */
    private static byte[] rotate90CounterClockwise(byte[] imageData, int width, int height) {
        int newWidth = height;
        int newHeight = width;
        byte[] rotatedData = new byte[imageData.length];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalIndex = y * width + x;
                int newX = height - 1 - y;
                int newY = x;
                int rotatedIndex = newY * newWidth + newX;
                rotatedData[rotatedIndex] = imageData[originalIndex];
            }
        }
        return rotatedData;
    }

    /**
     * 旋转180度
     */
    private static byte[] rotate180(byte[] imageData, int width, int height) {
        byte[] rotatedData = new byte[imageData.length];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalIndex = y * width + x;
                int rotatedX = width - 1 - x;
                int rotatedY = height - 1 - y;
                int rotatedIndex = rotatedY * width + rotatedX;
                rotatedData[rotatedIndex] = imageData[originalIndex];
            }
        }
        return rotatedData;
    }

    /**
     * 旋转JPEG图片，保持原始尺寸
     *
     * @param jpegData 原始JPEG图片字节数组
     * @param degrees  旋转角度（90, 180, 270）
     * @return 旋转后的JPEG图片字节数组
     * @throws IOException 如果图片解码或编码失败
     */
    public static byte[] rotateJpegByDegrees(byte[] jpegData, int degrees) throws IOException {
        // 解码JPEG图片
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(jpegData));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage rotatedImage;

        switch (degrees) {
            case 90:
            case 270:
            case -90:
                // 90度或270度旋转时，需要先创建一个宽高互换的临时图像进行旋转，然后缩放回原始尺寸
                // 创建旋转后的图像（宽高互换）
                BufferedImage tempRotated = new BufferedImage(height, width, originalImage.getType());
                Graphics2D g2d = tempRotated.createGraphics();

                if (degrees == 90) {
                    g2d.translate(height / 2.0, width / 2.0);
                    g2d.rotate(Math.PI / 2);
                    g2d.translate(-width / 2.0, -height / 2.0);
                } else { // 270度或-90度
                    g2d.translate(height / 2.0, width / 2.0);
                    g2d.rotate(-Math.PI / 2);
                    g2d.translate(-width / 2.0, -height / 2.0);
                }

                g2d.drawImage(originalImage, 0, 0, null);
                g2d.dispose();

                // 将旋转后的图像缩放回原始尺寸
                rotatedImage = new BufferedImage(width, height, originalImage.getType());
                Graphics2D g2dFinal = rotatedImage.createGraphics();
                // 计算居中位置
                int x = (width - height) / 2; // 当原图的高变成新图的宽时
                int y = (height - width) / 2; // 当原图的宽变成新图的高时

                // 将旋转后的图像居中绘制到原始尺寸的图像中
                g2dFinal.drawImage(tempRotated, x, y, Math.min(width, height), Math.min(width, height), null);
                g2dFinal.dispose();
                break;
            case 180:
                // 180度旋转，直接在原始尺寸内旋转
                rotatedImage = new BufferedImage(width, height, originalImage.getType());
                Graphics2D g2d180 = rotatedImage.createGraphics();
                g2d180.rotate(Math.PI, width / 2.0, height / 2.0); // 以图像中心为原点旋转180度
                g2d180.drawImage(originalImage, 0, 0, null);
                g2d180.dispose();
                break;
            default:
                throw new IllegalArgumentException("只支持90, 180, 270度旋转");
        }

        // 编码为JPEG字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(rotatedImage, "jpeg", outputStream);
        return outputStream.toByteArray();
    }
}
