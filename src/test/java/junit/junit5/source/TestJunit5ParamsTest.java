package junit.junit5.source;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class TestJunit5ParamsTest {
  @ParameterizedTest
  @ValueSource(ints = {2, 4, 8})
  void testNumberShouldBeEven(int num) {
    Assertions.assertEquals(0, num % 2);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Effective Java", "Code Complete", "Clean Code"})
  void testPrintTitle(String title) {
    log.info(title);
  }

  @ParameterizedTest
  @EnumSource(TimeUnit.class)
  void testWithEnumSource(TimeUnit timeUnit) {
    Assertions.assertNotNull(timeUnit);
  }

  /**
   * @param timeUnit
   * @EnumSource提供了一个使用Enum常量的简便方法。该注释提供了一个可选的name参数，可以指定使用哪些常量。如果省略，所有的常量将被用在下面的例子中。
   */
  @ParameterizedTest
  @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
  void testWithEnumSourceInclude(TimeUnit timeUnit) {
    Assertions.assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
  }

  /**
   * EnumSource注解还提供了一个可选的mode参数，可以对将哪些常量传递给测试方法进行细化控制。例如，您可以从枚举常量池中排除名称或指定正则表达式，如下例所示。
   *
   * @param timeUnit
   */
  @ParameterizedTest
  @EnumSource(value = TimeUnit.class, mode = EnumSource.Mode.EXCLUDE, names = {"DAYS", "HOURS"})
  void testWithEnumSourceExclude(TimeUnit timeUnit) {
    Assertions.assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    Assertions.assertTrue(timeUnit.name().length() > 5);
  }

  @ParameterizedTest
  @EnumSource(value = TimeUnit.class, mode = EnumSource.Mode.MATCH_ALL, names = "^(M|N).+SECONDS$")
  void testWithEnumSourceRegex(TimeUnit timeUnit) {
    String name = timeUnit.name();
    Assertions.assertTrue(name.startsWith("M") || name.startsWith("N"));
    Assertions.assertTrue(name.endsWith("SECONDS"));
  }

  @ParameterizedTest
  @MethodSource("stringProvider")
  void testWithSimpleMethodSource(String argument) {
    assertNotNull(argument);
  }

  static Stream<String> stringProvider() {
    return Stream.of("foo", "bar");
  }

  @ParameterizedTest
  @MethodSource("range")
  void testWithRangeMethodSource(int argument) {
    assertNotEquals(9, argument);
  }

  static IntStream range() {
    return IntStream.range(0, 20).skip(10);
  }

  /**
   * 多个参数的情况
   *
   * @param str
   * @param num
   * @param list
   */
  @ParameterizedTest
  @MethodSource("stringIntAndListProvider")
  void testWithMultiArgMethodSource(String str, int num, List<String> list) {
    assertEquals(3, str.length());
    assertTrue(num >= 1 && num <= 2);
    assertEquals(2, list.size());
  }

  static Stream<Arguments> stringIntAndListProvider() {
    return Stream.of(
        Arguments.of("foo", 1, Arrays.asList("a", "b")),
        Arguments.of("bar", 2, Arrays.asList("x", "y"))
    );
  }

  @ParameterizedTest
  @ArgumentsSource(MyArgumentsProvider.class)
  void testWithArgumentsSource(String argument) {
    assertNotNull(argument);
  }

  static class MyArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of("foo", "bar").map(Arguments::of);
    }
  }


  @ParameterizedTest
  @CsvSource({"1,One", "2,Two", "3,Three"})
  void testDataFromCsv(long id, String name) {
    log.info("id: {}, name: {}", id, name);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/input.csv", encoding = "UTF-8", delimiter = ',')
  void testDataFromCsvFileSource(long id, String name) {
    log.info("id: {}, name: {}", id, name);
  }


}
