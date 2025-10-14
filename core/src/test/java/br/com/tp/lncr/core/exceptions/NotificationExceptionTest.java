package br.com.tp.lncr.core.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationExceptionTest {
    @Test
    void testMessageAndCode() {
        NotificationException ex = new NotificationException("msg", 654);
        assertEquals("msg", ex.getMessage());
        assertEquals(654, ex.getCode());
    }
}

