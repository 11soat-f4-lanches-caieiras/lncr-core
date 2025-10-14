package br.com.tp.lncr.core.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodItemExceptionTest {
    @Test
    void testMessageAndCode() {
        FoodItemException ex = new FoodItemException("msg", 789);
        assertEquals("msg", ex.getMessage());
        assertEquals(789, ex.getCode());
    }
}

