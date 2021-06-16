package win.suroot.dopi.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class ScheduledTasks {

  /**
   * 上一个任务开始时间为基准，从上一任务开始执行后，1分钟再次调用：
   */
  @Scheduled(fixedRate = (60 * 1000))
  public void fixedRate() {
    log.info("begin fixedRate task！");
    try {
      log.info("task-------------------");
    } catch (Exception e) {
      log.error("fixedRate error message:{}", e.getMessage());
    }
    log.info("end fixedRate task！");
  }


  /**
   * 上一个调用任务的完成时间为基准，在上一个任务完成之后，一分钟后再次执行：
   */
  @Scheduled(fixedDelay = (60 * 1000))
  public void fixedDelay() {
    log.info("begin fixedDelay task！");
    try {
      log.info("task-------------------");
    } catch (Exception e) {
      log.error("fixedDelay error message:{}", e.getMessage());
    }
    log.info("end fixedDelay task！");
  }
}
