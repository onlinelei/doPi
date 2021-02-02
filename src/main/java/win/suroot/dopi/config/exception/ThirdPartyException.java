package win.suroot.dopi.config.exception;


public class ThirdPartyException extends BusinessRuntimeException {
    public ThirdPartyException(String errMsg, AlarmLevelCode alarmLevelCode) {
        super(errMsg, alarmLevelCode);
    }

    public ThirdPartyException(String errMsg, Throwable throwable, AlarmLevelCode alarmLevelCode) {
        super(errMsg, throwable, alarmLevelCode);
    }
}
