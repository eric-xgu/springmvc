package com.xugu;
//通过jetty启动，不使用web.xml启动

import com.xugu.config.MvcConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx =new AnnotationConfigWebApplicationContext();
        //注册mvc配置
        ctx.register(MvcConfig.class);
        //设置servlet上下文
        ctx.setServletContext(servletContext);
        //配置分发器Dispatch
        ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        servlet.addMapping("/");
        //初始化Bean
        servlet.setLoadOnStartup(1);
    }
}
