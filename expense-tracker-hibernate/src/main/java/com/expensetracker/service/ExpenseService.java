package com.expensetracker.service;

import com.expensetracker.model.Transaction;
import com.expensetracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction as HibTransaction;

import java.time.LocalDate;
import java.util.List;

public class ExpenseService {

    public Transaction addTransaction(String desc, Double amount, LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        HibTransaction tx = null;
        Transaction t = null;
        try {
            tx = session.beginTransaction();
            t = new Transaction(desc, amount, date);
            session.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return t;
    }

    public List<Transaction> listAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from Transaction", Transaction.class).list();
        } finally {
            session.close();
        }
    }

    public Transaction findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Transaction.class, id);
        } finally {
            session.close();
        }
    }

    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        HibTransaction tx = null;
        try {
            tx = session.beginTransaction();
            Transaction t = session.get(Transaction.class, id);
            if (t != null) session.remove(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
