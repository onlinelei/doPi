package win.suroot.dopi.thread;


import com.mysql.cj.QueryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TaskSubmitHandler implements InitializingBean {
  public static final int DEFAULT_SHUTDOWN_MILLSECOND_TIMEOUT = 20000;
  private static ThreadPoolExecutor threadPoolExecutor;
  private static ThreadPoolExecutor sampleDataPoolExecutor;


  @PostConstruct
  public void initialize() {
    threadPoolExecutor = new ThreadPoolExecutor(6, 6, 30, TimeUnit.MINUTES,
        new ArrayBlockingQueue<Runnable>(800), new ThreadPoolExecutor.CallerRunsPolicy());

    sampleDataPoolExecutor = new ThreadPoolExecutor(4, 6, 30, TimeUnit.MINUTES,
        new ArrayBlockingQueue<Runnable>(800), new ThreadPoolExecutor.CallerRunsPolicy());
  }

  @PreDestroy
  public void destroy() {
    if (threadPoolExecutor == null) {
      return;
    }
    try {
      threadPoolExecutor.shutdown();
      threadPoolExecutor.awaitTermination(DEFAULT_SHUTDOWN_MILLSECOND_TIMEOUT, TimeUnit.MILLISECONDS);
    } catch (Throwable ignore) {

    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // InitializingBean接口为bean提供了属性初始化后的处理方法，它只包括afterPropertiesSet方法，凡是继承该接口的类，在bean的属性初始化后都会执行该方法。
  }

  public static class TaskThread implements Callable<QueryResult> {
    // private ComputeTaskService computeTaskService;
    @Override
    public QueryResult call() {
      // task
      return null;
    }

    public TaskThread() {
      // public TaskThread(ComputeTaskService computeTaskService) {
      super();
      // this.computeTaskService = computeTaskService;
    }
  }
}