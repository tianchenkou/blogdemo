package me.koutian.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import me.koutian.annotation.PassToken;
import me.koutian.annotation.UserLoginToken;
import me.koutian.bean.User;
import me.koutian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: KouTian
 * @date: 2019-11-04 20:11
 * @description
 *
 */
public class tokenInterceptor implements HandlerInterceptor {
        @Autowired
        UserService userService;

    /**
     *          预处理回调，判断每个处理器是否符合要求，返回true表示符合要求，放行
     *          这里主要是对user的id和password进行解密，如果要改变，还需要改变生成token的service
     * @param httpServletRequest
     * @param httpServletResponse
     * @param object
     *          拦截的对象
     * @return
     * @throws Exception
     */
        @Override
        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
            String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
            // 如果不是映射到方法直接通过
            if(!(object instanceof HandlerMethod)){
                return true;
            }
            HandlerMethod handlerMethod=(HandlerMethod)object;
            Method method=handlerMethod.getMethod();
            //检查是否有passtoken注释，有则跳过认证
            if (method.isAnnotationPresent(PassToken.class)) {
                PassToken passToken = method.getAnnotation(PassToken.class);
                if (passToken.required()) {
                    return true;
                }
            }
            //检查有没有需要用户权限的注解
            if (method.isAnnotationPresent(UserLoginToken.class)) {
                UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
                if (userLoginToken.required()) {
                    // 执行认证
                    if (token == null) {
                        throw new RuntimeException("无token，请重新登录");
                    }
                    // 获取 token 中的 user id
                    String userId;
                    try {
                        userId = JWT.decode(token).getAudience().get(0);
                    } catch (JWTDecodeException j) {
                        //jwt解码失败
                        throw new RuntimeException("401");
                    }
                        User user = userService.findUserById(userId);
                        if (user == null) {
                            throw new RuntimeException("用户不存在，请重新登录");
                        }
                        // 验证 token
                        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                        try {
                            jwtVerifier.verify(token);
                        } catch (JWTVerificationException e) {
                            throw new RuntimeException("401");
                    }
                    //将验证通过后的用户信息放到请求中
                    httpServletRequest.setAttribute("currentUser", user);
                    return true;
                }
            }
            return true;
        }

    /**
     *         处理器执行之后，视图解析器渲染之前 执行的回调
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     *         处理器执行后返回的数据
     * @param modelAndView
     * @throws Exception
     */
        @Override
        public void postHandle(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               Object o, ModelAndView modelAndView) throws Exception {

        }

    /**
     *          视图解析器将视图解析之后的回调
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    Object o, Exception e) throws Exception {
        }

}
