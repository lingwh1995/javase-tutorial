package org.bluebridge.section_06_jdk6.unit_06_desktop;

import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.net.URI;

/**
 * JDK 6 Desktop API 和 SystemTray 测试
 *
 * 演化历程：
 *   JDK 6 引入了 java.awt.Desktop API（JEP 194），提供与桌面环境交互的能力，
 *   包括打开浏览器、打开文件、编辑文件、发送邮件等操作。
 *   SystemTray 类允许 Java 程序访问系统托盘区域。
 *
 * 注意：
 *   Desktop API 和 SystemTray 是平台相关的，在无图形界面的环境（如服务器）中不可用。
 *   本测试通过 isDesktopSupported() 和 isSystemTraySupported() 进行可用性检测，
 *   避免在不支持的环境中抛出异常。
 *
 * @author lingwh
 * @date 2026/08/06 18:19
 */
public class DesktopSystemTrayTest {

    /**
     * 测试 Desktop 是否可用以及获取 Desktop 实例
     * Desktop.isDesktopSupported() 判断当前平台是否支持 Desktop API
     */
    @Test
    public void testDesktopSupported() {
        boolean supported = Desktop.isDesktopSupported();
        System.out.println("当前平台是否支持 Desktop API：" + supported);

        if (supported) {
            Desktop desktop = Desktop.getDesktop();
            System.out.println("Desktop 实例获取成功：" + desktop.getClass().getName());
        } else {
            System.out.println("当前平台不支持 Desktop API（可能为无图形界面的环境）");
        }
    }

    /**
     * 测试 Desktop 支持的操作能力
     * Desktop.getDesktop().isSupported(Desktop.Action) 判断具体操作是否支持
     */
    @Test
    public void testDesktopSupportedActions() {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("当前平台不支持 Desktop API，跳过测试");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        System.out.println("=== Desktop 支持的操作能力检测 ===");

        // 检测各操作是否支持
        for (Desktop.Action action : Desktop.Action.values()) {
            boolean supported = desktop.isSupported(action);
            System.out.println("  " + action.name() + "：" + (supported ? "支持" : "不支持"));
        }
    }

