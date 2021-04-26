package cn.episooo.datastructurewebapplication.interceptor;

import cn.episooo.datastructurewebapplication.annotation.Authorization;
import cn.episooo.datastructurewebapplication.config.Constants;
import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.utils.security.JwtObject;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 20:09
 * @Description：
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private void writeJson(HttpServletResponse response, ResultMsg res) throws IOException {
        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");
        response.getWriter().append(res.toString());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是静态资源则不拦截
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        Annotation annotation;
        // 如果没有Authorization标记就不处理
        if ((annotation = method.getAnnotation(Authorization.class)) == null) {
            return true;
        }

        String accessTokenString = request.getHeader(Constants.AUTHORIZATION_HEADER);
        JwtObject token = JwtObject.parseToken(accessTokenString);

        //如果token解析出错
        if (token == null) {
            writeJson(response, new ResultMsg(-301, "token error or expired"));
            return false;
        }
//        if(((Authorization) annotation).type()==null){
//            return true;
//        }
        //校验合格
        if (((Authorization) annotation).type() == token.getType()
                && token.getUserId() != null) {
            request.setAttribute(Constants.CURRENT_USER_ID, token.getUserId());
            return true;
        }
        //权限不匹配
        writeJson(response, new ResultMsg(-401, "not authorized"));
        return false;

    }
}
