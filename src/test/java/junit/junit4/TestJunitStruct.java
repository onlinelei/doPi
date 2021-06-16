package junit.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@Slf4j
@RunWith(BlockJUnit4ClassRunner.class)
public class TestJunitStruct {

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        log.info("@BeforeClass executed before all test ------");
    }

    @Before
    public void executedBeforeEach() {
        log.info("@Before executed before each test -------");
    }

    @AfterClass
    public static void onceExecutedAfterAll() {
        log.info("@AfterClass executed after finish all test ------");
    }

    @After
    public void executedAfterEach() {
        log.info("@After executed after each test --------");
    }

    @Test(expected = Exception.class, timeout = 1000)
    public void testMethod1() throws Exception {
        log.info("@Test test Meth 1 --------");
        throw new Exception("error");
    }

    @Test(expected = Exception.class, timeout = 1000)
    public void testMeth2() throws Exception {
        log.info("@Test test Meth 2 --------");
        throw new Exception("error");
    }

    @Ignore("skip this test method")
    public void testIgnore() {
        log.info("@Ignore ignore test ");
    }
}
