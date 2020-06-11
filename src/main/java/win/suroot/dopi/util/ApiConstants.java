package win.suroot.dopi.util;

/**
 * Created by wangzz on 2016/9/29.
 */
public class ApiConstants {

    /**
     * json接口数据
     **/
    public static final String STATUS_CODE = "flag";
    public static final int STATUS_CODE_SUCCESS = 1;
    public static final int STATUS_CODE_FAIL = 0;
    public static final int STATUS_CODE_FAIL_401 = 401;

    public static final String STATUS_DESC = "message";
    public static final String STATUS_DESC_SUCCESS = "ok";
    public static final String STATUS_DESC_FAIL_401 = "授权失败，请重新登录";
    public static final String STATUS_DESC_FAIL_401_2 = "session失效或授权失败";
    public static final String STATUS_DESC_NO_RIGHT = "无权限访问";

    public static final String DATA = "data";
    public static final String DATA_LIST = "dataList";

    /**
     * 格式列表
     **/
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String INTEGER_FORMAT = "1";
    public static final String LONG_FORMAT = "2";
    public static final String FLOAT_FORMAT = "3";
    public static final String INTEGER_COMMA_LIST_FORMAT = "11";

}
