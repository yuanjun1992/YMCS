package main.java.com.yep.web;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;

    /**
     * @classname: UEditorController
     * @description: 定义 UEditor控制层
     * @author: lixy
     */
@Controller
@Scope("prototype")
@RequestMapping("/uedit")
public class UEditorController {
     
        private static final Logger logger = LoggerFactory.getLogger(UEditorController.class);
     
        @RequestMapping(value="/config")
        public void config(HttpServletRequest request, HttpServletResponse response) {
     
            response.setContentType("application/json");
            String rootPath = request.getSession()
                    .getServletContext().getRealPath("/");
            System.out.println("进入UEditorController");
     
            try {
                String exec = new ActionEnter(request, rootPath).exec();
                PrintWriter writer = response.getWriter();
                writer.write(exec);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
     
        }
}

