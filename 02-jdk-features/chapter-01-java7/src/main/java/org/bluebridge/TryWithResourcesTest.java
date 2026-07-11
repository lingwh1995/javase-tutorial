package org.bluebridge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

/**
 * @author lingwh
 * @desc try-with-resources测试
 * @date 2026/7/9 00:00
 */
public class TryWithResourcesTest {
    @Test
    public void testTryWithResources() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
