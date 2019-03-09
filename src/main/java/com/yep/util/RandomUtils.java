package main.java.com.yep.util;

public class RandomUtils {
    public static String getRandom(int num){
        int randomNum = (int)(Math.random()*Math.pow( 10, num));
        System.out.println(randomNum);
        return String.valueOf(randomNum);
        
    }
    
}

