package com.logs.alert;

import com.logs.alert.service.EventLogProcessService;
import com.logs.alert.service.EventLogProcessServiceImpl;

public class LogsEventAlert {

    private static EventLogProcessService eventLogProcessService = new EventLogProcessServiceImpl();

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Please check the arguments and run again.");
        }

        String filename = args[0];

        try {
            eventLogProcessService.processEventLog(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
