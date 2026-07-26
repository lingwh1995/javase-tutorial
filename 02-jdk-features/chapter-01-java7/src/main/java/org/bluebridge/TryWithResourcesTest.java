package org.bluebridge;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * try-with-resources 测试
 *
 * @author lingwh
 * @date 2025/6/9 09:17
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
