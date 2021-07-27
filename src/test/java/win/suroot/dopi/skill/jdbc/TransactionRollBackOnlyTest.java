package win.suroot.dopi.skill.jdbc;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import win.suroot.dopi.DoPiApplicationTests;
import win.suroot.dopi.skill.jdbc.rollback.TransactionRollBackOnly;

class TransactionRollBackOnlyTest extends DoPiApplicationTests {

  @Autowired
  private TransactionRollBackOnly transactionRollBackOnly;

  @Test
  public void springTransaction() {
    transactionRollBackOnly.springTransaction();
  }

  @Test
  public void rollBackTransaction() {
    transactionRollBackOnly.rollBackTransaction();
  }

}