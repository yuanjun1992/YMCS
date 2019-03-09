package main.java.com.yep.web;


import main.java.com.yep.pojo.User;
import main.java.com.yep.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 










import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
 
@Controller
@RequestMapping(value = "/user")
public class UserController {
    /*
     * 框架测试使用
     * */
    @Resource(name = "userService")
    UserService userService;
    
 
    @RequestMapping(value = "/list")
    public ModelAndView list()
    {
        ModelAndView mv=new ModelAndView();
        List<User>  userList=userService.getUser();
        mv.addObject("userList",userList);
        mv.setViewName("/show");
        return mv;
    }
   
}
