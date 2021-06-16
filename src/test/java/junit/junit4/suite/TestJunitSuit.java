package junit.junit4.suite;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Slf4j
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestJunitSuitSubTest1.class, TestJunitSuitSubTest2.class
})
public class TestJunitSuit {
}
