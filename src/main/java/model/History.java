package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Deque;
import java.util.LinkedList;

public class History {
    private static final int CAPACITY = 5;
    private Deque<Record> records;
    private double useful =1D;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("record");

    public History() {
        records = new LinkedList<Record>();
    }

    public double getUseful() {
        return useful;
    }

    public void setUseful(double useful) {
        this.useful = useful;
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
