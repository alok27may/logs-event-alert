package com.logs.alert.service;

import java.io.IOException;

public interface EventLogProcessService {
    boolean processEventLog(String filename) throws Exception;
}
