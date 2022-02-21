package com.logs.alert.model;

public class EventAlert {
    private String id;
    private long duration;
    private boolean alert;
    private String type;
    private String host;

    public EventAlert(final String id, final long duration, final boolean alert, final String type, final String host) {
        this.id = id;
        this.duration = duration;
        this.alert = alert;
        this.type = type;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(final boolean alert) {
        this.alert = alert;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }
}
