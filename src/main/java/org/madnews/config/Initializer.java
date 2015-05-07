package org.madnews.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("Initializer -> getRootConfigClasses:  Inicio");
        return new Class[] {RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("Initializer -> getServletConfigClasses:  Inicio");
        return new Class<?>[] { WebAppConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("Initializer -> getServletMappings:  Inicio");
        return new String[] { "/api/v1/public/" };
    }
}
