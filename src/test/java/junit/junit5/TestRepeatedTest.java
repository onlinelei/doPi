package junit.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class TestRepeatedTest {
  @DisplayName("重复测试")
  @RepeatedTest(value = 3)
  public void testRepeatedTest() {
    System.out.println("执行测试");
  }

  @DisplayName("自定义名称重复测试")
  @RepeatedTest(value = 3, name = "{displayName} 第 {currentRepetition} 次")
  public void testRepeatedTest2() {
    System.out.println("执行测试");
  }
}
