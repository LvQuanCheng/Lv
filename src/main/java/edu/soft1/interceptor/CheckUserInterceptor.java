package edu.soft1.interceptor;

import edu.soft1.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
      //获取session对象
        HttpSession session = request.getSession();
      //判定session中是否有登录对象
        Object obj = session.getAttribute("user");//获取session中的登录对象
        /*User user = (User) session.getAttribute("user");//null*/
        if (obj != null && request instanceof User){
            System.out.println("拦截器放行");
            return true;
        }
        //返回登陆页
        System.out.println("返回登陆页");
        return false;//被拦截


    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
