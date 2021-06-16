package junit.junit4.assertExample;

import junit.Dog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;


@Slf4j
public class TestAssert {
    @Test
    public void testNull() {
        Integer nullValue = null;
        Assert.assertNull(nullValue);
    }

    @Test
    public void testEquals() {
        Dog keji = new Dog("Fido", 5.15f);
        Dog kejiClone = new Dog("Fido", 5.15f);
        String message = "assert equals message";
        Assert.assertNotEquals(message, keji, kejiClone);
    }

    @Test
    public void testSame() {
        Dog keji = new Dog("Fido", 5.15f);
        Dog kejiClone = new Dog("FidoClone", 5.15f);
        Assert.assertNotSame(keji, kejiClone);
    }

    @Test
    public void testTrue() {
        boolean condition = true;
        Assert.assertTrue(condition);
    }

    @Test
    public void testArrayEquals() {
        String[] array1 = {"hello", "junit", "test"};
        String[] array2 = {"hello", "junit", "test"};
        log.info("array1:{}", Arrays.toString(array1));
        log.info("array2:{}", Arrays.toString(array2));
        Assert.assertArrayEquals(array1, array2);
    }

    @Test
    @Ignore
    public void testFail() {
        Assert.fail();
    }
}
