package junit.springTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import win.suroot.dopi.DoPiApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DoPiApplication.class)
//@Transactional(transactionManager = "asterTransactionManager")
public class BaseSpringJunit4ClassTest {

  @Test
  public void test() {
  }
}
