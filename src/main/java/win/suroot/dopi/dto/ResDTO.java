package win.suroot.dopi.dto;

import lombok.Data;

/**
 * 接口返回标准类
 */
@Data
public class ResDTO {

    private Integer flag;
    private String message;
    private Object data;

    public static final int FAIL_CODE = 0;
    public static final int SUCCESS_CODE = 1;


    public static final String SUCCESS_MESSAGE = "SUCCESS";

    public static ResDTO buildSuccessRes() {
        ResDTO res = new ResDTO();
        res.setFlag(SUCCESS_CODE);
        res.setMessage(SUCCESS_MESSAGE);
        return res;
    }

    public static ResDTO buildSuccessRes(Object data) {
        ResDTO res = buildSuccessRes();
        res.setData(data);
        return res;
    }

    public static ResDTO buildSuccessRes(String message, Object data) {
        ResDTO res = buildSuccessRes(data);
        res.setMessage(message);
        return res;
    }

    public static ResDTO buildFailedRes() {
        ResDTO res = new ResDTO();
        res.setFlag(FAIL_CODE);
        return res;
    }

    public static ResDTO buildFailedRes(String message) {
        ResDTO res = buildFailedRes();
        res.setMessage(message);
        return res;
    }

    public static ResDTO buildRes(int flag, String message, Object data) {
        ResDTO res = new ResDTO();
        res.setFlag(flag);
        res.setMessage(message);
        res.setData(data);
        return res;
    }
}
