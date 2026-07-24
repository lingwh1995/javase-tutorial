package org.bluebridge.java11;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * 流标记器测试
 *
 * @author lingwh
 * @date 2026/4/21 10:30
 */
public class StreamTokenizerTest {

    public static void main(String[] args) {
        StreamTokenizer streamTokenizer = null;
        String src = "The quick brown fox jumped over the lazy dog";
        StringReader reader = new StringReader(src);
        int wordCount = 0;
        try {
            streamTokenizer = new StreamTokenizer(reader);
            while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                    wordCount++;
                }
            }
            System.out.println("Number of words in filereaderandwriter: " + wordCount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
