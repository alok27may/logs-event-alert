package com.logs.alert.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logs.alert.model.EventLog;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EventLogParser {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssz";

    public List<EventLog> parseEventLog(final String fileName) throws Exception {
        final List<EventLog> eventLogs = new ArrayList<>();
        File file = new File(fileName);
        LineIterator it = FileUtils.lineIterator(file);
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                Gson gson = getGson();
                final EventLog eventLog = gson.fromJson(line, EventLog.class);
                eventLogs.add(eventLog);
            }
        } catch (final Exception e) {
            throw new Exception("unable to prcess file : " + fileName, e);
        }
        finally {
            it.close();
        }
        return eventLogs;
    }

    private static Gson getGson() {
        return new GsonBuilder().setDateFormat(DATE_FORMAT).create();
    }

}
