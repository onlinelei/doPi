package junit.junit5.assetExample;

import junit.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

public class TestJunit5Assert {

    private Dog dog = new Dog("keji", 14.5f);

    @Test
    @DisplayName("测试捕获的异常")
    void assertThrowsException() {
        String str = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }

    @Test
    @DisplayName("多个断言")
    void standardAssertions() {
        assertEquals(2, 2);
        assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated -- to avoid constructing complex messages unnecessarily.");
    }

    @Test
    @DisplayName("组断言测试")
    void groupedAssertions() {
        // 只要有一个失败全部失败
        assertAll("dog",
                () -> assertEquals("keji", dog.getName()),
                () -> assertEquals(14.5f, dog.getWeight())
        );
    }

    @Test
    @DisplayName("嵌套组断言测试")
    void dependentAssertions() {
        assertAll("properties",
                () -> {
                    String name = dog.getName();
                    assertNotNull(name);

                    // 上面的断言执行成功下面的才会执行
                    assertAll("name",
                            () -> assertTrue(name.startsWith("k")),
                            () -> assertTrue(name.endsWith("i"))
                    );
                },
                () -> {
                    // 分组并行处理断言
                    Float weight = dog.getWeight();
                    assertNotNull(weight);

                    // 上面的断言执行成功下面的才会执行
                    assertAll("weight",
                            () -> assertFalse(weight.isNaN()),
                            () -> assertEquals(14.5f, weight)
                    );
                }
        );
    }

    @Test
    @DisplayName("异常断言测试")
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    @DisplayName("超时断言测试")
    void timeoutNotExceeded() {
        assertTimeout(ofMinutes(1), () -> {
            Thread.sleep(1000);
        });
    }

    @Test
    @DisplayName("超时测试并返回结果断言测试")
    void timeoutNotExceededWithResult() {
        String actualResult = assertTimeout(ofMinutes(1), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    @DisplayName("超时方法测试,带返回结果")
    void timeoutNotExceededWithMethod() {
        String actualGreeting = assertTimeout(ofMinutes(2), TestJunit5Assert::greeting);
        assertEquals("hello world!", actualGreeting);
    }

    private static String greeting() {
        return "hello world!";
    }

}
