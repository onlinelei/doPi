package win.suroot.dopi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import win.suroot.dopi.dto.ResDTO;


/**
 * @Author:wangwei
 * @Description:全局处理返回结果
 * @Date: 2019/7/01 15:28
 */
@ControllerAdvice(annotations = RestController.class)
public class ControllerResponseAdvisor implements ResponseBodyAdvice<Object> {

  @Value("${server.servlet.context-path}")
  private String contextPath;

  /**
   * 过滤哪些接口需要统一返回
   *
   * @param returnType
   * @param converterType
   * @return
   */
  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  /**
   * 统一返回体
   *
   * @param body
   * @param returnType
   * @param selectedContentType
   * @param selectedConverterType
   * @param request
   * @param response
   * @return
   */
  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                ServerHttpRequest request, ServerHttpResponse response) {
    String requestUrl = request.getURI().getPath();
    if (body instanceof String) {
      return body;
    }
    //对特定路径的结果集进行封装
    if (requestUrl.startsWith(contextPath)) {
      return ResDTO.buildSuccessRes(body);
    }
    return body;
  }
}