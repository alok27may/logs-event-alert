package com.logs.alert.model;

public class EventLog {
    private String id;
    private String state;
    private String host;
    private String type;
    private long timestamp;

    public EventLog() {
    }

    public EventLog(final String id, final String state, final String host, final String type, final long timestamp) {
        this.id = id;
        this.state = state;
        this.host = host;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }
}
