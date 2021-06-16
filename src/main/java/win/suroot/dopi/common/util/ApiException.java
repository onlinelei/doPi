package win.suroot.dopi.common.util;

/**
 * @author qianlei
 * @create 2017-12-20 14:54
 * @desc Api参数
 */
public class ApiException extends RuntimeException {
  //java方法传递的参数有问题
  public static final String SYS_PARAM_ERROR = "系统参数错误";
  //java传递的格式无法处理
  public static final String UNKOWN_FORAMT_ERROR = "未知的format格式";

  //格式化参数错误
  public static final String PARAM_FORMAT_ERROR = "参数格式错误";
  //缺少必须参数错误
  public static final String PARAM_REQUIRED_ERROR = "缺少必须参数";


  //checked exception
  public ApiException(String message) {
    super(message);
  }

  //non-checked exception
  public ApiException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
