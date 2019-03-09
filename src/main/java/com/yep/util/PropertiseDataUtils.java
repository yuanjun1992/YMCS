package main.java.com.yep.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiseDataUtils {
    public static String getValue(String keyWord){
               Properties prop = new Properties();
              String value = null;
               try {
                  InputStream inputStream = PropertiseDataUtils.class.getResourceAsStream("/main/resources/ys.properties");
                     prop.load(inputStream);
                     value = prop.getProperty(keyWord);
                } catch (IOException e) {
                    e.printStackTrace();
               }
                 return value;
            }
}
