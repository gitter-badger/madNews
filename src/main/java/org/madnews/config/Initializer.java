package org.madnews.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer {
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        // register configuration
        ctx.register(WebAppConfig.class);
        // add listener
        servletContext.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(servletContext);
        // configure mapping Dispatcher Servlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        servlet.addMapping("/api/v1/public/");
        servlet.setLoadOnStartup(1);
    }
}
