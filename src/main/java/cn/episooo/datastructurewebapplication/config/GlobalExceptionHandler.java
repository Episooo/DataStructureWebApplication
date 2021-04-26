package cn.episooo.datastructurewebapplication.config;

import cn.episooo.datastructurewebapplication.interceptor.AuthInterceptor;
import cn.episooo.datastructurewebapplication.interceptor.CurrentStudentInterceptor;
import cn.episooo.datastructurewebapplication.interceptor.CurrentTeacherInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 22:45
 * @Description：
 */
@RestControllerAdvice
@Configuration
public class GlobalExceptionHandler implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**");
        System.out.println("鉴权 @Authorization 注解增加成功");
    }

    @Bean
    public CurrentStudentInterceptor getCurrentStudentInterceptor(){
        return new CurrentStudentInterceptor();
    }
    @Bean
    public CurrentTeacherInterceptor getCurrentTeacherInterceptor(){
        return new CurrentTeacherInterceptor();
    }
    @Bean
    public AuthInterceptor getAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getCurrentStudentInterceptor());
        resolvers.add(getCurrentTeacherInterceptor());
        System.out.println("用户对象注入的 @CurrentStudent 和 @CurrentTeacher 注解增加成功");
    }
}
