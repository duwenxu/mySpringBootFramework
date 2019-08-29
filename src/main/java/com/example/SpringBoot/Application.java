package com.example.springboot;

import com.example.springboot.jpa.extrepository.ExtJpaRepositoryFactoryBean;
import com.example.springboot.database.model.ConfigIDEABean;
import org.apache.catalina.filters.RemoteIpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @SpringBootApplication注解是SpringBoot的核心注解，它其实是一个组合注解：
 *
 * 该注解主要组合了以下注解：
 * 1.@SpringBootConfiguration：这是SpringBoot项目的配置注解，这也是一个组合注解：
 *    在Spring Boot项目中推荐使用@SpringBootConfiguration替代@Configuration
 * 2.@EnableAutoConfiguration：启用自动配置，该注解会使SpringBoot根据项目中依赖的jar包自动配置项目的配置项：
 如：我们添加了spring-boot-starter-web的依赖，项目中也就会引入SpringMVC的依赖，SpringBoot就会自动配置tomcat和SpringMVC
 * 3.@ComponentScan：默认扫描@SpringBootApplication所在类的同级目录以及它的子目录。
 */

/**
 * 我们常常在项目中会使用filters用于录调用日志、排除有XSS威胁的字符、执行权限验证等等。Spring Boot自动添加了OrderedCharacterEncodingFilter和HiddenHttpMethodFilter，并且我们可以自定义Filter。
 *
 * 两个步骤：
 * 1.实现Filter接口，实现Filter方法
 * 2.添加@Configurationz 注解，将自定义Filter加入过滤链
 */
/**
 * 还可以通过注解的方式设置Filter
 * @Order(1)
 * @WebFilter(filterName = "testFilter1", urlPatterns = "/*")
 */
@SpringBootApplication
@EnableScheduling    //开启定时任务注解    在方法上使用@Scheduled注解定时执行方法
@EnableJpaRepositories(repositoryFactoryBeanClass = ExtJpaRepositoryFactoryBean.class)  //spring jpa
@EnableConfigurationProperties({ConfigIDEABean.class})

@EnableCaching()    //开启缓存
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.warn("------------------------------------------------------appStart-----------------------------------------------------------------------------------");
        SpringApplication.run(Application.class, args);
        logger.warn("------------------------------------------------------appStarted-----------------------------------------------------------------------------------");
    }

    //Spring Boot、Spring Web和Spring MVC等其他框架，都提供了很多servlet 过滤器可使用，我们需要在配置文件中定义这些过滤器为bean对象。
    //Spring Boot监测到有javax.servlet.Filter的bean时就会自动加入 过滤器调用链
    //Tomcat 8 提供的过滤器：RemoteIpFilter   将代理服务器发来的请求包含的IP地址转换成真正的用户IP
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    //可以通过创建FilterRegistrationBean对象并用 @Bean 标记,将自定义的过滤器添加并生效
//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new MyFilter());//添加过滤器
//        registration.addUrlPatterns("/*");//设置过滤路径   /*：所有路径
//        registration.addInitParameter("paramName", "paramValue");//添加默认参数
//        registration.setName("MyFilter");//设置过滤器名称
//        registration.setOrder(1);//设置过滤器的执行顺序，值越小，越先执行
//        return registration;
//    }

    //实现Filter接口，并创建自定义Filter
    public class MyFilter implements Filter {
        @Override
        public void destroy() {
            // TODO Auto-generated method stub
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            // TODO Auto-generated method stub
            HttpServletRequest request = (HttpServletRequest) srequest;
            System.out.println("this is MyFilter,url :"+request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }
        @Override
        public void init(FilterConfig arg0) throws ServletException {
            // TODO Auto-generated method stub
        }
    }

    //在Application启动类添加Servlet的支持
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}

