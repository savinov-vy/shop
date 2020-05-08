/*

package ru.savinov.spring.study_shop.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.WHERE;

public class CrudApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        SessionFactory factory = new Configuration()
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            System.out.println("================\n==CREATE==\n================");
            session = factory.getCurrentSession(); // открываем сессию
            session.beginTransaction(); // начинаем транзацкию
            Product newProduct = new Product();
            System.out.println("Befor save: " + newProduct);
            session.save(newProduct);
            System.out.println("After save: " + newProduct);
            session.getTransaction().commit();
            System.out.println("After save and commit: " + newProduct);

            System.out.println("================\n==READ==\n================");
            session = factory.getCurrentSession(); // открываем сессию
            session.beginTransaction(); // начинаем транзацкию
            Product productFromDB = session.get(Product.class, 1L);
            System.out.println(productFromDB);
            session.getTransaction().commit();

            System.out.println("================\n==UPDATE==\n================");
            session = factory.getCurrentSession(); // открываем сессию
            session.beginTransaction(); // начинаем транзацкию
            maxId = session.createQuery("SELECT MAX(s.id)) FROM Product s", Long.class);
            Product productForUpdate = session.createQuery("FROM Product s WHERE s.id = :id ", Product.class)
                    .setParameter("id", maxId)
                    .getSingleResult();
            System.out.println("Loaded item with max(id): " + productForUpdate);
            productForUpdate.setPrice(productForUpdate.getPrice() + 100);
            System.out.println("Modifid product: " + productForUpdate);
            session.getTransaction().commit();

            System.out.println("================\n==DELETE==\n================");
            session = factory.getCurrentSession(); // открываем сессию
            session.beginTransaction(); // начинаем транзацкию
            maxId = session.get(Product.class, maxId);
            session.getTransaction().commit();

        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
*/
