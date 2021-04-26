package cn.episooo.datastructurewebapplication.interceptor;

import cn.episooo.datastructurewebapplication.annotation.CurrentTeacher;
import cn.episooo.datastructurewebapplication.config.Constants;
import cn.episooo.datastructurewebapplication.domain.Teacher;
import cn.episooo.datastructurewebapplication.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/8 0:24
 * @Description：
 */
@Component
public class CurrentTeacherInterceptor implements HandlerMethodArgumentResolver {
    @Autowired
    TeacherService teacherService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //如果参数类型是UserEntity并且有CurrentUser注解则支持
        return methodParameter.getParameterType().isAssignableFrom(Teacher.class) &&
                methodParameter.hasParameterAnnotation(CurrentTeacher.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Integer userId = (Integer) nativeWebRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if(methodParameter.getParameterAnnotation(CurrentTeacher.class).formDAO())
        return teacherService.getTeacher(userId);
        return new Teacher().setId(userId);
    }
}
