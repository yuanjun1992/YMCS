package main.java.com.yep.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping(value="/view")
    public String showContent(HttpServletRequest request,HttpServletResponse response){
        return "/temp/layout-1";
    }
    
    @RequestMapping(value="/menu")
    public String showMenuContent(HttpServletRequest request,HttpServletResponse response){
        return "/temp/menu";
    }
}
