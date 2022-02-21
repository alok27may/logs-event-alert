package com.logs.alert.dao;

import com.logs.alert.model.EventAlert;

import java.util.List;

public interface EventAlertDAO {
    boolean storeEventAlert(List<EventAlert> eventAlerts);
}
