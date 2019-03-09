package main.java.com.yep.util;

public class StringUtils {
    public static String getExt(String fileName){
        
        String ext = fileName.split("\\.")[1];
        return ext;
        
    }
   
}
