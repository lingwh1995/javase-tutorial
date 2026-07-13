package org.bluebridge.utils;

/**
 * BMP文件头工具类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BmpHeaderUtil {

    /**
     * 创建 BMP 文件头
     *
     * @param width 图像宽度
     * @param height 图像高度
     * @param imageDataSize 图像数据大小
     * @return BMP 文件头字节数组
     */
    public static byte[] createBmpHeader(int width, int height, int imageDataSize) {
        // BMP 文件头总大小 (54 字节)
        int fileSize = 54 + imageDataSize;

        byte[] header = new byte[54];

        // BMP 标识符 "BM"
        header[0] = 0x42; // 'B'
        header[1] = 0x4d; // 'M'

        // 文件大小 (4 字节)
        header[2] = (byte) (fileSize & 0xFF);
        header[3] = (byte) ((fileSize >> 8) & 0xFF);
        header[4] = (byte) ((fileSize >> 16) & 0xFF);
        header[5] = (byte) ((fileSize >> 24) & 0xFF);

        // 保留字段 (4 字节)
        header[6] = 0;
        header[7] = 0;
        header[8] = 0;
        header[9] = 0;

        int offset = 54 + 1024; // 文件头(54) + 调色板(256色×4字节)
        header[10] = (byte) (offset & 0xFF);
        header[11] = (byte) ((offset >> 8) & 0xFF);
        header[12] = (byte) ((offset >> 16) & 0xFF);
        header[13] = (byte) ((offset >> 24) & 0xFF);

        // BITMAPINFOHEADER 大小 (40 字节)
        header[14] = 40;
        header[15] = 0;
        header[16] = 0;
        header[17] = 0;

        // 图像宽度
        header[18] = (byte) (width & 0xFF);
        header[19] = (byte) ((width >> 8) & 0xFF);
        header[20] = (byte) ((width >> 16) & 0xFF);
        header[21] = (byte) ((width >> 24) & 0xFF);

        // 图像高度
        header[22] = (byte) (height & 0xFF);
        header[23] = (byte) ((height >> 8) & 0xFF);
        header[24] = (byte) ((height >> 16) & 0xFF);
        header[25] = (byte) ((height >> 24) & 0xFF);

        // 颜色平面数 (1)
        header[26] = 1;
        header[27] = 0;

        // 位深度 (8 位)
        header[28] = 8;
        header[29] = 0;

        // 压缩方式 (0 = 不压缩)
        header[30] = 0;
        header[31] = 0;
        header[32] = 0;
        header[33] = 0;

        // 图像数据大小
        header[34] = (byte) (imageDataSize & 0xFF);
        header[35] = (byte) ((imageDataSize >> 8) & 0xFF);
        header[36] = (byte) ((imageDataSize >> 16) & 0xFF);
        header[37] = (byte) ((imageDataSize >> 24) & 0xFF);

        // 水平分辨率 (pixels/meter)
        header[38] = 0x13; // 2835
        header[39] = 0x0B;
        header[40] = 0;
        header[41] = 0;

        // 垂直分辨率 (pixels/meter)
        header[42] = 0x13; // 2835
        header[43] = 0x0B;
        header[44] = 0;
        header[45] = 0;

        // 使用的颜色数 (0 = 使用最大颜色数)
        header[46] = 0;
        header[47] = 0;
        header[48] = 0;
        header[49] = 0;

        // 重要颜色数 (0 = 所有颜色都重要)
        header[50] = 0;
        header[51] = 0;
        header[52] = 0;
        header[53] = 0;

        return header;
    }

    /**
     * 创建 256 色调色板
     *
     * @return 调色板字节数组
     */
    public static byte[] createColorPalette() {
        byte[] palette = new byte[256 * 4]; // 256 个颜色，每个颜色 4 字节
        for (int i = 0; i < 256; i++) {
            palette[i * 4] = (byte) i; // 蓝色
            palette[i * 4 + 1] = (byte) i; // 绿色
            palette[i * 4 + 2] = (byte) i; // 红色
            palette[i * 4 + 3] = 0; // 保留
        }
        return palette;
    }

    /**
     * 拼接完整的 BMP 文件
     *
     * @param imageData 图像数据
     * @param width 图像宽度
     * @param height 图像高度
     * @return 完整的 BMP 文件字节数组
     */
    public static byte[] createBmpFile(byte[] imageData, int width, int height) {
        byte[] header = createBmpHeader(width, height, imageData.length);
        byte[] palette = createColorPalette();

        // 拼接文件头、调色板和图像数据
        byte[] bmpFile = new byte[header.length + palette.length + imageData.length];

        System.arraycopy(header, 0, bmpFile, 0, header.length);
        System.arraycopy(palette, 0, bmpFile, header.length, palette.length);
        System.arraycopy(imageData, 0, bmpFile, header.length + palette.length, imageData.length);

        return bmpFile;
    }
}
