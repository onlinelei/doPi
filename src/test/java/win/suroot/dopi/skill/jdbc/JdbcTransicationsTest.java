package win.suroot.dopi.skill.jdbc;

import win.suroot.dopi.DoPiApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JdbcTransicationsTest extends DoPiApplicationTests {

  @Autowired
  private JdbcTransications jdbcTransaction;

  @Test
  void jdbcTransfer() {
    jdbcTransaction.JdbcTransfer();
  }

  @Test
  void dataSourceTransfer() {
    jdbcTransaction.dataSourceTransfer();
  }
}