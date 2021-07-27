package win.suroot.dopi.skill.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Component
public class SpringJdbcTemplateTransaction {

  @Autowired
  @Qualifier("masterJdbcTemplate")
  private JdbcTemplate jdbcTemplate;
  @Autowired
  @Qualifier("masterDataSourceTransactionManager")
  private DataSourceTransactionManager transactionManager;

  public void jdbcTemplate() {
    String sql1 = "update t_user set money = money + 50 where id = 1";
    String sql2 = "update t_user set money = money - 50 where id = 2";
    jdbcTemplate.execute(sql1);
    jdbcTemplate.execute(sql2);
  }

  public void springTransaction() {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = transactionManager.getTransaction(def);

    try {
      String sql1 = "update t_user set money = money + 50 where id = 1";
      String sql2 = "update t_user set money = money - 50 where id = 2";
      jdbcTemplate.execute(sql1);
      jdbcTemplate.execute(sql2);

      // commit.
      transactionManager.commit(status);
    } catch (Exception e) {
      log.error("error", e);
      transactionManager.rollback(status);
    }
  }

  @Transactional(value = "masterDataSourceTransactionManager",
      propagation = Propagation.REQUIRES_NEW,
      isolation = Isolation.DEFAULT,
      readOnly = false,
      timeout = 3000,
      rollbackFor = Exception.class,
      rollbackForClassName = {"ClassCastException", "NullPointerException"},
      noRollbackFor = IllegalArgumentException.class)
  public void aopSpringTransaction() {
    try {
      String sql1 = "update t_user set money = money + 50 where id = 1";
      String sql2 = "update t_user set money = money - 50 where id = 2";
      jdbcTemplate.execute(sql1);
      jdbcTemplate.execute(sql2);
    } catch (Exception e) {
      log.error("error", e);
      throw e;
    }
  }


}
