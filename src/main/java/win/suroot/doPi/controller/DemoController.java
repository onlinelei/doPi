package win.suroot.doPi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qianlei
 * @create 2020-04-20 11:48
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
