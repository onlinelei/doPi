package junit.junit5.assetExample;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class TestJunitAssumption {

  @Test
  void testOnlyOnCiServer() {
    assumeTrue("mac".equals(System.getenv("ENV")));
  }

  @Test
  void testOnlyOnDeveloperWorkstation() {
    assumeTrue("DEV".equals(System.getenv("ENV")),
        () -> "Aborting test: not on developer workstation");
  }

  @Test
  void testInAllEnvironments() {
    assumingThat("CI".equals(System.getenv("ENV")),
        () -> {
          // perform these assertions only on the CI server
          assertEquals(2, 2);
        });

    // perform these assertions in all environments
    assertEquals("a string", "a string");
  }

  @Test
  @Disabled
  void fail() {
    fail();
  }
}
