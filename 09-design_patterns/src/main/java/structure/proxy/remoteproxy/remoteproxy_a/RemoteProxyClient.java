package structure.proxy.remoteproxy.remoteproxy_a;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author lingwh
 * @desc 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。
 * @date 2019/9/24 17:43
 */
public class RemoteProxyClient {
    public static void main(String args[]) {
        try {
            CompareHelper compareHelper;
            compareHelper = (CompareHelper) Naming.lookup("rmi://localhost:9090/StringCompareHelper");
            System.out.println(compareHelper.append("你好", "哈哈哈"));
            System.out.println(compareHelper.compare("abc", "ioi"));

            compareHelper = (CompareHelper) Naming.lookup("rmi://localhost:9090/IntegerCompareHelper");
            System.out.println(compareHelper.append(6379, 8080));
            System.out.println(compareHelper.compare(435, 666));
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
