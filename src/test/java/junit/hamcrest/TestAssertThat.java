package junit.hamcrest;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;


@Slf4j
public class TestAssertThat {

  @Test
  public void testAssertThatAnyThing() {
    Integer actual = 1000;
    Assert.assertThat(actual, CoreMatchers.anything());
  }

  @Test
  public void testAssertThatAnyValue() {
    Integer actual = 1000;
    Assert.assertThat(actual, CoreMatchers.any(Integer.class));
  }

  @Test
  public void testAssertIs() {
    boolean expected = true;
    boolean actual = true;
    Assert.assertThat(actual, CoreMatchers.is(expected));
  }

  @Test
  public void testAssertThatEqual() {
    Integer expected = 1000;
    Integer actual = 1000;
    Assert.assertThat(actual, CoreMatchers.equalTo(expected));
  }

  @Test
  public void testAssertThatContainsString() {
    String expected = "hello";
    String actual = "hello world";
    Assert.assertThat(actual, CoreMatchers.containsString(expected));
  }

  @Test
  public void testAssertThatWrapsMatchers() {
    String expected = "hello";
    String expected1 = "world";
    String actual = "hello world";
    String description = "wraps matchers";
    Matcher<String> stringMatcher = CoreMatchers
        .describedAs(description, CoreMatchers.containsString(expected), CoreMatchers.containsString(expected1));
    Assert.assertThat(actual, stringMatcher);
  }

  @Test
  public void testAssertThatEndWith() {
    String expected = "world";
    String actual = "hello world";
    Assert.assertThat(actual, CoreMatchers.endsWith(expected));
  }

  @Test
  public void testAssertThat1() {
    Integer expected = 1000;
    Integer actual = 1001;
    Assert.assertThat(actual, CoreMatchers.not(CoreMatchers.equalTo(expected)));
  }

}
