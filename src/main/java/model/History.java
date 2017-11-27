package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class History
{
    private static final int CAPACITY = 5;
    private Deque<Record> records;

    public History()
    {
        records = new LinkedList<Record>();
        establishDatabaseConnection();
    }

    private void establishDatabaseConnection()
    {

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setServerName("localhost");
//        dataSource.setDatabaseName("postgres");
//        dataSource.setCurrentSchema("");
//        dataSource.setUser("postgres");
//        dataSource.setPassword("superteam");
//        try {
//            String sql = "CREATE TABLE IF NOT EXISTS record (\n" +
//                         "  id     SERIAL PRIMARY KEY,\n" +
//                         "  x      DOUBLE PRECISION,\n" +
//                         "  y      DOUBLE PRECISION,\n" +
//                         "  r      DOUBLE PRECISION,\n" +
//                         "  result BOOLEAN\n" +
//                         ");\n";
//            databaseConnection = dataSource.getConnection();
//            final PreparedStatement statement = databaseConnection.prepareStatement(sql);
//            statement.execute();
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }

    private static final String PERSISTENCE_UNIT_NAME = "record";
    private static EntityManagerFactory factory;

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
            EntityManager em = factory.createEntityManager();
            em.persist(record);

            em.close();

//            final String sql = "INSERT INTO record (x, y, r, result) VALUES (?, ?, ?, ?)";
//            final PreparedStatement statement = databaseConnection.prepareStatement(sql);
//            statement.setDouble(1, record.getPoint().getY());
//            statement.setDouble(2, record.getPoint().getY());
//            statement.setDouble(3, record.getArea().getRadius());
//            statement.setBoolean(4, record.getResult());
//            statement.execute();
    }
}
