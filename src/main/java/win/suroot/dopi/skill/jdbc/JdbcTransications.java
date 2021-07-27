package win.suroot.dopi.skill.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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

  @Autowired
  @Qualifier("masterDataSource")
  private DataSource dataSource;

  public void JdbcTransfer() {
    try (Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
         Statement stmt = conn.createStatement();) {
      // 将自动提交设置为 false，若设置为 true 则数据库将会把每一次数据更新认定为一个事务并自动提交
      conn.setAutoCommit(true);
      // 将 A 账户中的金额减少 50
      stmt.execute("update t_user set money = money + 50 where id = 1");
      // 将 B 账户中的金额增加 50
      stmt.execute("update t_user set money = money - 50 where id = 2");

      // 事务提交：转账的两步操作同时成功
      conn.commit();
    } catch (SQLException throwables) {
      log.error("connection error", throwables);
    }
  }

  public void dataSourceTransfer() {
    try (Connection conn = dataSource.getConnection();
         Statement stmt = conn.createStatement();) {
      // 将自动提交设置为 false，若设置为 true 则数据库将会把每一次数据更新认定为一个事务并自动提交
      conn.setAutoCommit(false);
      // 将 A 账户中的金额减少 50
      stmt.execute("update t_user set money = money + 50 where id = 1");
      // 将 B 账户中的金额增加 50
      stmt.execute("update t_user set money = money - 50 where id = 2");

      // 事务提交：转账的两步操作同时成功
      conn.commit();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

}
