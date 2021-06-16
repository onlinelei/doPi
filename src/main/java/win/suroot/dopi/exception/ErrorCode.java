package win.suroot.dopi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

  private static final Logger logger = LoggerFactory.getLogger(ErrorCode.class);

  private static Map<Integer, String> msgMap = new HashMap<Integer, String>();

  public static final Integer USER_NOT_UNIQUE = 100000;

  static {
    msgMap.put(USER_NOT_UNIQUE, "查询的用户不唯一");
  }

  public static final Integer SYSTEM_ERROR = 100001;

  static {
    msgMap.put(SYSTEM_ERROR, "服务器内部错误，请重试！");
  }

  public static final Integer HIVE_TABLE_EXITESED = 100002;

  static {
    msgMap.put(HIVE_TABLE_EXITESED, "该表名已经存在，请重试！");
  }

  public static final Integer UNKNOW_TABLE_ID = 100003;

  public static final Integer PARAM_ERROR = 100004;

  public static final Integer SIGN_ERROR = 100005;

  static {
    msgMap.put(PARAM_ERROR, "参数错误");
  }

	/*public static final Integer LINE_AGE_ERROR = 100006;
	static{addToMsgMap(LINE_AGE_ERROR, "血缘关系");}*/

  public static final Integer HIVE_TABLE_DELELTE_ERROR = 100007;

  static {
    msgMap.put(HIVE_TABLE_DELELTE_ERROR, "存在关联指标，请取消字段关联指标后重试");
  }


  public static final Integer HIVE_TABLE_NAME_ERROR = 100008;

  static {
    msgMap.put(HIVE_TABLE_NAME_ERROR, "建表语句表名须与左侧表名保持一致，请调整");
  }

  public static final Integer TASK_TEMPLATE_ID_NULL = 101007;

  static {
    msgMap.put(TASK_TEMPLATE_ID_NULL, "任务模板ID为空");
  }

  public static final Integer TASK_TEMPLATE_TYEP_NULL = 101008;

  static {
    msgMap.put(TASK_TEMPLATE_ID_NULL, "任务模板类型为空");
  }

  public static final Integer COMPUTE_TASKNAME_EXIST = 101009;

  static {
    msgMap.put(COMPUTE_TASKNAME_EXIST, "该任务已经存在");
  }

  public static final Integer COMPUTE_TASK_UNEFFECTIVE = 101010;

  static {
    msgMap.put(COMPUTE_TASK_UNEFFECTIVE, "不能在凌晨1点至凌晨4点使用知数堂进行数据查询");
  }

  public static final Integer COMPUTE_TASK_RUNNING_FAIL = 101011;

  static {
    msgMap.put(COMPUTE_TASK_RUNNING_FAIL, "SQL执行过程中出错，可能由于脏数据、中间计算数据过大、select字段带有\",;{}()\\n\\t=\"时未重命名等原因导致");
  }

  public static final Integer EXPORT_TASK_ERROR = 102000;

  static {
    msgMap.put(EXPORT_TASK_ERROR, "导出任务错误");
  }

  public static final Integer EXPORT_TASK_PROJECT_ID_NULL = 102001;

  static {
    msgMap.put(EXPORT_TASK_PROJECT_ID_NULL, "导出任务项目ID为空");
  }

  public static final Integer EXPORT_TASK_ID_NULL = 102002;

  static {
    msgMap.put(EXPORT_TASK_ID_NULL, "导出任务ID为空");
  }

  public static final Integer EXPORT_TASK_NAME_NULL = 102003;

  static {
    msgMap.put(EXPORT_TASK_NAME_NULL, "导出任务名称为空");
  }

  public static final Integer EXPORT_TASK_DESC_NULL = 102004;

  static {
    msgMap.put(EXPORT_TASK_DESC_NULL, "导出任务别名为空");
  }

  public static final Integer EXPORT_TASK_OWNER_NULL = 102005;

  static {
    msgMap.put(EXPORT_TASK_OWNER_NULL, "导出任务负责人为空");
  }

  public static final Integer EXPORT_TASK_TEMPLATE_NULL = 102006;

  static {
    msgMap.put(EXPORT_TASK_TEMPLATE_NULL, "导出任务模板ID为空");
  }

  static {
    msgMap.put(UNKNOW_TABLE_ID, "无效的table id");
    msgMap.put(SIGN_ERROR, "签名验证错误");
  }

  private static void addToMsgMap(Integer code, String msg) {
    if (null != msgMap.get(code)) {
      throw new RuntimeException("code already existed in msgMap. code:" + code);
    }
    if (logger.isDebugEnabled()) {
      logger.info("load errorCode:" + code + " msg:" + msg);
    }

    msgMap.put(code, msg);
  }

  public static String getMsg(Integer code) {
    return msgMap.get(code);
  }
}