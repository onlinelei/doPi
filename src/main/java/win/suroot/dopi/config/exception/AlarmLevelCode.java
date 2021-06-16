package win.suroot.dopi.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author:zhuzule
 * @Description: 报警级别
 * @Date: 2019/6/26 18:46
 */
@Getter
@AllArgsConstructor
public enum AlarmLevelCode {
  NO_NEED_ALARM("0", "无需告警"),
  NEED_ALARM("1", "需要告警");
  //告警级别
  private String code;
  //级别信息
  private String msg;
}
