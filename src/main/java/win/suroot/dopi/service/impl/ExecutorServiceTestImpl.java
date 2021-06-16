package win.suroot.dopi.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import win.suroot.dopi.service.ExecutorServiceTest;
import win.suroot.dopi.task.TestTask;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
public class ExecutorServiceTestImpl implements ExecutorServiceTest {

  @Autowired
  private ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Override
  public void execTest() {
    for (int i = 0; i < 30; i++) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        log.error("thread sleep error", e);
        return;
      }
      Future submit = threadPoolTaskExecutor.submit(new TestTask());
      log.info("fature: {}", JSONObject.toJSONString(submit));
    }
  }

  @Override
  public void asyncTask() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("Async task~");
  }
}



