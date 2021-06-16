package junit.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@Slf4j
@RunWith(BlockJUnit4ClassRunner.class)
public class TestJunitSimpleStruct {

    @Test
    public void testMax() {
        int max = Math.max(2, 5);
        int expected = 5;

        log.info("@Test max(): " + max + " = " + expected);
        Assert.assertEquals(expected, max);
    }
}
