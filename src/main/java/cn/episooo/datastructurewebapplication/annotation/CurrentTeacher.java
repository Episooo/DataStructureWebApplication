package cn.episooo.datastructurewebapplication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/8 0:26
 * @Description：
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentTeacher {
    //是否通过dao获取对象
    boolean formDAO() default false;
}
