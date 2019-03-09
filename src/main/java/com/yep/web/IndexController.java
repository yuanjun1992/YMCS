package main.java.com.yep.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @RequestMapping(value="/index")
    public String showIndexPage(HttpServletRequest request,HttpServletResponse response){
        return "index";
    }
}
