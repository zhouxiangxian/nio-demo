package cn.mldn.util.interceptor;

import cn.mldn.util.tools.Validator;
import org.springframework.context.MessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ValidatorInteceptor implements HandlerInterceptor {
    @Resource
    private MessageSource messageSource;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) o;   // 取得操作对象
        try {
            String vKey = handlerMethod.getBean().getClass().getSimpleName() + "." + handlerMethod.getMethod().getName();
            String pKey = handlerMethod.getBean().getClass().getSimpleName() + "." + handlerMethod.getMethod().getName() + ".error";
            String validatorValue = this.messageSource.getMessage(vKey, null, Locale.getDefault());
            String pageValue = this.messageSource.getMessage(pKey, null, Locale.getDefault()); // 错误的跳转路径
//            System.out.println("vKey = " + vKey + "、Value = " + validatorValue);
//            System.out.println("pKey = " + pKey + "、Value = " + pageValue);
            if (validatorValue != null) {   // 有此规则
                if (new Validator().validate(httpServletRequest, validatorValue)) {  // 验证成功
                    return true;
                } else {
                    httpServletRequest.getRequestDispatcher(pageValue).forward(httpServletRequest, httpServletResponse);
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
