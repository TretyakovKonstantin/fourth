package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class History {
    private static final int CAPACITY = 10;
    private Deque<Record> records;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("record");

    public History() {
        records = new LinkedList<Record>();
    }

    public Deque<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) {
        records.addFirst(record);
        while (records.size() > CAPACITY) {
            records.removeLast();
        }
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.persist(record);
        entityTransaction.commit();
    }
}
