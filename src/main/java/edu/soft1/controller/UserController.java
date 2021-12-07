package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController {

/*    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile image, HttpServletRequest request) throws IOException {
        InputStream is = image.getInputStream();//输入流
        String filename = image.getOriginalFilename();//文件名
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("上传路径="+realPath);
        FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
        int size = IOUtils.copy(is,os);
        System.out.println("完成上传size="+size+"Byte");
        os.close();is.close();//关闭流
        return "welcome";
    }

    private  String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//读取uuid字符，规避名称重复
        System.out.println("上传文件名="+uuid);
        return uuid+"."+extension;
    }*/

    @RequestMapping("/hello")
    public String hello(/*String username, Model model*/){
        System.out.println("----hello()----");
       /* //将传入的参数添加到Model对象(存入request作用域)
        model.addAttribute("username",username);*/
        return "hello";
    }

    @RequestMapping(value = "/login")
    public String login(User user, HttpSession session, HttpServletRequest request){
        System.out.println("---login()----");
        //调用业务,获取flag的值
        System.out.println("username="+user.getUsername());
        int flag=1;
        if(flag==1){
            System.out.println("username="+user.getUsername());
            session.setAttribute("user",user);//登陆对象放入session
            return "welcome";//成功
        }
        System.out.println("登陆失败，请重新尝试");
        request.setAttribute("error","用户名或密码不正确");
    return "forward:/index.jsp";//失败
    }

    @RequestMapping("reg")
    public String reg(User user){
        System.out.println("username="+user.getUsername());
        System.out.println("pwd="+user.getPwd());
        System.out.println("age="+user.getAge());
        System.out.println("birthday="+user.getBirthday());
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        return "hello";
    }
    @RequestMapping("/delete")
    public String delete(){
        System.out.println("-----delete()-----");
        return "welcome";
    }
    @RequestMapping("/logout")//登出功能
    public String logout(HttpSession session){
        //清空session
        session.invalidate();
        System.out.println("已退出");
        return "redirect:/index.jsp";//重定向跳转到首页
    }
}
