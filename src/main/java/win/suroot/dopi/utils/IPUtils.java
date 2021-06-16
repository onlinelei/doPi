package win.suroot.dopi.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * #func Java获取客户端IP<br>
 * #desc 考虑(代理,包括UTR)转发等情形 不再只调用getRemoteAddr()
 */
public class IPUtils {

  private static final String IP_UNKNOWN = "unknown";

  /**
   * #func 获取IP地址<br>
   * #desc 不再简单getRemoteAddr
   */
  public static String getIpAddr(HttpServletRequest request) {
    if (request == null) {
      return null;
    }
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }

    // 取X-Forwarded-For中第一个非unknown的有效IP字符串。
    if (ip.indexOf(",") != -1) {
      String[] ipList = ip.split(",");
      String tmp;
      for (int i = 0; i < ipList.length; i++) {
        tmp = ipList[i];
        if (tmp != null && !IP_UNKNOWN.equalsIgnoreCase(tmp.trim())) {
          return tmp.trim();
        }
      }
    }
    return ip;
  }
}
