package junit.AssertJ;


import junit.Dog;
import org.assertj.core.api.AbstractAssert;

public class DogAssert extends AbstractAssert<DogAssert, Dog> {

    public DogAssert(Dog actual) {
        super(actual, DogAssert.class);
    }

    public static DogAssert assertThat(Dog actual) {
        return new DogAssert(actual);
    }

    public DogAssert withName(String name) {
        isNotNull();
        if (actual.getName() != name) {
            failWithMessage("Expected the dog'name to be <%s> but was <%s>", name, actual.getName());
        }
        return this;
    }
}

