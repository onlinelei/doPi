package win.suroot.dopi.common.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author qianlei
 * @create 2019-03-08 15:40
 * @desc
 */
@Slf4j
public class ApiUtils {

    /**
     * 格式化json数据异常信息.
     *
     * @param e
     * @param map
     */
    public static void formatJsonException(Exception e, Map map) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        map.put(ApiConstants.STATUS_DESC, e.getMessage());
    }

    public static void formatJsonException(Exception e, JSONObject rstJson) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        rstJson.put(ApiConstants.STATUS_DESC, e.getMessage());
    }

    /**
     * 格式化有data数据的json异常信息.
     *
     * @param e
     * @param map
     * @param data
     */
    public static void formatJsonExceptionWithData(Exception e, Map map, String data) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        map.put(ApiConstants.STATUS_DESC, e.getMessage());
        map.put(ApiConstants.DATA, data == null ? "" : data);
    }

    public static void formatJsonExceptionWithData(Exception e, JSONObject rstJson, String data) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        rstJson.put(ApiConstants.STATUS_DESC, e.getMessage());
        rstJson.put(ApiConstants.DATA, data == null ? "" : data);
    }

    /**
     * 格式化参数错误等非数据库异常信息.
     *
     * @param desc
     * @param map
     */
    public static void formatJsonError(String desc, Map map) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        map.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
    }

    public static void formatJsonError(String desc, JSONObject rstJson) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        rstJson.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
    }

    /**
     * 格式化有data(如：参数错误)的非数据库异常信息.
     *
     * @param desc
     * @param map
     * @param data
     */
    public static void formatJsonErrorWithData(String desc, Map map, String data) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        map.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
        map.put(ApiConstants.DATA, data == null ? "" : data);
    }

    public static void formatJsonErrorWithData(String desc, JSONObject rstJson, String data) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL);
        rstJson.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
        rstJson.put(ApiConstants.DATA, data == null ? "" : data);
    }

    /**
     * 格式化正常的有data数据的信息.
     *
     * @param map
     * @param obj
     */
    public static void formatJsonWithData(Map map, Object obj) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_SUCCESS);
        map.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_SUCCESS);
        map.put(ApiConstants.DATA, obj == null ? "" : obj);
    }

    public static void formatJsonWithData(JSONObject rstJson, Object obj) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_SUCCESS);
        rstJson.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_SUCCESS);
        rstJson.put(ApiConstants.DATA, obj == null ? "" : obj);
    }

    /**
     * 格式化正常的无data数据的信息.
     *
     * @param map
     */
    public static void formatJson(Map map) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_SUCCESS);
        map.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_SUCCESS);
    }

    public static void formatJson(JSONObject rstJson) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_SUCCESS);
        rstJson.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_SUCCESS);
    }

    /**
     * 格式化401seesion失效的json对象.
     *
     * @param map
     */
    public static void formatSessionInvalid(Map map, String data) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_401);
        map.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_FAIL_401);
        map.put(ApiConstants.DATA, data == null ? "" : data);
    }

    public static void formatSessionInvalid(JSONObject rstJson, Object obj) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_401);
        rstJson.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_FAIL_401_2);
        rstJson.put(ApiConstants.DATA, obj == null ? "" : obj);
    }


    public static String SimpleEncode(String str) {
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = MapChar(array[i]);
        }
        return new String(array);
    }

    private static char MapChar(char c) {
        if (c >= '0' && c <= '4') {
            return (char) (c + 5);
        } else if (c >= '5' && c <= '9') {
            return (char) (c - 5);
        } else if (c >= 'A' && c <= 'M') {
            return (char) ('n' + (c - 'A'));
        } else if (c >= 'n' && c <= 'z') {
            return (char) (c - 'n' + 'A');
        } else if (c >= 'N' && c <= 'Z') {
            return (char) ('m' - (c - 'N'));
        } else if (c >= 'a' && c <= 'm') {
            return (char) ('N' + ('m' - c));
        } else if (c == '@') {
            return '-';
        } else if (c == '-') {
            return '@';
        } else {
            return c;
        }
    }

    public static String SimpleDecode(String str) {
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = MapChar(array[i]);
        }
        return new String(array);
    }


}