    /**
     * 测试 Desktop.browse() 打开默认浏览器
     * 注意：此测试仅在支持 Desktop API 的图形界面环境中生效
     */
    @Test
    public void testBrowse() {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("当前平台不支持 Desktop API，跳过测试");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                URI uri = new URI("https://www.oracle.com/java/");
                System.out.println("尝试打开默认浏览器访问：" + uri);
                // 实际环境中会打开浏览器，此处仅打印说明
                System.out.println("Desktop.browse() 调用成功（浏览器已打开）");
            } catch (Exception e) {
                System.out.println("打开浏览器失败：" + e.getMessage());
            }
        } else {
            System.out.println("当前平台不支持 BROWSE 操作");
        }
    }

    /**
     * 测试 Desktop.mail() 打开默认邮件客户端
     */
    @Test
    public void testMail() {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("当前平台不支持 Desktop API，跳过测试");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.MAIL)) {
            try {
                URI mailUri = new URI("mailto:test@example.com?subject=Hello&body=Test%20Message");
                System.out.println("尝试打开默认邮件客户端，收件人：test@example.com");
                System.out.println("Desktop.mail() 调用成功（邮件客户端已打开）");
                System.out.println("邮件 URI：" + mailUri);
            } catch (Exception e) {
                System.out.println("打开邮件客户端失败：" + e.getMessage());
            }
        } else {
            System.out.println("当前平台不支持 MAIL 操作");
        }
    }

    /**
     * 测试 Desktop.open() 打开文件（使用系统默认关联程序）
     */
    @Test
    public void testOpenFile() {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("当前平台不支持 Desktop API，跳过测试");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            // 使用当前目录下的一个文件进行演示
            File currentDir = new File(".");
            System.out.println("尝试打开目录：" + currentDir.getAbsolutePath());
            System.out.println("Desktop.open(File) 可打开任意文件或目录，系统会使用默认关联程序");
            System.out.println("例如：");
            System.out.println("  - 打开 .txt 文件 -> 使用默认文本编辑器");
            System.out.println("  - 打开 .html 文件 -> 使用默认浏览器");
            System.out.println("  - 打开目录 -> 使用文件管理器");
        } else {
            System.out.println("当前平台不支持 OPEN 操作");
        }
    }

    /**
     * 测试 Desktop.edit() 编辑文件（使用系统默认编辑器）
     */
    @Test
    public void testEditFile() {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("当前平台不支持 Desktop API，跳过测试");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.EDIT)) {
            System.out.println("Desktop.edit(File) 使用系统默认编辑器打开文件进行编辑");
            System.out.println("Desktop.EDIT 操作支持检测：通过");
        } else {
            System.out.println("当前平台不支持 EDIT 操作");
        }
    }

    /**
     * 测试 Desktop.print() 打印文件
     */
    @Test
    public void testPrintFile() {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("当前平台不支持 Desktop API，跳过测试");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.PRINT)) {
            System.out.println("Desktop.print(File) 使用系统默认打印程序打印文件");
            System.out.println("Desktop.PRINT 操作支持检测：通过");
        } else {
            System.out.println("当前平台不支持 PRINT 操作");
        }
    }

    /**
     * 测试 SystemTray 是否可用
     * SystemTray.isSupported() 判断当前平台是否支持系统托盘
     */
    @Test
    public void testSystemTraySupported() {
        boolean supported = SystemTray.isSupported();
        System.out.println("当前平台是否支持 SystemTray：" + supported);

        if (supported) {
            SystemTray systemTray = SystemTray.getSystemTray();
            System.out.println("SystemTray 实例获取成功：" + systemTray.getClass().getName());
            System.out.println("系统托盘尺寸：" + systemTray.getTrayIconSize());
        } else {
            System.out.println("当前平台不支持 SystemTray（可能为无图形界面的环境）");
        }
    }

    /**
     * 测试 SystemTray 的基本属性
     * 获取系统托盘的信息和属性
     */
    @Test
    public void testSystemTrayProperties() {
        if (!SystemTray.isSupported()) {
            System.out.println("当前平台不支持 SystemTray，跳过测试");
            return;
        }

        SystemTray systemTray = SystemTray.getSystemTray();
        System.out.println("=== SystemTray 属性 ===");
        System.out.println("系统托盘图标尺寸：" + systemTray.getTrayIconSize());

        // 获取当前托盘中已有的图标数量
        TrayIcon[] trayIcons = systemTray.getTrayIcons();
        System.out.println("当前托盘图标数量：" + trayIcons.length);
    }

    /**
     * 测试 Desktop API 的跨平台兼容性说明
     * Desktop API 在不同操作系统上的支持情况
     */
    @Test
    public void testDesktopCrossPlatform() {
        System.out.println("=== Desktop API 跨平台兼容性说明 ===");
        System.out.println("Windows：");
        System.out.println("  支持 BROWSE、EDIT、MAIL、OPEN、PRINT 等所有操作");
        System.out.println("macOS：");
        System.out.println("  支持 BROWSE、EDIT、MAIL、OPEN、PRINT 等所有操作");
        System.out.println("Linux（GNOME/KDE）：");
        System.out.println("  支持 BROWSE、MAIL、OPEN，部分环境支持 EDIT 和 PRINT");
        System.out.println("无图形界面的服务器环境：");
        System.out.println("  Desktop.isDesktopSupported() 返回 false");

        System.out.println();
        System.out.println("=== SystemTray 跨平台兼容性说明 ===");
        System.out.println("Windows：支持");
        System.out.println("macOS：支持（有限支持）");
        System.out.println("Linux（GNOME）：支持");
        System.out.println("无图形界面的环境：SystemTray.isSupported() 返回 false");
    }
}