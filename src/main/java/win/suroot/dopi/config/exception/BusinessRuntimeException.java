package win.suroot.dopi.config.exception;

import lombok.Data;

/**
 * @Author:zhuzule
 * @Description:自定义异常类
 * @Date: 2019/6/26 14:27
 */
@Data
public class BusinessRuntimeException extends RuntimeException {
    /**
     * 结果码
     */
    private String code;
    /**
     * 结果码描述
     */
    private String msg;
    /**
     * 结果码枚举
     */
    private ResultCode resultCode;
    /**
     * 告警级别
     */
    private AlarmLevelCode alarmLevelCode;

    public BusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    public BusinessRuntimeException(String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.msg = errMsg;
    }

    public BusinessRuntimeException(String errMsg, Throwable cause, AlarmLevelCode alarmLevelCode) {
        super(errMsg, cause);
        this.msg = errMsg;
        this.alarmLevelCode = alarmLevelCode;
    }

    /**
     * @param resultCode     返回错误码
     * @param alarmLevelCode 是否需要告警
     */
    public BusinessRuntimeException(ResultCode resultCode, AlarmLevelCode alarmLevelCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.resultCode = resultCode;
    }

    /**
     * @param errMsg         返回的错误信息
     * @param alarmLevelCode 是否需要告警
     */
    public BusinessRuntimeException(String errMsg, AlarmLevelCode alarmLevelCode) {
        super(errMsg);
        this.code = ResultCode.ERROR.getCode();
        this.msg = errMsg;
        this.alarmLevelCode = alarmLevelCode;
    }
}
