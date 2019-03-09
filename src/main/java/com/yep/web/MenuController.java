package main.java.com.yep.web;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.yep.pojo.ColumnInfo;
import main.java.com.yep.service.MenuService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping(value="/column")
public class MenuController  extends BaseController{
    
    @Resource(name="menuService")
    MenuService menuService;
    
    /*
     *获取栏目信息
     * */
    @RequestMapping(value="/menu")
    public void getMenuInfo(HttpServletRequest request, HttpServletResponse response){
        Object array = menuService.selectMenuInfo();
        
        this.outJson1(response, array);
    }
    
    /*
     * 显示栏目界面
     * */
    @RequestMapping(value="/index")
    public String showColumnPage(HttpServletRequest request, HttpServletResponse response){
        return "temp/column";
        
    }
    
    /*
     * 添加栏目页面
     * */
    @RequestMapping(value="/add")
    public String columnAddPage(){
        return "temp/column_form" ;
        
    }
    
    /*
     * 保存栏目
     * */    
    @RequestMapping(value="/save")
    public void addColumnItem(ColumnInfo columnInfo ,HttpServletRequest request, HttpServletResponse response){
       
    }
 
}
