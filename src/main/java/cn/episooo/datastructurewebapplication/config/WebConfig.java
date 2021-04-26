package cn.episooo.datastructurewebapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/23 20:55
 * @Description：
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/","classpath:/public/")
//                .setCacheControl(CacheControl.noStore());
//    }


//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        HttpMessageConverter<?> converter = fastConverter;
//        return new HttpMessageConverters(converter);
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        WebMvcConfigurer.super.configureMessageConverters(converters);
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.PrettyFormat
//        );
//
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//        System.out.println("添加fastjson转化");
//    }

//  允许跨域
@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedHeaders("*")
            .allowCredentials(true)
            .allowedMethods("*")
            .maxAge(1800)
            .allowedOrigins("http://localhost:9090");
    System.out.println("跨域请求已经支持 白名单：http://localhost:9090");
//    registry.addMapping("/**")
//            .allowedOriginPatterns("*")
//            .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
//            .allowCredentials(true);
}
}
