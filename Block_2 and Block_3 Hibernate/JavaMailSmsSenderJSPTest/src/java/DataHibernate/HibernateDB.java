package DataHibernate;

import java.io.File;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.Query;
import java.util.List;

public class HibernateDB {
  public boolean userIsAuthorized(String username, String pass) {
    java.util.Locale locale = java.util.Locale.getDefault();
    java.util.Locale.setDefault(java.util.Locale.ENGLISH);
    SessionFactory mFctory;
    try {
      mFctory = new Configuration().configure(new File("D:\\Programming\\Java Exam\\EXAM\\Block_2 and Block_3 Hibernate\\JavaMailSmsSenderJSPTest\\src\\resources\\hibernate.cfg.xml")).buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Couldn't create session factory." + ex);
      throw new ExceptionInInitializerError(ex);
    }
    Session session = null;
    Transaction tx = null;
    //
    session = mFctory.openSession();
    Query query = session
        .createQuery("SELECT us FROM User us WHERE us.userName = '" + username + "' AND us.pass = '" + pass + "'");

    return !query.list().isEmpty();
  }

}
