package org.bluebridge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;

/**
 * 进度条案例 - 拷贝文件
 *
 * @author lingwh
 * @date 2026/1/31 17:33
 */
public class Case_05_JProgressBarDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Case_05_JProgressBarDemo()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new Case_05_JProgressBarDemo()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体的大小
        frame.setSize(500, 300);

        // 设置布局管理器
        frame.setLayout(null);

        // 进度条显示文件拷贝进度
        JProgressBar bar = new JProgressBar();
        bar.setMaximum(1000);
        bar.setBounds(20, 50, 300, 10);

        // 点击按钮开始拷贝文件
        JButton button = new JButton("点击开始");
        button.setBounds(20, 100, 100, 30);
        frame.add(button);

        button.addActionListener(
            e ->
                new Thread(
                    () -> {
                        // 注意，不能直接在这个线程里面处理，因为这个线程是负责图形界面的，得单独创建一个线程处理，否则图形界面会卡死
                        File file = new File("d://test//images.tar.gz"); // 这个源文件路径可以更改
                        try (FileInputStream in = new FileInputStream(file);
                            FileOutputStream out =
                                        new FileOutputStream("d://test//images.tar.gz_")) { // 这个目标文件路径可以更改
                            long size = file.length(), current = 0;
                            int len;
                            byte[] bytes = new byte[1024];
                            while ((len = in.read(bytes)) > 0) {
                                current += len;
                                // 每次拷贝都更新进度条
                                bar.setValue((int) (bar.getMaximum() * (double) current / size));
                                // 因为并不是每次更新值都会使得组件重新绘制，如果视觉上比较卡，可以每次拷贝都重新绘制组件
                                bar.repaint();
                                out.write(bytes, 0, len);
                            }
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    })
            .start());
        frame.add(bar);

        frame.setVisible(true);
    }
}
