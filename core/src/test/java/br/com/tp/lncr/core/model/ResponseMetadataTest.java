package br.com.tp.lncr.core.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMetadataTest {
    @Test
    void testDefaultConstructor() {
        ResponseMetadata meta = new ResponseMetadata();
        assertNotNull(meta.getTraceId());
        assertNotNull(meta.getTimestamp());
        assertNull(meta.getMessage());
    }

    @Test
    void testConstructorWithAllFields() {
        ResponseMetadata meta = new ResponseMetadata("trace", "2025-07-18T12:00:00Z", "msg");
        assertEquals("trace", meta.getTraceId());
        assertEquals("2025-07-18T12:00:00Z", meta.getTimestamp());
        assertEquals("msg", meta.getMessage());
    }

    @Test
    void testConstructorWithoutMessage() {
        ResponseMetadata meta = new ResponseMetadata("trace2", "2025-07-18T13:00:00Z");
        assertEquals("trace2", meta.getTraceId());
        assertEquals("2025-07-18T13:00:00Z", meta.getTimestamp());
        assertNull(meta.getMessage());
    }

    @Test
    void testSettersAndGetters() {
        ResponseMetadata meta = new ResponseMetadata();
        meta.setTraceId("id");
        meta.setTimestamp("2025-07-18T14:00:00Z");
        meta.setMessage("mensagem");
        assertEquals("id", meta.getTraceId());
        assertEquals("2025-07-18T14:00:00Z", meta.getTimestamp());
        assertEquals("mensagem", meta.getMessage());
    }
}

