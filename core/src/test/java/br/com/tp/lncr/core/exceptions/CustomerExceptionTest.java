package br.com.tp.lncr.core.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerExceptionTest {
    @Test
    void testMessageAndCode() {
        CustomerException ex = new CustomerException("msg", 123);
        assertEquals("msg", ex.getMessage());
        assertEquals(123, ex.getCode());
    }
}

