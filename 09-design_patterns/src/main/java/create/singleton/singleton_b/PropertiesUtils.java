package create.singleton.singleton_b;

import java.io.IOException;
import java.util.Properties;

/**
 * Properties工具类
 *
 * @author lingwh
 * @date 2019/8/5 16:46
 */
public class PropertiesUtils {

    public static void read() {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesUtils.class.getResourceAsStream("single_thread.properties"));
            System.out.println("name:" + properties.get("name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
