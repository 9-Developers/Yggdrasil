package tech.ninecraft.data;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

class ReflectionTest {
    @Test
    void test() {
        for (final Field field : TestData.class.getDeclaredFields()) {
            System.out.println(boolean.class.getName());
            System.out.printf(
                "%s: %s (%s)%n",
                field.getName(),
                field.getType().getName(),
                field.getType().isPrimitive());
        }
    }

    private record TestData(boolean a, char b, byte c, short d, int e, long f, float g, double h, String i) {
    }
}
