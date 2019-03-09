package main.test;

public class test {
    public static void main(String[] arg){
        String str = "a\\b\\c";
        String[] list = str.split("\\\\");
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
            
        }
        String Str="";
        if(Str == ""){
            System.out.println("kong ");
        }
    }
}
