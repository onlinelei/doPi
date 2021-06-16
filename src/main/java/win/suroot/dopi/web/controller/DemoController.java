package win.suroot.dopi.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.suroot.dopi.config.logConfig.LoggerManage;
import win.suroot.dopi.service.ExecutorServiceTest;
import win.suroot.dopi.service.VerifyTableService;

/**
 * @author qianlei
 * @create 2020-04-20 11:48
 * @desc
 */

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

  @Autowired
  private ExecutorServiceTest executorService;
  @Autowired
  private VerifyTableService verifyTableService;

  @RequestMapping("/hello")
  public String hello() {
    return "hello world!";
  }

  @RequestMapping("/task")
  @LoggerManage(description = "异步线程测试")
  public String task() {
    for (int i = 0; i < 20; i++) {
      executorService.asyncTask();
    }
    return "done";
  }

  @RequestMapping("/testMysql")
  @LoggerManage(description = "测试 mysql 连通性")
  public Object testMysql() {
    return verifyTableService.testMysql();
  }
}
