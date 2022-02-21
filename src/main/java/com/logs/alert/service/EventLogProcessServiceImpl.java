package com.logs.alert.service;

import com.logs.alert.dao.EventAlertDAO;
import com.logs.alert.dao.EventAlertDAOImpl;
import com.logs.alert.model.EventAlert;
import com.logs.alert.model.EventLog;
import com.logs.alert.parser.EventLogParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventLogProcessServiceImpl implements EventLogProcessService {

    private EventAlertDAO eventAlertDAO;
    private EventLogParser eventLogParser;

    public EventLogProcessServiceImpl() {
        this.eventAlertDAO = new EventAlertDAOImpl();
        this.eventLogParser = new EventLogParser();
    }

    public boolean processEventLog(final String filename) throws Exception {
        final List<EventLog> eventLogs = eventLogParser.parseEventLog(filename);

        if (eventLogs == null || eventLogs.isEmpty()) {
            return false;
        }
        final List<EventAlert> eventAlerts = getEventAlertFromLog(eventLogs);
        return eventAlertDAO.storeEventAlert(eventAlerts);
    }

    private List<EventAlert> getEventAlertFromLog(final List<EventLog> eventLogs) {
        Map<String, EventLog> eventDetailMap = new HashMap<>();
        List<EventAlert> eventAlerts = new ArrayList<>();

        for (EventLog currentEvent : eventLogs) {
            final EventLog previousEvent = eventDetailMap.get(currentEvent.getId());
            if (previousEvent != null) {
                Long prevTime = previousEvent.getTimestamp();
                Long currTime = currentEvent.getTimestamp();

                if (prevTime > currTime && (prevTime - currTime > 4)) {
                    eventAlerts.add(createAlert(currentEvent, (prevTime - currTime)));
                } else if (currTime > prevTime && (currTime - prevTime > 4)) {
                    eventAlerts.add(createAlert(currentEvent, (currTime - prevTime)));
                }
            } else {
                eventDetailMap.put(currentEvent.getId(),
                        currentEvent);
            }
        }

        return eventAlerts;
    }

    private EventAlert createAlert(final EventLog eventLog, final long duration) {
        return new EventAlert(eventLog.getId(), duration, true, eventLog.getType(), eventLog.getHost());
    }

}
