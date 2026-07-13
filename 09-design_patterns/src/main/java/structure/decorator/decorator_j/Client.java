package structure.decorator.decorator_j;

import org.junit.Test;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    @Test
    public void fun() {
        FileInputStream fileInputStream = new FileInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        bufferedInputStream.read();
    }
}
