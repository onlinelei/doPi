package junit.AssertJ;

import com.google.common.collect.Maps;
import junit.Dog;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class TestAssertJExample {
  @Test
  public void testString() {
    String actual = "test";
    // 字符串断言
    assertThat("test")
        .as("字符串断言描述")
        .isNotBlank() // 是否为" "字符串
        .isSubstringOf("test1") // 是否为test1的一部分
        .isSameAs("test") // 对象内元素是否相等
        .isNotEmpty() // 是否为空字符串
        .isEqualTo("test") // 是否相等
        .isEqualToIgnoringCase("Test") // 是否相等（忽略大小写）
        .isExactlyInstanceOf(String.class) // 是否是实例
        .isIn(Arrays.asList("test", "hello")) // 是否在列表中
        .isIn("test", "hello") // 是否在参数列表中
        .isInstanceOfAny(String.class, Integer.class) // 是否是实例中任何一个
        .isNotNull() // 是否不为空
        .contains("es") // 是否包含es子串
        .startsWith("te") // te开始
        .endsWith("st") // st结束
        .matches(".e.t"); // 是否匹配 .e.t 格式
    assertThat("").isNullOrEmpty();
  }

  @Test
  public void testInteger() {
    Integer actual = 100;
    // 数字断言
    assertThat(actual)
        .as("数字断言描述").isEqualTo(100) // 是否相等
        .isBetween(0, 300) // 是否在0，300之间
        .isNotNull() // 是否非空
        .isNotZero() // 是否不等于0
        .isGreaterThanOrEqualTo(80) // 是否大约等于80
        .isLessThan(200) // 是否小于200
        .isPositive() // 是否是正数
        .isNotNegative() // 是否是非负数
        .isIn(Arrays.asList(100, 200)) // 是否在列表中
        .isInstanceOf(Integer.class); // 是否是Integer类型
  }

  @Test
  public void testDate() {
    Date actual = new Date();
    // 日期断言
    assertThat(actual)
        .as("日期断言描述")
        .isAfter("2020-12-12") // 是否在日期之后
        .isAfterYear(2018) // 是否在某年之后
        .isBetween("2020-12-12", "2021-06-01") // 是否在日期之间
        .isEqualToIgnoringHours(new Date().toLocaleString()) // 是否在忽略小时的情况下相等
        .isExactlyInstanceOf(Date.class) // 是否是 Date 的实例
        .isInSameHourAs(new Date()) // 小时是否相同
        .isInThePast() // 是否已经过时
        .isToday(); // 是否是今天
  }

  @Test
  public void testArray() {
    List<String> list = Arrays.asList("world", "hello");
    // 列表断言
    assertThat(list)
        .as("列表断言描述")
        .isNotEmpty()
        .isNotNull()
        .isInstanceOf(List.class)
        .isSubsetOf("hello", "world") // 是否包含子array {"hello", "world"}
        .contains("hello") // 是否包含
        .containsOnlyOnce("world") // 是否只包含一个
        .startsWith("world") // 第一个是否是
        .endsWith("hello"); // 最后一个是否是
  }

  @Test
  public void testMap() {
    Map<String, Integer> foo = Maps.newHashMap();
    foo.put("A", 1);
    foo.put("B", 2);
    foo.put("C", 3);
    // 字典断言
    assertThat(foo)
        .as("字典断言描述")
        .isNotNull() // 是否不为空
        .isNotEmpty() // 是否size为0
        .hasSize(3) // size是否为3
        .contains(entry("A", 1)) // 是否包含entry
        .containsKeys("A") // 是否包含key
        .containsValue(1); // 是否包含value
  }


  @Test
  public void testObject() {
    Dog keji = new Dog("keji", 5.14f);
    Dog kejiClone = new Dog("keji", 5.14f);
    Dog kejiCLone2 = keji;

    assertThat(keji).isNotEqualTo(kejiClone);
    assertThat(keji).usingRecursiveComparison().isEqualTo(kejiClone);
    assertThat(keji.getName()).isEqualTo(kejiClone.getName());
    assertThat(keji.getName()).startsWith("ke").endsWith("ji").isEqualToIgnoringCase("KEJI");
    assertThat(keji.getWeight()).as("check %s's weight", keji.getName()).isEqualTo(5.14f);

    // 对象断言
    assertThat(keji)
        .as("对象断言描述")
        .isEqualToComparingFieldByField(kejiClone) //user1的每个字段是否与user2相同
        .isExactlyInstanceOf(Dog.class) //user1是否是User类的对象
        .isSameAs(kejiCLone2) //是否是同一个对象
        .isNotNull() //是否非空
        .hasFieldOrProperty("name") //是否含有name字段
        .hasFieldOrPropertyWithValue("weight", 5.14f); //是否含有age字段，且值为12

  }

}
