package main.java.com.yep.web;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;



import javax.servlet.http.HttpServletResponse;

import main.java.com.yep.util.PropertiseDataUtils;
import main.java.com.yep.util.RandomUtils;
import main.java.com.yep.util.StringUtils;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;





@Controller

@RequestMapping(value="/resource/upload")
public class UploadContoller {
    /**
     * 上传图片
     * @param file
     * @param request
     * @param response
     * @return MultipartFile uploadFile,
     */
    @ResponseBody
    @RequestMapping(value="/images",method = RequestMethod.POST)
    public Map<String, Object> images (@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
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
    
    /**
     * 供读取服务器上传成功的图片显示到jsp上使用(多个图片循环调用)
     * @param request
     * @param response
     * @param imagePath  图片地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/viewImageToPage")
    public String viewImagesToPage(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "imagePath", required = false) String imagePath
                                  ) {
        System.out.println("imagePath:"+imagePath);
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            ips = new FileInputStream(new File(imagePath));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            // 读取文件流
            int i = 0;
            byte[] buffer = new byte[4096];
            while ((i = ips.read(buffer)) != -1) {
                // 写文件流
                out.write(buffer, 0, i);
            }
            out.flush();
            ips.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ips != null) {
                try {
                    ips.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    
    /**
     * 下载文件
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fileDownLoad",method = RequestMethod.GET)
    public void fileDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 下载本地文件
        String url = request.getParameter("url")+"";
        String fileName = request.getParameter("filename")+"";
        //如果是IE浏览器，则用URLEncode解析
        if(isMSBrowser(request)){
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }else{//如果是谷歌、火狐则解析为ISO-8859-1
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        System.out.println("filename:"+fileName+" url:"+url);
        // 读到流中
        InputStream inStream = new FileInputStream(url);// 文件的存放路径
//      fileName = url.substring(url.lastIndexOf("/")+1);
//      System.out.println("filename:"+fileName);
        // 设置输出的格式
        response.reset();
//      response.setContentType("bin");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isMSBrowser(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String agent = request.getHeader("USER-AGENT");  

        if (null != agent && -1 != agent.indexOf("MSIE")){  //IE
            return true;
            } //Firefox
        return false;
    }
    
    @RequestMapping(value="/fileUpload")
    public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, FileUploadException{
        String webPath=request.getServletContext().getRealPath("/") ; 
        File file = new File(webPath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        
        BufferedOutputStream bos =null;
        BufferedInputStream bis=null;
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = params.getFiles("model-file");//model-file为文件项的name值
        try {
                for (MultipartFile mf : files) {
                if(!mf.isEmpty()) {
                String originalFilename = mf.getOriginalFilename();
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
               
                String fileName=originalFilename.substring(originalFilename.lastIndexOf("/")+1);
                //为避免文件同名覆盖，给文件名加上时间戳
                int index = fileName.lastIndexOf(".");
                String firstName=fileName.substring(0, index);
                String lastName=fileName.substring(index);
                fileName=firstName+"_"+System.currentTimeMillis()+lastName;
                //读取文件
                bis=new BufferedInputStream (mf.getInputStream());
                //指定存储的路径
                bos=new BufferedOutputStream(new FileOutputStream
                (webPath+fileName));
                int len=0;
                byte[] buffer=new byte[10240];
                    while((len=bis.read(buffer))!=-1){
                    bos.write(buffer, 0, len);
                    }
                           //刷新此缓冲的输出流，保证数据全部都能写出
                       bos.flush();
                    }
                }
                if(bis!=null) {
                    bis.close();
                }
                if(bos!=null) {
                    bos.close();
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }
 }
