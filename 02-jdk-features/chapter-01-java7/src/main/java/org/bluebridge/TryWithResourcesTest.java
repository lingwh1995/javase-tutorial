package org.bluebridge;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * try-with-resources测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
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
