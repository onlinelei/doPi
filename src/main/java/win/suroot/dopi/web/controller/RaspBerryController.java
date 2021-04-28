package win.suroot.dopi.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.suroot.dopi.service.RaspberryService;
import win.suroot.dopi.common.util.ApiUtils;


@RestController()
@RequestMapping("/pi")
public class RaspBerryController {
    private static final Logger logger = LoggerFactory.getLogger(RaspBerryController.class);

    @Autowired
    private RaspberryService raspberryService;

    @RequestMapping("/light")
    public JSONObject light() {
        JSONObject result = new JSONObject();
        try {
            raspberryService.light();

        } catch (Exception e) {
            logger.debug("demo()----ERROR" + e.getMessage());
            ApiUtils.formatJsonException(e, result);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/light1")
    public JSONObject light1() {
        JSONObject result = new JSONObject();
        try {
            raspberryService.light1();

        } catch (Exception e) {
            logger.debug("demo()----ERROR" + e.getMessage());
            ApiUtils.formatJsonException(e, result);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/sysInfo")
    public JSONObject sysInfo() {
        JSONObject result = new JSONObject();
        try {
            raspberryService.sysInfo();

        } catch (Exception e) {
            logger.debug("demo()----ERROR" + e.getMessage());
            ApiUtils.formatJsonException(e, result);
            e.printStackTrace();
        }
        return result;
    }

}
