package junit.junit5.nestUnit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Slf4j
@DisplayName("内嵌测试类")
public class TestNestUnitTest {

    @BeforeEach
    void init() {
        log.info("测试方法执行前准备");
    }

    @Nested
    @DisplayName("第一个内嵌测试类")
    class FirstNestTest {

        @Test
        @DisplayName("第一个内嵌测试")
        void test() {
            log.info("第一个内嵌测试类执行测试");
        }
    }

    @Nested
    @DisplayName("第二个内嵌测试类")
    class SecondNestTest {
        @Test
        @DisplayName("第二个内嵌测试")
        void test() {
            log.info("第二个内嵌测试类执行测试");
        }
    }
}
