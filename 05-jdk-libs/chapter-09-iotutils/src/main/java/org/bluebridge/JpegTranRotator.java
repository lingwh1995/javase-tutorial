package org.bluebridge;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * JPEG 无损旋转工具类
 *
 * @author lingwh
 * @date 2026/2/3 10:13
 */
public class JpegTranRotator {

    /**
     * 物理无损旋转 JPEG
     *
     * @param inputPath  源文件路径
     * @param outputPath 输出文件路径
     * @param angle      角度 (只能是 90, 180, 270)
     */
    public static void rotateLossless(String inputPath, String outputPath, int angle)
            throws Exception {
        // 1. 检查输入文件是否存在
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            throw new Exception("源文件不存在: " + inputPath);
        }

        // 2. 构造命令
        List<String> command = new ArrayList<>();
        // 根据系统选择命令名称
        String cmdName = System.getProperty("os.name").toLowerCase().contains("win")
                ? "D:\\software\\develop\\libjpeg-turbo-gcc64\\bin\\jpegtran.exe"
                : "jpegtran";

        command.add(cmdName);
        command.add("-rotate");
        command.add(String.valueOf(angle));
        command.add("-copy");
        command.add("all"); // 保留所有元数据
        command.add("-outfile");
        command.add(outputPath);
        command.add(inputPath);

        // 3. 执行进程
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true); // 合并错误流
        Process process = pb.start();

        // 4. 读取输出信息（用于调试）
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("jpegtran output: " + line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("无损旋转成功！输出至: " + outputPath);
        } else {
            throw new RuntimeException("jpegtran 执行失败，错误码: " + exitCode + ". 请确保系统已安装 jpegtran。");
        }
    }

    public static void main(String[] args) {
        try {
            String source = "d://a.jpeg";
            String target = "d://b.jpeg";
            // 注意：jpegtran 不支持在同一个文件上覆盖写入，建议先输出到临时文件
            rotateLossless(source, target, 90);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
