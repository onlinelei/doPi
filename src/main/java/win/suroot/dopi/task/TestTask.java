package win.suroot.dopi.task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestTask implements Runnable {


  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info("this is test task");
    }
  }
}
