package main.java.com.yep.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.yep.web.ModelController;

import org.apache.commons.lang.StringUtils;


public class FileUtil {

        /**
         * 文件格式默认为utf-8
         */
        public static final String URF8 = "UTF-8";

        /**
         * i/o进行读取htm模版
         * @param filePath 文件路径
         * @return 返回读出的内容
         */
        public static  String readFile(String filePath) {
            String fileContent = "";
            try {
                File f = new File(filePath);
                if (f.isFile() && f.exists()) {
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(f), "UTF-8");
                    BufferedReader reader = new BufferedReader(read);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        fileContent += line + "\n";
                    }
                    reader.close();
                    read.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fileContent;
        }

        /**
         * 读取文件夹
         * 
         * @param folderPath 文件夹路径　
         * @param list 返回包括文件名称与文件路径 Map返回结构 name:文件名称 path;文件路径
         * @return 返回包括文件名称与文件路径 Map返回结构 name:文件名称 path;文件路径
         */
        @SuppressWarnings("rawtypes")
        public static List readFolder(List<Map<String, String>> list,
                String folderPath) {
            if (list == null) {
                return null;
            }
            File file = new File(folderPath);
            File[] tempList = file.listFiles();
            if (tempList != null && tempList.length > 0) {
                for (File tmpFile : tempList) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("name", file.getName());
                    map.put("path", file.getPath());
                    list.add(map);
                    if (tmpFile.isDirectory()) {
                        readFolder(list, tmpFile.getPath()); // 递归删除
                    }
                }
            }
            return list;
        }

        /**
         * i/o写入文件
         * 
         * @param content 写入文件内容
         * @param writePath 要写入的文件名路径
         */
        public static void writeFile(String content, String writePath,
                String charCoder) {
            try {
                File file = new File(writePath);
                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(file), charCoder);
                BufferedWriter reader = new BufferedWriter(osw);
                reader.write(content);
                osw.flush();
                reader.close();
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 创建文件夹
         * 
         * @param path　路径
         */
        public static void createFolder(String path) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        /**
         * 删除指定文件夹 如果文件夹里面存在文件夹就进行递归，删除规则是从里面开始先删除
         * 
         * @param folderPath 文件夹路径
         */
        public static void delFolders(String folderPath) {
            // 删除完里面所有内容
            File file = new File(folderPath);
            // 如果路径本身就是一个文件就直接删除
            if (!file.isDirectory()) {
                file.delete();
                return;
            }
            // 检查文件夹里面是否存在文件夹
            File[] tempList = file.listFiles();
            if (tempList != null && tempList.length > 0) {
                for (File tmpFile : tempList) {
                    if (tmpFile.isDirectory()) {
                        delFolders(tmpFile.getPath()); // 递归删除
                    } else {
                        tmpFile.delete();
                    }
                }
            } else {
                file.delete();
            }
            delFolders(file.getPath());
        }

        /**
         * 删除文件
         * 
         * @param path 文件路径
         */
        public static void delFile(String path) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }

        /**
         * 编辑tmp文件
         * @param fileName 文件名
         * @param content 文件内容
         */
        public static void writeTmpFile(String fileName, String content) {
            // 获取程序当前路径
            String strDir = System.getProperty("user.dir");
            // 将路径分隔符更换
            String folderpath = strDir;// .replace("/", File.separatorChar);
            String filepath = folderpath + File.separatorChar + fileName + ".tmp";
            FileUtil.writeFile(content, filepath, "utf-8");
        }

        /**
         * 读取tmp文件
         * @param fileName 文件名
         * @return 返回文件内容
         */
        public static String readTmpFile(String fileName) {
            // 获取程序当前路径
            String strDir = System.getProperty("user.dir");
            // 将路径分隔符更换
            String folderpath = strDir;// .replace("/", File.separatorChar);
            String filepath = folderpath + File.separatorChar + fileName + ".tmp";
            return FileUtil.readFile(filepath);
        }
        
        /*
         * 路径处理 
         * 
         * */
        public static String dealPath(String filePath,HttpServletRequest request,int rank,HttpSession session){
            String path = request.getServletContext().getRealPath("/") + ModelController.templateUrl;
            if(filePath!=null){
                String[] fileArray = filePath.split("\\\\");
                if(rank <= fileArray.length){
                    String[] newFile = {};  
                    for(int i=0;i<rank-1;i++){
                        System.out.println(fileArray[i]);
                        newFile[i]=fileArray[i];
                    }
                    filePath=StringUtils.join(newFile, "\\");
                    session.removeValue("filename");
                    if(filePath != null && filePath != ""){
                        session.setAttribute("filename",filePath );
                    }
                   
                }
                path = path+File.separator+filePath;
            }
            return path;
        }
}

