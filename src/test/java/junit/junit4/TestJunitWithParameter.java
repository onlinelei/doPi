package junit.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestJunitWithParameter {

    private int expected;
    private int first;
    private int second;

    public TestJunitWithParameter(int expectedResult, int firstNumber, int secondNumber) {
        this.expected = expectedResult;
        this.first = firstNumber;
        this.second = secondNumber;
    }

    @Parameters
    public static Collection addedNumbers() {
        return Arrays.asList(new Integer[][]{{3, 1, 2}, {5, 2, 3}, {7, 3, 4}, {9, 4, 5},});
    }

    @Test
    public void sum() {
        System.out.println("Addition with parameters : " + first + " and " + second + " expected = " + expected);
        Assert.assertEquals(expected, Math.addExact(first, second));
    }

}
