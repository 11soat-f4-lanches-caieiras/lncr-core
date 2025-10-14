package br.com.tp.lncr.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoggerUtilTest {
    @Test
    void testInfoLog() {
        assertDoesNotThrow(() -> LoggerUtil.info("Mensagem de info"));
    }

    @Test
    void testDebugLog() {
        assertDoesNotThrow(() -> LoggerUtil.debug("Mensagem de debug"));
    }

    @Test
    void testErrorLog() {
        assertDoesNotThrow(() -> LoggerUtil.error("Mensagem de erro"));
    }

    @Test
    void testLogWithLevel() {
        assertDoesNotThrow(() -> LoggerUtil.log(LoggerUtil.Level.INFO, "Teste com level"));
    }
}

