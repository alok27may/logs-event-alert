package com.logs.alert.dao;

import com.logs.alert.model.EventAlert;
import org.hsqldb.jdbc.JDBCDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EventAlertDAOImpl implements EventAlertDAO {

    private static final String EVENT_ALERT_CREATE_SQL = "CREATE TABLE EVENTALERT (id varchar(255), "
            + " type varchar(255), host varchar(255), duration bigint,"
            + " alert varchar(10), primary key(id));";
    private static final String EVENT_ALERT_INSERT_SQL = "INSERT INTO logFileEventsDb.EVENTALERT (id,type,host,duration,alert) %s );";

    @Override
    public boolean storeEventAlert(final List<EventAlert> eventAlerts) {
        if (eventAlerts == null || eventAlerts.isEmpty()) {
            return false;
        }
        try {
            final Connection conn = getConnection();
            createEventAlertTable(conn);
            persistEventAlert(conn, eventAlerts);
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Connection getConnection() throws SQLException {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setDatabase("jdbc:hsqldb:file:logFileEventsDb");
        Connection conn = dataSource.getConnection("SA", "");
        return conn;
    }

    private void persistEventAlert(final Connection conn, final List<EventAlert> eventAlerts) throws SQLException {
        for (EventAlert alert : eventAlerts) {
            final String insertStmt = getInsertQuery(alert);
            conn.prepareCall(insertStmt).close();
        }
    }

    private String getInsertQuery(final EventAlert alert) {
        String values = "VALUES ("
                + alert.getId()
                + ","
                + (alert.getType() != null ? alert.getType()
                : "")
                + ","
                + (alert.getHost() != null ? alert.getHost()
                : "") + "," + alert.getDuration()
                + ",true";

        return String.format(EVENT_ALERT_INSERT_SQL, values);
    }

    private void createEventAlertTable(final Connection conn) throws SQLException {
        final Statement stat = conn.createStatement();
        try {
            stat.executeUpdate("DROP TABLE EVENTALERT");
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        stat.executeUpdate(EVENT_ALERT_CREATE_SQL);
        stat.close();
    }
}
