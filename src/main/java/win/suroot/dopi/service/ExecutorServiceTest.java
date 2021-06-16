package win.suroot.dopi.service;

import org.springframework.scheduling.annotation.Async;

public interface ExecutorServiceTest {

  void execTest();

  @Async
  void asyncTask();

}
