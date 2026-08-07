package org.bluebridge.section_09_jdk9.unit_05_process;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Java9 进程 API 更新测试
 *
 * Java9 通过 java.lang.ProcessHandle 接口增强了进程管理能力:
 * 1. ProcessHandle.current(): 获取当前 Java 进程的句柄
 * 2. ProcessHandle.info(): 获取进程的详细信息(如 PID, 命令, 启动时间等)
 * 3. ProcessHandle.onExit(): 注册进程退出时的回调, 返回 CompletableFuture
 * 4. ProcessHandle.allProcesses(): 列出当前系统中所有可见的进程
 * 5. ProcessHandle.of(pid): 按 PID 查找指定进程, 返回 Optional<ProcessHandle>
 *
 * 演化历程: Process API 更新 JDK 9 STANDARD（JEP 102）
 *
 * @author lingwh
 * @date 2026/08/06 14:06
 */
public class ProcessAPITest {

    /**
     * 测试 ProcessHandle.current(): 获取当前 Java 进程信息
     */
    @Test
    public void testCurrentProcess() {
        // 获取当前进程的 ProcessHandle
        ProcessHandle current = ProcessHandle.current();
        System.out.println("当前进程 PID: " + current.pid());
        // 获取当前进程的详细信息
        ProcessHandle.Info info = current.info();
        System.out.println("进程命令: " + info.command().orElse("未知"));
        System.out.println("进程命令行参数: " + info.commandLine().orElse("未知"));
        System.out.println("进程启动时间: " + info.startInstant().map(Instant::toString).orElse("未知"));
        System.out.println("进程总运行时间: " + info.totalCpuDuration().map(Duration::toString).orElse("未知"));
        System.out.println("进程用户: " + info.user().orElse("未知"));
    }

    /**
     * 测试 ProcessHandle.info(): 获取子进程的详细信息
     */
    @Test
    public void testProcessInfo() {
        ProcessHandle current = ProcessHandle.current();
        // 获取当前进程的所有子进程
        current.children().forEach(child -> {
            System.out.println("子进程 PID: " + child.pid());
            ProcessHandle.Info info = child.info();
            System.out.println("  命令: " + info.command().orElse("未知"));
            System.out.println("  启动时间: " + info.startInstant().map(Instant::toString).orElse("未知"));
        });
    }

    /**
     * 测试 ProcessHandle.onExit(): 注册进程退出时的回调
     */
    @Test
    public void testOnExit() throws Exception {
        // 启动一个新进程来观察 onExit 机制
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "echo hello & exit");
        Process process = processBuilder.start();
        ProcessHandle handle = process.toHandle();

        // 注册进程退出回调
        CompletableFuture<ProcessHandle> exitFuture = handle.onExit();
        // 等待进程退出并获取结果
        ProcessHandle exitedHandle = exitFuture.get(5, TimeUnit.SECONDS);
        System.out.println("进程已退出, PID: " + exitedHandle.pid());
        System.out.println("进程是否存活: " + exitedHandle.isAlive());
    }

    /**
     * 测试 ProcessHandle.allProcesses(): 列出当前系统中所有可见进程
     */
    @Test
    public void testAllProcesses() {
        // 列出所有进程中前 5 个, 避免输出过多
        ProcessHandle.allProcesses()
                .limit(5)
                .forEach(process -> {
                    ProcessHandle.Info info = process.info();
                    System.out.println("PID: " + process.pid()
                            + ", 命令: " + info.command().orElse("未知")
                            + ", 用户: " + info.user().orElse("未知"));
                });
        // 统计系统中的进程总数
        long totalProcesses = ProcessHandle.allProcesses().count();
        System.out.println("当前系统进程总数: " + totalProcesses);
    }

    /**
     * 测试 ProcessHandle.of(pid): 按 PID 查找指定进程
     */
    @Test
    public void testFindProcessByPid() {
        // 获取当前进程的 PID
        long currentPid = ProcessHandle.current().pid();
        // 按 PID 查找当前进程
        Optional<ProcessHandle> found = ProcessHandle.of(currentPid);
        System.out.println("按 PID " + currentPid + " 查找进程: " + (found.isPresent() ? "找到" : "未找到"));
        found.ifPresent(handle -> {
            ProcessHandle.Info info = handle.info();
            System.out.println("找到的进程命令: " + info.command().orElse("未知"));
        });
        // 查找一个不存在的 PID, 验证返回 Optional.empty()
        Optional<ProcessHandle> notFound = ProcessHandle.of(-1);
        System.out.println("按 PID -1 查找进程: " + (notFound.isPresent() ? "找到" : "未找到(符合预期)"));
    }

    /**
     * 测试 ProcessHandle 的子进程与后代进程遍历
     */
    @Test
    public void testChildrenAndDescendants() {
        ProcessHandle current = ProcessHandle.current();
        // 获取直接子进程
        long childCount = current.children().count();
        System.out.println("当前进程的直接子进程数量: " + childCount);
        // 获取所有后代进程(包括子进程的子孙进程)
        long descendantCount = current.descendants().count();
        System.out.println("当前进程的所有后代进程数量: " + descendantCount);
        // 列出所有子进程的 PID 和命令
        String childrenInfo = current.children()
                .map(child -> {
                    String cmd = child.info().command().orElse("未知");
                    return child.pid() + "(" + cmd + ")";
                })
                .collect(Collectors.joining(", "));
        System.out.println("子进程列表: [" + (childrenInfo.isEmpty() ? "无" : childrenInfo) + "]");
    }
}