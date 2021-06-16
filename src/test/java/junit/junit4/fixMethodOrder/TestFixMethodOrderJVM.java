package junit.junit4.fixMethodOrder;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@Slf4j
@FixMethodOrder(MethodSorters.JVM)
public class TestFixMethodOrderJVM {
  @Test
  public void testAddAndGet() {
    log.info("test 'addBean' and 'getBean' ");
  }

  @Test
  public final void testSearch() {
    log.info("test search CODE from JNI memory...");
  }

  @Test
  public final void testRemove() {
    log.info("test remove CODE from JNI memory...");
  }

}
