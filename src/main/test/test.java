package main.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;

import com.sun.org.apache.bcel.internal.generic.NEW;



public class test {
    public static void main(String[] arg) throws InterruptedException{
        String str = "a\\b\\c";
        String[] list = str.split("\\\\");
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
            
        }
        String Str="";
        if(Str == ""){
            System.out.println("kong ");
        }
        while(true){
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         Thread.sleep(1500);
        System.out.println(df.format(new Date()));
        }
    }
}
