package edu.ncsu.csc.coffee_maker.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * A utility class for setting up the Hibernate SessionFactory
 *
 * @author Elizabeth Gilbert
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory () {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch ( final Throwable ex ) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println( "Initial SessionFactory creation failed." + ex );
            throw new ExceptionInInitializerError( ex );
        }
    }

    /**
     * Returns the Hibernate SessionFactory.
     *
     * @return session factory
     */
    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }

    /**
     * Shuts down the connection to the database.
     */
    public static void shutdown () {
        // Close caches and connection pools
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }
}
