package junit.AssertJ;


import org.assertj.core.api.Assertions;
import org.junit.Test;

public class TestAssertJSimple {
  @Test
  public void test() {
    Integer actual = 1000;
    Integer expected = 1000;
    Assertions.assertThat(actual).isEqualTo(expected).isIn(1000, 2000, 3000);
  }
}
