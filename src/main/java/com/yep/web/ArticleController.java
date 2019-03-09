package main.java.com.yep.web;



import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.yep.pojo.ArticleBean.RowsBean;
import main.java.com.yep.pojo.ArticleBean;
import main.java.com.yep.pojo.ResultJson;
import main.java.com.yep.service.ArticleService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/article")
public class ArticleController extends BaseController {
    
    @Resource(name="articleService")
    ArticleService articleService;
    
    @RequestMapping(value="/main")
    public String showArticleList(HttpServletRequest request, HttpServletResponse response){
        return "temp/article_main";
    }
    
    /*
     * 获取文章的相关信息
     * 
     * */
    /**
     * @param request
     * @param response
     */
    @RequestMapping(value="/info")
    public void getArticleInfo(HttpServletRequest request, HttpServletResponse response , 
            @RequestParam("articleCategoryId") int articleCategoryId){
        ArticleBean articleBean  = new ArticleBean();
        
        List<RowsBean> list = articleService.getAllArticleInfo();  
        
        articleBean.setTotal(list.size());
        
        articleBean.setRows(list);
        
        this.outJson1(response, articleBean);
    }
    @RequestMapping(value="/add")
    public String showArticlePage(HttpServletRequest request, HttpServletResponse response){
        String url = "https://www.baidu.com";
        return "temp/article_form" ;
        
    }
    
    @RequestMapping(value="/save")
    public void saveArticleEntity(@RequestParam(value = "pic", required = false) MultipartFile pic,HttpServletRequest request, HttpServletResponse response) throws ParseException{
       Map<String, Object> resultParam =  this.uploadImage(pic);
       
       RowsBean articleBean =  this.setArticleInfo(request, resultParam);
       
       articleService.saveArticle(articleBean);

       ResultJson result = new ResultJson();
       result.setResult(true);
       result.setResultMsg("ok!");
        this.outJson1(response, result);
    }
}
