package win.suroot.dopi.skill.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class JdbcTransications {

  @Value("${spring.datasource.master.jdbc-url}")
  private String jdbcUrl;
  @Value("${spring.datasource.master.username}")
  private String userName;
  @Value("${spring.datasource.master.password}")
  private String password;

  public void JdbcTransfer() {
    try (Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
         Statement stmt = conn.createStatement();) {
      // 将自动提交设置为 false，若设置为 true 则数据库将会把每一次数据更新认定为一个事务并自动提交
      conn.setAutoCommit(true);
      // 将 A 账户中的金额减少 500
      stmt.execute("update t_verify_table set create_time = '2021-05-10 16:07:59' where id = 2");
      // 将 B 账户中的金额增加 500
      stmt.execute("update t_verify_table set create_time = '2021-05-10 16:08:59' where id = 2");

      // 事务提交：转账的两步操作同时成功
      conn.commit();

    } catch (SQLException throwables) {
      log.error("connection error", throwables);
    }
  }
}
