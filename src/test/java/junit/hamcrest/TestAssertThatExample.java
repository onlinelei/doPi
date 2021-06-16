package junit.hamcrest;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TestAssertThatExample {
  @Test
  public void allOfExampleShowsAllMatchersMustAllBeTrue() throws Exception {
    assertThat("Hello", is(allOf(notNullValue(), instanceOf(String.class), equalTo("Hello"))));
  }

  @Test
  public void allOfExampleShowsFailingIfOneMatcherDoesNotMatch() throws Exception {
    assertThat("Hello", is(not(allOf(notNullValue(), instanceOf(Integer.class)))));
  }

  @Test
  public void anyExampleChecksThatClassIsOfSameType() throws Exception {
    assertThat("Hello", is(any(String.class)));
  }

  @Test
  public void anyExampleShowsStringIsAlsoAnObject() throws Exception {
    assertThat("Hello", is(any(Object.class)));
  }

  @Test
  public void anyOfExampleReturnsTrueIfOneMatches() throws Exception {
    assertThat("Hello", is(anyOf(nullValue(), instanceOf(String.class), equalTo("Goodbye"))));
  }

  @Test
  public void anyOfExampleFailingIfAllMatchersAreFalse() throws Exception {
    assertThat("Hello", is(not(anyOf(nullValue(), instanceOf(Integer.class), equalTo("Goodbye")))));
  }
}
