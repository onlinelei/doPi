package win.suroot.dopi.skill.jdbc.rollback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Component
public class TransactionRollBackOnly {

  @Autowired
  @Qualifier("masterJdbcTemplate")
  private JdbcTemplate jdbcTemplate;
  @Autowired
  @Qualifier("masterDataSourceTransactionManager")
  private DataSourceTransactionManager transactionManager;
  @Autowired
  private RollBackOnlyService rollBackOnlyService;


  public void springTransaction() {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = transactionManager.getTransaction(def);

    try {
      String sql1 = "update t_user set money = money + 50 where id = 1";
      String sql2 = "update t_user set money = money - 50 where id = 2";
      jdbcTemplate.execute(sql1);
      jdbcTemplate.execute(sql2);

      log.info("rollback only status: {}", status.isRollbackOnly());
      status.setRollbackOnly();
      log.info("rollback only status: {}", status.isRollbackOnly());

      // commit.
      transactionManager.commit(status);
    } catch (Exception e) {
      log.error("error", e);
      transactionManager.rollback(status);
    }
  }

  @Transactional
  public void rollBackTransaction() {
    try {
      rollBackOnlyService.rollBackTransaction();
    } catch (Exception e) {
      log.error("error", e);
//      throw e;
//      rollback
    }
//    do something...

  }

}
