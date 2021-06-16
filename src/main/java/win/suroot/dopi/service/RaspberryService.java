package win.suroot.dopi.service;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author qianlei
 * @create 2018-06-01 15:53
 * @desc 树莓派服务类
 */
public interface RaspberryService {

  /**
   * light test demo
   *
   * @author: qianlei
   * @date: 2019-09-29 16:01
   */
  void light() throws InterruptedException;

  /**
   * light test demo1
   *
   * @author: qianlei
   * @date: 2019-09-29 16:01
   */
  void light1();

  /**
   * system info of pi
   *
   * @author: qianlei
   * @date: 2019-09-29 15:42
   * @update: [变更日期YYYY-MM-DD][变更人姓名][变更描述]
   */
  void sysInfo() throws IOException, InterruptedException, ParseException;

}
