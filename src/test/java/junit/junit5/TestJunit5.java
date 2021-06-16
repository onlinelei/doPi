package junit.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@DisplayName("我的第一个测试用例")
public class TestJunit5 {
    @BeforeAll
    public static void init() {
        log.info("@BeforeAll 初始化数据");
    }

    @AfterAll
    public static void cleanup() {
        log.info("@AfterAll 清理数据");
    }

    @BeforeEach
    public void tearup() {
        log.info("@BeforeEach 当前测试方法开始");
    }

    @AfterEach
    public void tearDown() {
        log.info("@AfterEach 当前测试方法结束");
    }

    @DisplayName("我的第一个测试😄")
    @Test
    void testFirstTest() {
        log.info("@Test 我的第一个测试开始测试");
    }

    @DisplayName("我的第二个测试😄")
    @Test
    void testSecondTest() {
        log.info("@Test 我的第二个测试开始测试");
    }

    @Test
    @Disabled
    @DisplayName("我的第三个测试😱")
    void testDisableTest() {
        log.info("@Test 我的第三个测试开始测试");
    }
}
