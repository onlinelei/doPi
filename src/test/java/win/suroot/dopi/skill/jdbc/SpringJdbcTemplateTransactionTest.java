package win.suroot.dopi.skill.jdbc;

import win.suroot.dopi.DoPiApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class SpringJdbcTemplateTransactionTest extends DoPiApplicationTests {


  @Autowired
  private SpringJdbcTemplateTransaction springJdbcTemplateTransaction;

  @Test
  public void testJdbcTemplate() {
    springJdbcTemplateTransaction.jdbcTemplate();
  }

  @Test
  void jdbcTemplateTransaction() {
    springJdbcTemplateTransaction.springTransaction();
  }

  @Test
  void aopSpringTransaction() {
    springJdbcTemplateTransaction.aopSpringTransaction();
  }
}