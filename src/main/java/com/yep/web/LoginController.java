package main.java.com.yep.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="/login")
public class LoginController {
    
    @RequestMapping(value="/check")
    @ResponseBody
    public String login(@RequestParam("username") String username, 
            @RequestParam("password") String password,@RequestParam("verify") String verify,HttpServletRequest request ){
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);        
        try {
            //执行认证操作. 
            subject.login(token);
            if(!verify.equalsIgnoreCase(session.getAttribute("rand_code").toString())){
                return "{\"code\":0,\"msg\":\"验证码错误！\"}";
            }
        }catch (AuthenticationException ae) {
            System.out.println("登陆失败: " + ae.getMessage());
            return "{\"code\":0,\"msg\":\"用户名或者密码错误！\"}";
        }
        
        return "{\"code\":1,\"msg\":\"登陆成功!\"}";
    }
    
    @RequestMapping(value="/code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        
        int imgWidth = 90;
        
        int imgHeight = 35;
        
        String fontStyle;
        
        int fontHeight;
        
        // 在内存中创建图象
        BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics2D g = image.createGraphics();

        // 生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imgWidth, imgHeight);

        // 设定字体
        g.setFont(new Font("楷体", Font.PLAIN + Font.ITALIC, 35));

        // 画边框
        g.setColor(new Color(55, 55, 12));
        //g.drawRect(0, 0, imgWidth - 1, imgHeight - 1);

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 60; i++) {
            int x = random.nextInt(imgWidth);
            int y = random.nextInt(imgHeight);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand = "";
        int red = 0, green = 0, blue = 0;
        for (int i = 0; i < 4; i++) {
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            int wordType = random.nextInt(3);
            char retWord = 0;
            switch (wordType) {
            case 0:
                retWord = this.getSingleNumberChar();
                break;
            case 1:
                retWord = this.getLowerOrUpperChar(0);
                break;
            case 2:
                retWord = this.getLowerOrUpperChar(1);
                break;
            }
            sRand += String.valueOf(retWord);
            g.setColor(new Color(red, green, blue));
            g.drawString(String.valueOf(retWord), (i) * 20+5, 30);

        }
        // 将认证码存入SESSION
        session.setAttribute("rand_code", sRand);
        // 图象生效
        g.dispose();
        ServletOutputStream responseOutputStream = response.getOutputStream();
        // 输出图象到页面
        ImageIO.write(image, "JPEG", responseOutputStream);

        // 以下关闭输入流！
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    /**
     * 给定范围获得随机颜色
     * @param fc 范围1
     * @param bc 范围2
     * @return 返回生成颜色
     */
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    /**
     * 获取字符
     * @param upper 是否大小写 0：小写 1：大写
     * @return  字符
     */
    private char getLowerOrUpperChar(int upper) {
        Random random = new Random();
        int numberResult = random.nextInt(26);
        int ret = 0;
        if (upper == 0) {// 小写
            ret = numberResult + 97;
        } else if (upper == 1) {// 大写
            ret = numberResult + 65;
        }
        return (char) ret;
    }
    /**
     * 获取数据数字字符
     * @return 数字字符
     */
    private char getSingleNumberChar() {
        Random random = new Random();
        int numberResult = random.nextInt(10);
        int ret = numberResult + 48;
        return (char) ret;
    }
    
    
}
