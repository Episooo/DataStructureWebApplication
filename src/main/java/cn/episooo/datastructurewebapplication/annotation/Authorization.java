package cn.episooo.datastructurewebapplication.annotation;

import cn.episooo.datastructurewebapplication.utils.security.TokenType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 20:35
 * @Description：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
    TokenType type();
}
