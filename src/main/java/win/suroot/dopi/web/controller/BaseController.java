package win.suroot.dopi.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import win.suroot.dopi.exception.ErrorCode;
import win.suroot.dopi.po.User;
import win.suroot.dopi.utils.IPUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
class BaseController implements ApplicationContextAware {


    public static final String CURRENT_LOGIN_USER = "CURRENT_USER_KEY";
    protected static final String STATUS_OK = "0";
    protected static final String LOGIN_USERNAME = "loginUserName";
    protected static final String SESSION_USER = "currentUser";
    protected static final int DEFAULT_PAGE_COUNT = 10;
    protected static final String CONTENT_EXCEL_TYPE = "application/vnd.ms-excel";
    protected ApplicationContext applicationContext;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    protected User getCurrentLoginUser(HttpServletRequest request) {
        Object obj = session.getAttribute(BaseController.CURRENT_LOGIN_USER);
        if (null != obj) {
            return (User) obj;
        }
        return null;
    }

    protected User getCurrentUser() {
        return (User) getRequest().getSession().getAttribute(CURRENT_LOGIN_USER);
    }

    protected HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected Map<String, Object> createSuccessMap(Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", 0);
        result.put("data", data);
        result.put("error", null);
        return result;
    }

    protected Map<String, Object> createFailedMap(Integer errorCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", 1);
        result.put("data", null);
        result.put("error", ErrorCode.getMsg(errorCode));
        return result;
    }

    protected Map<String, Object> createFailedMap(String errorMsg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", 1);
        result.put("data", null);
        result.put("error", errorMsg);
        return result;
    }

    public static Map<String, Object> sendSuccessMap(Map<String, Object> params) {
        params.put("flag", 0);
        params.put("error", "");
        return params;
    }

    public static Map<String, Object> sendFailedMap(Map<String, Object> params, Integer errorCode) {
        params.put("flag", 1);
        params.put("error", ErrorCode.getMsg(errorCode));
        return params;
    }

    // ======== Exception Handle ========

    private void reportException(Exception ex, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String queryStr = request.getQueryString();
        String message = String.format("ip:%s, requestUri:%s, queryStr:%s, exception:%s", IPUtils.getIpAddr(request), requestUri, queryStr, ex.getMessage());
        log.error(message, ex);
    }

    /**
     * 未处理的系统异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleException(Exception ex, HttpServletRequest request) {
        this.reportException(ex, request);
        return this.createFailedMap(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 非法参数异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        this.reportException(ex, request);
        return this.createFailedMap(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 尝试将字符串转换为数字时异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleNumberFormatException(NumberFormatException ex, HttpServletRequest request) {
        this.reportException(ex, request);
        return this.createFailedMap(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 参数类型匹配异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleTypeMismatchException(TypeMismatchException ex, HttpServletRequest request) {
        this.reportException(ex, request);
        return this.createFailedMap(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 缺少必须传入的参数
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex,
                                                                             HttpServletRequest request) {
        this.reportException(ex, request);
        return this.createFailedMap(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 参数类型绑定异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleBindException(BindException ex, HttpServletRequest request) {
        this.reportException(ex, request);
        return this.createFailedMap(ErrorCode.SYSTEM_ERROR);
    }

    public String getContextUploadPath(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/") + "/upload/temp/";
        File pathFolder = new File(path);
        if (!pathFolder.exists() || !pathFolder.isDirectory()) {
            pathFolder.mkdirs();
        }
        return path;
    }

    public String getContextDownloadPath(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/") + "/download/temp/";
        File pathFolder = new File(path);
        if (!pathFolder.exists() || !pathFolder.isDirectory()) {
            pathFolder.mkdirs();
        }
        return path;
    }
}
