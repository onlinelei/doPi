package win.suroot.dopi.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.suroot.dopi.dto.DailyInfoDTO;
import win.suroot.dopi.service.DailyInfoService;

@Slf4j
@RestController
@RequestMapping("/daily")
public class DailyController extends BaseController {

    @Autowired
    private DailyInfoService dailyInfoService;

    @RequestMapping("/info")
    public Object hello() {
        DailyInfoDTO info = dailyInfoService.todayInfo();
        return info;
    }
}
