package org.bluebridge.section_18_jdk18.unit_04_internet_address;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * JDK 18 网络地址 SPI (JEP 418) 测试
 *
 * JEP 418 引入了 java.net.spi.InetAddressResolver 和
 * java.net.spi.InetAddressResolverProvider SPI 接口,
 * 允许开发者定义自定义的互联网地址解析机制,
 * 替代默认的 InetAddress 解析行为。
 * 这对于需要自定义 DNS 解析逻辑的应用非常有用。
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class InternetAddressTest {

    /**
     * 测试 InetAddress.getByName 基本用法
     * 根据主机名获取 IP 地址, 是 InetAddress 最常用的方法
     */
    @Test
    public void testGetByName() throws UnknownHostException {
        // 根据主机名解析 IP 地址
        InetAddress address = InetAddress.getByName("localhost");
        System.out.println("主机名: " + address.getHostName());
        System.out.println("IP 地址: " + address.getHostAddress());
        System.out.println("规范主机名: " + address.getCanonicalHostName());
        System.out.println("是否为回环地址: " + address.isLoopbackAddress());

        // 解析常见域名
        InetAddress google = InetAddress.getByName("google.com");
        System.out.println("google.com 地址: " + google.getHostAddress());

        InetAddress ipAddress = InetAddress.getByName("127.0.0.1");
        System.out.println("127.0.0.1 解析: " + ipAddress.getHostName() + " / " + ipAddress.getHostAddress());
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 InetAddress.getAllByName 获取所有 IP 地址
     * 一个域名可能对应多个 IP 地址, 用于负载均衡和高可用
     */
    @Test
    public void testGetAllByName() throws UnknownHostException {
        // 获取域名对应的所有 IP 地址
        InetAddress[] addresses = InetAddress.getAllByName("localhost");
        System.out.println("localhost 的 IP 地址数量: " + addresses.length);
        for (int i = 0; i < addresses.length; i++) {
            System.out.println("IP 地址 [" + i + "]: " + addresses[i].getHostAddress());
        }

        // 打印所有地址
        System.out.println("所有地址: " + Arrays.toString(addresses));
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 InetAddress.getLocalHost 获取本地主机地址
     * 返回本地主机的 InetAddress 对象
     */
    @Test
    public void testGetLocalHost() throws UnknownHostException {
        // 获取本地主机地址
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("本地主机名: " + localHost.getHostName());
        System.out.println("本地 IP 地址: " + localHost.getHostAddress());
        System.out.println("规范主机名: " + localHost.getCanonicalHostName());
        System.out.println("是否为回环地址: " + localHost.isLoopbackAddress());
        System.out.println("是否为本地地址: " + localHost.isSiteLocalAddress());
        System.out.println("是否可达: " + isReachable(localHost));
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 InetAddress 的地址类型判断方法
     * InetAddress 提供了多种方法来判断地址类型
     */
    @Test
    public void testAddressTypeJudgment() throws UnknownHostException {
        // 测试回环地址
        InetAddress loopback = InetAddress.getByName("127.0.0.1");
        System.out.println("127.0.0.1 是回环地址: " + loopback.isLoopbackAddress());
        System.out.println("127.0.0.1 是本地地址: " + loopback.isSiteLocalAddress());
        System.out.println("127.0.0.1 是通配符地址: " + loopback.isAnyLocalAddress());

        // 测试 IPv4 和 IPv6
        System.out.println("127.0.0.1 是 IPv4: " + (loopback instanceof java.net.Inet4Address));
        System.out.println("127.0.0.1 是 IPv6: " + (loopback instanceof java.net.Inet6Address));

        // 获取回环地址 (JDK 18 推荐方式)
        InetAddress loopback2 = InetAddress.getLoopbackAddress();
        System.out.println("回环地址: " + loopback2.getHostAddress());

        // 测试地址字节数组
        byte[] addressBytes = loopback.getAddress();
        System.out.println("地址字节数组: " + Arrays.toString(addressBytes));
        System.out.println("地址字节数组长度: " + addressBytes.length + " (IPv4 为 4 字节)");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 InetAddress 的 reachability 检测
     * 检测主机是否可达, 超时时间为 3 秒
     */
    @Test
    public void testReachability() throws IOException {
        // 测试本地回环地址的可达性
        InetAddress localhost = InetAddress.getByName("127.0.0.1");
        boolean reachable = localhost.isReachable(3000);
        System.out.println("localhost 是否可达: " + reachable);

        // 测试本地主机可达性
        InetAddress localHost = InetAddress.getLocalHost();
        boolean localReachable = localHost.isReachable(3000);
        System.out.println("本地主机 " + localHost.getHostName() + " 是否可达: " + localReachable);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 InetSocketAddress 的基本用法
     * InetSocketAddress 是 InetAddress + 端口号的组合, 常用于网络编程
     */
    @Test
    public void testInetSocketAddress() throws UnknownHostException {
        // 创建 InetSocketAddress 的不同方式
        InetSocketAddress socketAddress1 = new InetSocketAddress(8080);
        System.out.println("通配符地址: " + socketAddress1.getAddress());
        System.out.println("端口: " + socketAddress1.getPort());
        System.out.println("是否未解析: " + socketAddress1.isUnresolved());

        // 通过主机名和端口创建
        InetSocketAddress socketAddress2 = new InetSocketAddress("localhost", 8080);
        System.out.println("主机名: " + socketAddress2.getHostName());
        System.out.println("IP 地址: " + socketAddress2.getAddress().getHostAddress());
        System.out.println("端口: " + socketAddress2.getPort());

        // 通过 InetAddress 和端口创建
        InetAddress address = InetAddress.getByName("127.0.0.1");
        InetSocketAddress socketAddress3 = new InetSocketAddress(address, 9090);
        System.out.println("地址: " + socketAddress3.getAddress().getHostAddress());
        System.out.println("端口: " + socketAddress3.getPort());
        System.out.println("字符串表示: " + socketAddress3.toString());
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 NetworkInterface 遍历网络接口
     * NetworkInterface 表示网络接口, 可用于获取主机的网络配置信息
     */
    @Test
    public void testNetworkInterface() throws IOException {
        // 获取本机所有网络接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        System.out.println("本机网络接口列表:");
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println("  - 接口名称: " + networkInterface.getName());
            System.out.println("    显示名称: " + networkInterface.getDisplayName());
            System.out.println("    是否运行: " + networkInterface.isUp());
            System.out.println("    是否回环: " + networkInterface.isLoopback());
            System.out.println("    是否虚拟: " + networkInterface.isVirtual());
            System.out.println("    MTU: " + networkInterface.getMTU());

            // 获取该接口的 IP 地址
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                System.out.println("    IP: " + inetAddress.getHostAddress() + " (" + inetAddress.getClass().getSimpleName() + ")");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 InetAddressResolver SPI 概念
     * JDK 18 引入的 InetAddressResolver 提供了自定义地址解析的 SPI 接口
     * 这里演示其概念和使用方式, 实际 SPI 实现需要通过 ServiceLoader 加载
     */
    @Test
    public void testInetAddressResolverConcept() throws UnknownHostException {
        // 使用 InetAddress.getByName 解析地址, 底层使用 InetAddressResolver
        InetAddress address = InetAddress.getByName("localhost");
        System.out.println("InetAddress.getByName 底层使用 InetAddressResolver 进行解析");
        System.out.println("解析结果 - 主机名: " + address.getHostName() + ", IP: " + address.getHostAddress());

        // 获取 InetAddress 的解析器 (JDK 18 新 API)
        // InetAddress 现在使用 InetAddressResolver 进行地址解析
        // 可以通过 InetAddressResolverProvider 自定义解析逻辑
        System.out.println("InetAddressResolver SPI 允许自定义 DNS 解析逻辑:");
        System.out.println("  1. 实现 InetAddressResolver 接口");
        System.out.println("  2. 实现 InetAddressResolverProvider 接口");
        System.out.println("  3. 在 META-INF/services 中注册 Provider");
        System.out.println("  4. 通过 ServiceLoader 加载自定义解析器");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 InetAddress 的序列化和比较
     * 验证 InetAddress 的 equals 和 hashCode 方法
     */
    @Test
    public void testAddressComparison() throws UnknownHostException {
        // 相同地址的 InetAddress 应该相等
        InetAddress address1 = InetAddress.getByName("127.0.0.1");
        InetAddress address2 = InetAddress.getByName("127.0.0.1");
        InetAddress address3 = InetAddress.getByName("localhost");

        System.out.println("address1: " + address1.getHostAddress());
        System.out.println("address2: " + address2.getHostAddress());
        System.out.println("address3: " + address3.getHostAddress());
        System.out.println("address1.equals(address2): " + address1.equals(address2));
        System.out.println("address1.equals(address3): " + address1.equals(address3));
        System.out.println("address1.hashCode(): " + address1.hashCode());
        System.out.println("address2.hashCode(): " + address2.hashCode());

        // 验证相等性
        assert address1.equals(address2) : "相同 IP 地址的 InetAddress 应该相等";
        System.out.println("--------------------------------------");
    }

    // 辅助方法: 检查地址是否可达
    private boolean isReachable(InetAddress address) {
        try {
            return address.isReachable(2000);
        } catch (IOException e) {
            return false;
        }
    }
}