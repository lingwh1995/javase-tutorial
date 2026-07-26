package headfirst.designpatterns.decorator.io;

import java.io.*;

/**
 * 输入测试
 *
 * @author lingwh
 * @date 2023/12/7 15:08
 */
public class InputTest {

    public static void main(String[] args) throws IOException {
        int c;
        InputStream in = null;
        try {
            in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));

            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        System.out.println();
        try (InputStream in2 = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")))) {
            while ((c = in2.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
