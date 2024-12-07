package com.klef.jfsd.exam.HibernateHQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {
    public static void main(String[] args) {
        // Create configuration and build session factory
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Open session
        Session session = sessionFactory.openSession();

        // Begin transaction
        Transaction transaction = session.beginTransaction();

        try {
            // HQL Update using Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, "Updated Department Name"); // Replace with the new name
            query.setParameter(2, "Updated Location");        // Replace with the new location
            query.setParameter(3, 1);                        // Replace with the target Department ID

            // Execute update
            int result = query.executeUpdate();
            System.out.println(result + " record(s) updated.");

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of failure
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close session and session factory
            session.close();
            sessionFactory.close();
        }
    }
}
