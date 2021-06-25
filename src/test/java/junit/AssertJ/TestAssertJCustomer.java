package junit.AssertJ;


import junit.Dog;
import org.junit.jupiter.api.Test;

public class TestAssertJCustomer {


  @Test
  public void testCustomerAssert1() {
    Dog dog = new Dog("keji", 5.4f);
    DogAssert.assertThat(dog)
        .withName("keji");
  }


}
