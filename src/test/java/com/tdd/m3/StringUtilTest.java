package com.tdd.m3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    public void limitNotReached_StringNotChanged() {
        String input = "The economy is about to"; // length 23
        int limit = 40;

        Assertions.assertEquals("The economy is about to", StringUtil.truncate(input, limit));
    }
}
