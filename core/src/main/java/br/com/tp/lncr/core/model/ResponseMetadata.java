package br.com.tp.lncr.core.model;

import java.time.Instant;
import java.util.UUID;

public class ResponseMetadata {
    private String _traceId;
    private String _timestamp;
    private String _message;

    public ResponseMetadata() {
        this._traceId = UUID.randomUUID().toString();
        this._timestamp = Instant.now().toString();
        this._message = null;
    }

    public ResponseMetadata(String _traceId, String _timestamp, String _message) {
        this._traceId = _traceId;
        this._timestamp = _timestamp;
        this._message = _message;
    }

    public ResponseMetadata(String _traceId, String _timestamp) {
        this._traceId = _traceId;
        this._timestamp = _timestamp;
    }

    public String getTraceId() {
        return _traceId;
    }

    public void setTraceId(String _traceId) {
        this._traceId = _traceId;
    }

    public String getTimestamp() {
        return _timestamp;
    }

    public void setTimestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String _message) {
        this._message = _message;
    }
}
