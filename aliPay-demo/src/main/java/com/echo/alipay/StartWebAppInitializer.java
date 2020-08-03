package com.echo.alipay;

import com.echo.alipay.config.MvcConfig;
import com.echo.alipay.config.SpringConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 14:04 2020/7/31
 * @description:
 */
@Order(100)
public class StartWebAppInitializer implements WebApplicationInitializer {

    private static final String SERVLET_NAME = "Spring-mvc-aliPay-demo";
    private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5; // 5 Mb
    private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb
    private static final long MAX_REQUEST_SIZE = -1L; // No request size limit

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("Web App Start...");
        // 注册监听器
        // servletContext.addListener(arg0);

        servletContext.addListener(new RequestContextListener());

        // 注册过滤器
        //CharacterEncodingFilter
        FilterRegistration.Dynamic filter = servletContext.addFilter("CharacterEncodingFilter", new CharacterEncodingFilter("utf-8", true));
        filter.addMappingForUrlPatterns(null, true, "/*");

        // 注册springMvc的servlet
        addServlet(servletContext);
    }

    private void addServlet(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webContext = createWebContext(SpringConfig.class, MvcConfig.class);

        DispatcherServlet dp = new DispatcherServlet(webContext);
        //dp.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet(SERVLET_NAME, dp);

        dynamic.addMapping("/");
        //dynamic.addMapping("*.txt");
        //dynamic.addMapping("*.html", "*.ajax", "*.css", "*.js", "*.gif", "*.jpg", "*.png");
        //dynamic.setInitParameter("throwExceptionIfNoHandlerFound", "true");

        //ServletSecurity secAnnotation = this.getClass().getAnnotation(ServletSecurity.class);


        //https://www.ibm.com/support/knowledgecenter/SSCKBL_8.5.5/com.ibm.websphere.nd.multiplatform

        //dynamic.setServletSecurity(new ServletSecurityElement(new HttpConstraintElement()));

        /*final HttpConstraintElement constraint = new HttpConstraintElement();
        final HttpConstraintElement denyConstraint = new HttpConstraintElement(ServletSecurity.EmptyRoleSemantic.DENY);
        dynamic.setServletSecurity(new ServletSecurityElement(constraint, new ArrayList<HttpMethodConstraintElement>(){
            private static final long serialVersionUID = -8938719332118022161L;  {
                add(new HttpMethodConstraintElement("OPTIONS", denyConstraint));
                add(new HttpMethodConstraintElement("PUT", denyConstraint));
                add(new HttpMethodConstraintElement("DELETE", denyConstraint));
                add(new HttpMethodConstraintElement("HEAD", denyConstraint));
                add(new HttpMethodConstraintElement("TRACE", denyConstraint));

			}
		}));*/

        dynamic.setLoadOnStartup(1);
        dynamic.setMultipartConfig(new MultipartConfigElement(null, MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
    }

    private AnnotationConfigWebApplicationContext createWebContext(Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(annotatedClasses);
        return webContext;
    }

}
