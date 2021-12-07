package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping(value = "/param1")
public class MyController {/*
    @RequestMapping("/hello.do")
    public String hello(){
        System.out.println("----hello----");
        return "hello";
    }*/

/*    @RequestMapping("hello")
    public ModelAndView hello(){
        System.out.println("--hello--");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        //添加到ModelAndView中的对象，存入request作用域
        mav.addObject("msg","I'm Lane");
        return mav;
    }*/
@RequestMapping(value = "upload",method = {RequestMethod.POST})
public  String fileUpload(MultipartFile image, HttpServletRequest request) throws IOException {
    InputStream is = image.getInputStream();//输入流
    String filename = image.getOriginalFilename();//文件名
    String realPath = request.getServletContext().getRealPath("/images");
    System.out.println("上传路径="+request);
    FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
    int size = IOUtils.copy(is,os);
    System.out.println("完成上传size="+size+"Byte");
    is.close();os.close();//关闭流
    return "welcome";
}
    @RequestMapping(value = "upload2",method = {RequestMethod.POST})
    public  String fileUpload2(MultipartFile[] images,HttpServletRequest request) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        for (MultipartFile image:images) {
            is = image.getInputStream();//输入流
            String filename = image.getOriginalFilename();//文件名称
            System.out.println("文件原名称="+filename);
            if (filename.equals("")){
                System.out.println("空字符串，进入下一轮循环");
                continue;//进入下一轮循环
            }
            String realPath = request.getServletContext().getRealPath("/images");
            System.out.println("上传路径=" + realPath);
            os = new FileOutputStream(new File(realPath, doFileName(filename)));
//            int size = IOUtils.copy(is, os);
//            System.out.println("完成上传size=" + size + "Byte");
            IOUtils.copy(is,os); //把输入流文件写入到输出流,完成文件的上传
        }
        os.close();
        is.close();//关闭流
        return "welcome";
    }

    private  String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//读取uuid字符，规避名称重复
        System.out.println("上传文件名="+uuid);
        return uuid+"."+extension;
    }

    @RequestMapping("/hello")
public String hello(String username, Model model){
    //将传入的参数添加到Model对象（存入了request作用域中）
    model.addAttribute("username",username);
    return "hello";
}




    @RequestMapping(value = "/param1",method = {RequestMethod.GET})
    public String param1(HttpServletRequest request){
        //接受client发来的数据
        String name = request.getParameter("name");
        System.out.println("name="+name);//打印接受过来的数据
        request.setAttribute("name",name);
        //调用业务层的方法
        //页面跳转
        return "hello";
    }
    @RequestMapping(value = "/param2",method = {RequestMethod.GET,RequestMethod.POST})
    public String param2(HttpServletRequest request, HttpSession session){
        //接受client发来的数据
        String name = request.getParameter("username");//获取数据
        String age = request.getParameter("age");//获取数据
        System.out.println("name="+name+",age="+age);//打印接受过来的数据
        session.setAttribute("age",age);//将数据存入session
        request.setAttribute("name",name);//将数据存入request
        //调用业务层的方法
        //页面跳转
        return "hello";//
    }
    @RequestMapping(value = "param3",method = {RequestMethod.POST})
    public String param3(String username,int age){
        System.out.println("----param3----");
        System.out.println("username="+username);
        System.out.println("age="+age);
        return "hello";
    }
    @RequestMapping(value = "param4",method = {RequestMethod.POST})//数据名与方法参数名不同
    public String param4(@RequestParam(value = "username",required = false) String u,
                         @RequestParam(value ="age",defaultValue = "18") int a,HttpSession session){
        System.out.println("----param4----");
        System.out.println("u="+u);
        System.out.println("a="+a);
        session.setAttribute("name",u);
        return "redirect: test";
    }
    @RequestMapping("test")
    public String test(){
        System.out.println("----test()----");
        return "hello";
    }
    @RequestMapping("reg")
    public String reg(User user){
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        System.out.println("birthday="+user.getBirthday());
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        return "redirect:/param1/test";
    }
    @RequestMapping(value = "param5",method = {RequestMethod.POST})
    public String param5(User user,HttpSession session){
        System.out.println("----param5----");
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        session.setAttribute("name",user.getUsername());
        return "redirect: test";
    }
 /*   @RequestMapping(value = "/load.do/{filename}")
    public void load(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response){
    response.setHeader("Content-Disposition","attachment;filename="+文件名);

    }*/
}
