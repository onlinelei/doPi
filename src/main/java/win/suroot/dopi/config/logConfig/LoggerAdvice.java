package win.suroot.dopi.config.logConfig;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggerAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("within(win.suroot.dopi.web.controller..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        logger.info("执行{}开始,方法签名:{},传入参数:{}", loggerManage.description(), joinPoint.getSignature().toShortString(), parseParams(joinPoint));
        startTime.set(System.nanoTime());
    }

    @AfterReturning(returning = "rvt", pointcut = "within(win.suroot.dopi.web.controller..*) && @annotation(loggerManage)")
    public void addAfterReturningLogger(LoggerManage loggerManage, Object rvt) {
        long executeTime = (System.nanoTime() - startTime.get()) / 1000000;
        startTime.remove();
        logger.info("执行{}结束,耗时{}ms", loggerManage.description(), executeTime);
        if (loggerManage.printReturning() && rvt != null) {
            if (rvt instanceof String) {
                String result = (String) rvt;
                if (StringUtils.isBlank(result)) {
                    return;
                }
            }
            logger.info("返回结果:{}", JSONObject.toJSONString(rvt));
        }
    }

    /**
     * 获取参数名和参数值
     * 这里要注意一点，若需要打印父类对象属性，则需子类toString方法中打印父类属性
     * 推荐使用lombok，子类上增加@ToString(callSuper = true)注解即可
     *
     * @param joinPoint
     * @return
     */
    private String parseParams(JoinPoint joinPoint) {
        // 1.这里获取到所有的参数值的数组
        Object[] paramValues = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 2.最关键的一步:通过这获取到方法的所有参数名称的字符串数组
        String[] paramNames = methodSignature.getParameterNames();
        Map<String, Object> param = new HashMap<>(16);
        for (int i = 0; i < paramNames.length; i++) {
            //ServletRequest、ServletResponse不能序列化
            if (paramValues[i] instanceof ServletRequest
                    || paramValues[i] instanceof ServletResponse
                    || paramValues[i] instanceof HttpSession
                    || paramValues[i] instanceof InputStreamSource) {
                continue;
            }
            param.put(paramNames[i], paramValues[i]);
        }
        String result = StringUtils.EMPTY;
        if (!CollectionUtils.isEmpty(param)) {
            try {
                result = JSONObject.toJSONString(param);
            } catch (Exception e) {
                logger.error("json转换异常", e);
                result = param.toString();
            }
        }
        return result;
    }
}
