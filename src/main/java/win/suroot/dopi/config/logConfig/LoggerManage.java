package win.suroot.dopi.config.logConfig;

import java.lang.annotation.*;

/**
 * @author wangwei
 * @Description: 日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {
  /**
   * 描述
   *
   * @return
   */
  String description();

  /**
   * 是否打印返回结果
   *
   * @return
   */
  boolean printReturning() default true;
}
