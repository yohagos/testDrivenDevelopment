package com.tdd.m3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StringUtilTest {

    /*
    * RED steps:
    * 1. Create TestClass and TestMethod with all requirements
    * 2. Create the Class and Methods which will be tested
    * 3. At first fake it (return hard-coded the expected value) if need be
    * */

    /*
    * GREEN steps:
    * 1. Implement the real implementation of the Method which will be tested
    * 2. Re-run test and fix implementation of the Method until it passes
    * */

    @Test
    public void limitReached_StringTruncate() {
        String input = "The economy is about to"; // length 23
        int limit = 11;
        Assertions.assertEquals("The economy...", StringUtil.truncate(input, limit));
    }

    /*@Test
    public void limitNotReached_StringNotChanged() {
        String input = "The economy is about to"; // length 23
        int limit = 40;
        Assertions.assertEquals("The economy is about to", StringUtil.truncate(input, limit));
    }*/

    /*
    * For ZOMBIES, we will write another test
    * */

    /*@Test
    public void limitNotReachedAtBorder_StringNotChanged() {
        String input = "The economy is about to"; // length 23
        int limit = 23;
        // Should the Limit be inclusive or exclusive?
        // example: Sale of shoe's until 20th of month.
        // does it end on 19th (exclusive) or on 20th (inclusive)
        Assertions.assertEquals("The economy is about to", StringUtil.truncate(input, limit));
    }*/

    // Copied second test, to change it to a parameterized Test
    @ParameterizedTest
    @MethodSource("inputOutputLimitProvider")
    public void limitNotReached_StringNotChanged(String input, int limit) {
        Assertions.assertEquals("The economy is about to", StringUtil.truncate(input, limit));
    }

    // @MethodSource will run the method inputOutputLimitProvider and needs a Stream of arguments
    public static Stream<Arguments> inputOutputLimitProvider() {
        String input = "The economy is about to";
        return Stream.of(
                Arguments.of(input, 40),
                Arguments.of(input, input.length())
        );
    }

    /*@Test
    public void invalidInput_isRejected() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> StringUtil.truncate(null, 5));
    }

    @Test
    public void invalidInteger_isRejected() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> StringUtil.truncate("The economy is about to", -8));
    }*/

    @ParameterizedTest
    @MethodSource("invalidArgumentsProvider")
    public void invalidInput_isRejected(String input, int limit) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> StringUtil.truncate(input, limit));
    }

    public static Stream<Arguments> invalidArgumentsProvider() {
        return Stream.of(
                Arguments.of(null, 5),
                Arguments.of("The economy is about to", -5)
        );
    }
}
