package main.java.com.yep.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import main.java.com.yep.pojo.ModelEntity;
import main.java.com.yep.util.FileUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/model")
public class ModelController extends BaseController {
    
    public static String templateUrl = "lang";
    /**
     * 模板获取
     * */
    @RequestMapping(value="/info")
    public void getModeInfo(HttpServletRequest request, HttpServletResponse response){
        List<ModelEntity> entities = new ArrayList<ModelEntity>();
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(1);
        modelEntity.setModel_name("123.html");
        entities.add(modelEntity);
        this.outJson1(response, entities);       
    }
      
    private void whlie() {
        // TODO Auto-generated method stub
        
    }

    @RequestMapping(value="/index")
    public String showModelFilePage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String name = request.getParameter("name");
        int rank = 0;
        if(request.getParameter("rank") != null){
           rank = Integer.valueOf(request.getParameter("rank"));
        }
        HttpSession session = request.getSession();
        String filePath = (String)session.getAttribute("filename");
        String path = FileUtil.dealPath(filePath, request, rank, session);
        String flag = request.getParameter("flag");
        
        if(flag != null){
            if(Integer.valueOf(flag) == 1){
                this.getFileContent(request, response, path+File.separator+request.getParameter("name"));
            } 
        }
        if(name!=null && (filePath !=null && filePath !="")){
            
            filePath  = filePath+File.separator+name;
            session.setAttribute("filename",filePath );
        }else if(name!=null){
            session.setAttribute("filename",name );
        }
        
        return "temp/model_index";
        
    }
    /*
     * 展示模板
     * */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/folderList")
    public void getFolderList(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        String filePath = (String)session.getAttribute("filename");
        
        if(filePath!=null && filePath.startsWith("\\")){
            filePath =  filePath.substring(1);
        }
        int rank = 0;
        if(request.getParameter("rank") != null){
           rank = Integer.valueOf(request.getParameter("rank"));
        }
        
        String path = FileUtil.dealPath(filePath, request, rank, session);
//        if(filePath!=null){
//            String[] fileArray = filePath.split("\\\\");
//            if(rank <= fileArray.length){
//                String[] newFile = {};  
//                for(int i=0;i<rank-1;i++){
//                    System.out.println(fileArray[i]);
//                    newFile[i]=fileArray[i];
//                }
//                filePath=StringUtils.join(newFile, "\\");
//                session.removeValue("filename");
//                if(filePath != null && filePath != ""){
//                    session.setAttribute("filename",filePath );
//                }
//               
//            }
//            path = path+File.separator+filePath;
//        }
        String flag = request.getParameter("flag");
        
        if(flag != null){
            if(Integer.valueOf(flag) == 1){
                this.getFileContent(request, response, path+File.separator+request.getParameter("name"));
            } 
        }else{
            HashMap folderNameList = this.queryTemplateFile(request, path);
            this.outJson1(response, folderNameList);
        }
        
    }
    
}
