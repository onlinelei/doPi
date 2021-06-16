package win.suroot.dopi.config.exception;

/**
 * @Author:zhuzule
 * @Description:
 * @Date: 2019/6/26 14:09
 */
public enum ResultCode {
  SUCCESS("1", "success"),
  ERROR("0", "服务异常，请稍后重试"),
  UNKNOWN_ERROR("0x10001", "未知错误"),
  PROCESS_FAIL("0x10001", "处理失败"),
  PARAM_ERROR("0x10002", "参数错误"),
  RPC_TIMEOUT("0x10003", "远程调用超时");

  //结果码
  private String code;
  //结果码对应的错误信息
  private String msg;

  ResultCode(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
