//package lv.java2.shopping_list.config;
//
//import lv.java2.shopping_list.config.SpringAppConfig;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
//
//public class WebAppInitializer extends AbstractDispatcherServletInitializer {
//
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigWebApplicationContext applicationContext =
//                new AnnotationConfigWebApplicationContext();
//        applicationContext.register(SpringAppConfig.class);
//        return applicationContext;
//    }
//
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigWebApplicationContext applicationContext =
//                new AnnotationConfigWebApplicationContext();
//        applicationContext.register(WebConfig.class);
//        return applicationContext;
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//}


