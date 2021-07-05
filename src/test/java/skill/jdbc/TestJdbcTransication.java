package skill.jdbc;

import doPi.DoPiApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import win.suroot.dopi.skill.jdbc.JdbcTransications;

public class TestJdbcTransication extends DoPiApplicationTests {

  @Autowired
  private JdbcTransications jdbcTransication;

  @Test
  public void testJdbcTransfer(){
    jdbcTransication.JdbcTransfer();
  }
}
