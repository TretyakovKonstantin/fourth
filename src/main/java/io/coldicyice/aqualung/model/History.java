package io.coldicyice.aqualung.model;

import org.postgresql.ds.PGSimpleDataSource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Deque;
import java.util.LinkedList;

@ManagedBean(name = "History", eager = true)
@SessionScoped
public class History
{
    private static final int CAPACITY = 3;

    private Connection databaseConnection;
    private Deque<Record> records;

    public History()
    {
        records = new LinkedList<Record>();
        databaseConnection = null;
        establishDatabaseConnection();
    }

    private void establishDatabaseConnection()
    {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("postgres");
        dataSource.setCurrentSchema("");
        dataSource.setUser("postgres");
        dataSource.setPassword("superteam");
        try {
            String sql = "CREATE TABLE IF NOT EXISTS record (\n" +
                         "  id     SERIAL PRIMARY KEY,\n" +
                         "  x      DOUBLE PRECISION,\n" +
                         "  y      DOUBLE PRECISION,\n" +
                         "  r      DOUBLE PRECISION,\n" +
                         "  result BOOLEAN\n" +
                         ");\n";
            databaseConnection = dataSource.getConnection();
            final PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.execute();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Deque<Record> getRecords()
    {
        return records;
    }

    public void addRecord(Record record)
    {
        records.addFirst(record);
        while (records.size() > CAPACITY) {
            records.removeLast();
        }
        try {
            final String sql = "INSERT INTO record (x, y, r, result) VALUES (?, ?, ?, ?)";
            final PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.setDouble(1, record.getPoint().getY());
            statement.setDouble(2, record.getPoint().getY());
            statement.setDouble(3, record.getArea().getRadius());
            statement.setBoolean(4, record.getResult());
            statement.execute();
        }
        catch (SQLException e) {
            // ignore
        }
    }
}
