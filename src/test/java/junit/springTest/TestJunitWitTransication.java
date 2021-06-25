package junit.springTest;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import win.suroot.dopi.service.VerifyTableService;

public class TestJunitWitTransication extends BaseSpringJunit4ClassTest {

  @Autowired
  private VerifyTableService verifyTableService;

  @Disabled
  @Test
  @Rollback
  public void testTransactionalRollback() {
    verifyTableService.testMysql();
  }

  @Disabled
  @Test
  public void testTransactionalCommit() {
    verifyTableService.testMysql();
  }
}
