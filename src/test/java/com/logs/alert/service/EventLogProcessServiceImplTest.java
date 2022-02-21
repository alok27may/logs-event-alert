package com.logs.alert.service;


import com.logs.alert.dao.EventAlertDAO;
import com.logs.alert.model.EventAlert;
import com.logs.alert.model.EventLog;
import com.logs.alert.parser.EventLogParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class EventLogProcessServiceImplTest {

    @Mock private EventAlertDAO eventAlertDAO;
    @Mock private EventLogParser eventLogParser;

    @InjectMocks private EventLogProcessService eventLogProcessService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldProcessFile() throws Exception {
        final String filename = "RandomStringUtil";
        final List<EventLog> eventLogs = getEventLogs();
        final List<EventAlert> eventAlerts = getEventAlerts();

        Mockito.when(eventLogParser.parseEventLog(filename)).thenReturn(eventLogs);
        Mockito.when(eventAlertDAO.storeEventAlert(eventAlerts)).thenReturn(true);

        final boolean result = eventLogProcessService.processEventLog(filename);

        Assert.assertTrue(result);
    }

    @Test
    public void shouldNotProcessFileIfParserReturnEmptyList() throws Exception {
        final String filename = "RandomStringUtil";

        Mockito.when(eventLogParser.parseEventLog(filename)).thenReturn(null);

        final boolean result = eventLogProcessService.processEventLog(filename);

        Assert.assertFalse(result);
    }


    private List<EventAlert> getEventAlerts() {
        final EventAlert eventAlert = createEventAlert();

        final List<EventAlert> eventAlerts = new ArrayList<>();
        eventAlerts.add(eventAlert);
        return eventAlerts;
    }

    private EventAlert createEventAlert() {
        final String id = "dfksadfjklas";
        final String host = "server.com";
        final String type = "APPLICATION_LOG";
        final long duration = 2323242342l;
        final EventAlert eventAlert = new EventAlert(id, duration, true, type, host);
        return eventAlert;
    }

    private List<EventLog> getEventLogs() {
        final EventLog eventLog = createEventLog();

        final List<EventLog> eventLogs = new ArrayList<>();
        eventLogs.add(eventLog);
        return eventLogs;
    }

    private EventLog createEventLog() {
        final String id = "dfksadfjklas";
        final String state = "STARTED";
        final String host = "server.com";
        final String type = "APPLICATION_LOG";
        final long timestamp = 2323242342l;
        return new EventLog(id, state, host, type, timestamp);
    }
}