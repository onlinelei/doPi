package win.suroot.dopi.config.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import win.suroot.dopi.config.exception.AlarmLevelCode;
import win.suroot.dopi.config.exception.BusinessRuntimeException;
import win.suroot.dopi.config.exception.ThirdPartyException;
import win.suroot.dopi.dto.ResDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler {

    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Throwable e) {
        // 打印异常堆栈信息
        String title = String.format("接口异常%s", request.getRequestURI());
        log.error(title + e.getMessage(), e);
        // 发送未知异常的报警
        sendMail(request, e);
        // 暂时直接打印异常信息
        return ResDTO.buildFailedRes("系统异常");
    }

    /**
     * 处理所有校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseBody
    public Object handleIllegalArgumentException(HttpServletRequest request, RuntimeException e) {
        // 打印异常堆栈信息
        log.warn(e.getMessage(), e);
        return ResDTO.buildFailedRes(e.getMessage());
    }

    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseBody
    public Object handleBusinessException(HttpServletRequest request, BusinessRuntimeException e, HandlerMethod method) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        // 可以根据BusinessRuntimeException的code来选择是否报警
        if (null != e.getAlarmLevelCode() && AlarmLevelCode.NEED_ALARM.getCode().equals(e.getAlarmLevelCode().getCode())) {
            sendMail(request, e);
        }
        String showMsg = StringUtils.isBlank(e.getMsg()) ? e.getMessage() : e.getMsg();
        return ResDTO.buildFailedRes(showMsg);
    }

    @ExceptionHandler(ThirdPartyException.class)
    @ResponseBody
    public Object handleThirdPartyException(HttpServletRequest request, ThirdPartyException e, HandlerMethod method) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        if (null != e.getAlarmLevelCode() && AlarmLevelCode.NEED_ALARM.getCode().equals(e.getAlarmLevelCode().getCode())) {
            sendMail(request, e);
        }
        return ResDTO.buildFailedRes("第三方服务异常");
    }

    private void sendMail(HttpServletRequest request, Throwable e) {
        String title = String.format("接口异常%s", request.getRequestURI());
        String context = String.format("<br><b>请求参数：</b>%s", getParameter(request));
        // TODO 发送邮件
        log.info("发送邮件内容：" + title + context);
    }

    /**
     * 从request中获取请求参数
     *
     * @param request
     * @return
     */
    private String getParameter(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String parameterBody = null;
        try {
            parameterBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("读取流异常", e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!parameterMap.isEmpty()) {
            stringBuilder.append(JSONObject.toJSONString(parameterMap));
        }
        if (parameterBody != null) {
            stringBuilder.append(parameterBody);
        }
        return stringBuilder.toString();
    }
}
