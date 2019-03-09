package main.java.com.yep.web;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.WriterOutputStream;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import main.java.com.yep.pojo.ArticleBean;
import main.java.com.yep.pojo.ArticleBean.RowsBean;
import main.java.com.yep.pojo.ResultJson;
import main.java.com.yep.util.PropertiseDataUtils;
import main.java.com.yep.util.RandomUtils;
import main.java.com.yep.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
public class BaseController {
    
   
    /*
     * 输出json数据(包含信息)
     * */
    protected void outJson(HttpServletResponse response, String code, boolean flag, String msg, Object data) {
        try {
            response.setContentType("application/json;charset=utf-8");
            ResultJson result = new ResultJson();
            result.setResult(flag);
            result.setResultMsg(msg);
            result.setResultData(data);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSONObject.toJSON(result));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /*
    * 输出json数据（用于数据展示）
    * 
    * */
    protected void outJson1(HttpServletResponse response, Object data) {
        try {
            response.setContentType("application/json;charset=utf-8");
            
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSONObject.toJSON(data));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected Map<String, Object> uploadImage(MultipartFile file) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (file == null){
            System.out.println("未获得上传文件!");         
            return null;
        }  
        try{
             String filetype = file.getContentType().split("/")[0];
             System.out.println("filetype:"+filetype);
             String basePath = "";
             if("image".equals(filetype)){
                 basePath = PropertiseDataUtils.getValue("ys.uploading.url.image");
             }else if("file".equals(filetype)){
                 basePath = PropertiseDataUtils.getValue("ys.uploading.url.file");
             }
             if(basePath == null || "".equals(basePath)){
                 basePath = "d:/ys/static";  //与properties文件中ys.uploading.url相同，未读取到文件数据时为basePath赋默认值
             }
             System.out.println("filename:"+file.getOriginalFilename());
             String ext = StringUtils.getExt(file.getOriginalFilename());
             String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(RandomUtils.getRandom(6)).concat(".").concat(ext);
             StringBuilder sb = new StringBuilder();
             //拼接保存路径
             sb.append(basePath).append("/").append(fileName);
             
             String visitUrl = basePath.concat("/"+fileName);
             File f = new File(sb.toString());
             if(!f.exists()){
                 f.getParentFile().mkdirs();
             }
             OutputStream out = new FileOutputStream(f);
             FileCopyUtils.copy(file.getInputStream(), out);
             params.put("state", "SUCCESS");
             params.put("url", visitUrl);
             params.put("size", file.getSize());
             params.put("original", fileName);
             params.put("type", file.getContentType());
             params.put("filename", file.getOriginalFilename());
             System.out.println("url:"+visitUrl+" original:"+fileName+" filename:"+file.getOriginalFilename()+" type:"+file.getContentType());
        } catch (Exception e){
             params.put("state", "ERROR");
             e.printStackTrace();
        }
         return params;
        
    }
    
    
    protected ArticleBean.RowsBean setArticleInfo(HttpServletRequest request,Map<String, Object> resultParam) throws ParseException{
        
        RowsBean article = new RowsBean();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Enumeration<String>  paramNames =  request.getParameterNames();
        
        while (paramNames.hasMoreElements()) {
            String  name = paramNames.nextElement();

            String[] values =  request.getParameterValues(name);

            if(values!=null && values.length>0){
                StringBuilder builder = new StringBuilder(); 
                for (int i = 0; i < values.length; i++) {
                    builder.append(values[i]);
                }
                System.out.println(name+" : "+builder.toString());
                
             if(name.equals("editorValue")){
                 article.setArticlecontent(builder.toString());
             }else if(name.equals("article-title")){
                 article.setArticletitle(builder.toString());
             }else if(name.equals("article-description")){
                 article.setArticledescription(builder.toString());
             }else if(name.equals("keyword")){
                 article.setArticlekeyword(builder.toString());
             }else if(name.equals("category-id")){
                 article.setArticlecategoryid(Integer.valueOf(builder.toString()));
             }else if(name.equals("article-create-time")){
                 article.setArticledatetime(sdf.parse(builder.toString()));
             }else if(name.equals("article-author")){
                 article.setArticleauthor(builder.toString());
             }
            }
        }
        article.setArticleimageurl((String)resultParam.get("url"));
        return article;
        
    }
    
    
    
    @SuppressWarnings("rawtypes")
    protected HashMap queryTemplateFile(HttpServletRequest request,String path) {
        List<HashMap> folderNameList = new ArrayList<HashMap>();
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        File file = new File(path);
        File[] str = file.listFiles();
        if (str.length > 0) {
            for (int i = 0; i < str.length; i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                // 避免不为文件夹的文件显示
                if (str[i].isDirectory()) {
                    map.put("filename",str[i].getName());
                    map.put("filetype", "文件夹");
                    folderNameList.add(map);
                }else{
                    map.put("filename",str[i].getName());
                    map.put("filetype", "文件");
                    folderNameList.add(map);
                }
            }
            
        }
        resultMap.put("rows", folderNameList);
        return resultMap;
    }
    
    //显示文件内容
    protected String getFileContent(HttpServletRequest request,HttpServletResponse response,String path) throws IOException{

        String fileContent = "";
        try {
            File f = new File(path);
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
}
