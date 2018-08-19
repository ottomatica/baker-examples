package edu.ncsu.csc.coffee_maker.models.persistent;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import edu.ncsu.csc.coffee_maker.util.HibernateUtil;

/**
 * The common super-class for all database entities. This is done to centralize
 * the logic for database saves and retrieval. When using this class, you should
 * create an overridden getAll() method in the subclasses that passes in their
 * class type to the DomainObject.getAll(Class cls) method so that it can
 * perform the database retrieval.
 *
 * The DomainObject (this class) itself isn't persisted to the database, but it
 * is included here as it is the root class for all persistent classes.
 *
 * Where applicable (ie, on retrieval requests) we use the @Transactional (
 * readOnly = true ) annotation which has the potential to get better
 * performance out of the underlying database system. It is not _required_ but
 * is better to have it than not.
 *
 * @author Kai Presler-Marshall
 *
 * @param <D>
 *            Subtype of DomainObject in question
 */
@SuppressWarnings ( { "unchecked", "rawtypes" } ) // generally a bad idea but
                                                  // Hibernate returns a List<?>
                                                  // that we want to cast
public abstract class DomainObject <D extends DomainObject<D>> {

    /**
     * Lots of DomainObjects are retrieved by ID. This way we get compile-time
     * errors of typos
     */
    static final protected String ID = "id";

    /**
     * Performs a getAll on the subtype of DomainObject in question. The
     * resulting list can then be streamed and filtered on any parameters
     * desired
     *
     * @param cls
     *            class to find DomainObjects for
     * @return A List of all records for the selected type.
     */
    protected static List< ? extends DomainObject> getAll ( final Class cls ) {
        List< ? extends DomainObject> results = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            results = session.createCriteria( cls ).list();
        }
        finally {
            try {
                session.getTransaction().commit();
                session.close();
            }
            catch ( final Exception e ) {
                e.printStackTrace( System.out );
                // Continue
            }

        }

        return results;
    }

    /**
     * When we want to perform an update, rather than deleting and re-creating,
     * we can perform a copyFrom instead. This is advantageous because it's
     * faster and won't break references.
     *
     * @param other
     *            Object to copy from
     * @param includeId
     *            Whether to copy the ID
     */
    public void copyFrom ( final DomainObject other, final Boolean includeId ) {
        if ( !this.getClass().equals( other.getClass() ) ) {
            throw new IllegalArgumentException( "Cannot copy between different types!" );
        }
        final List<Field> fields = Arrays.asList( this.getClass().getDeclaredFields() );
        try {
            for ( final Field f : fields ) {
                final Integer modifiers = f.getModifiers();
                if ( Modifier.isFinal( modifiers ) ) {
                    continue;
                }

                f.setAccessible( true );
                boolean id = false;
                final List<Annotation> annotations = Arrays.asList( f.getAnnotations() );
                for ( final Annotation annotation : annotations ) {
                    if ( annotation.annotationType().equals( javax.persistence.Id.class ) ) {
                        id = true;
                    }
                }
                if ( ( id && includeId ) || !id ) {
                    f.set( this, f.get( other ) );
                }

            }

        }
        catch ( final Exception e ) {
            throw new IllegalArgumentException( e );
        }

    }

    /**
     * Method for retrieving a subset of the DomainObjects from the database.
     * Requires the sub-class of DomainObject to retrieve and a list of what
     * criteria to retrieve by. Think of this as SQL similar to: `WHERE
     * condition1=value1 AND condition2=value2`. If you require an OR clause,
     * that must be created as a single Criterion that is passed as an element
     * in this list; all of the Criterion provided are AND'ed together into the
     * clause that is executed.
     *
     * @param cls
     *            Subclass of DomainObject to retrieve
     * @param criteriaList
     *            List of Criterion to AND together and search by
     * @return The resulting list of elements found
     */
    protected static List< ? extends DomainObject> getWhere ( final Class cls, final List<Criterion> criteriaList ) {
        final Session session = HibernateUtil.getSessionFactory().openSession();

        List< ? extends DomainObject> results = null;
        try {
            session.beginTransaction();
            final Criteria c = session.createCriteria( cls );
            for ( final Criterion criterion : criteriaList ) {
                c.add( criterion );
            }
            results = c.list();
        }
        finally {
            try {
                session.getTransaction().commit();
                session.close();
            }
            catch ( final Exception e ) {
                e.printStackTrace( System.out );
                // Continue
            }
        }

        return results;
    }

    /**
     * Provides the ability to quickly delete all instances of the current
     * class. Useful for clearing out data for testing or regeneration.
     * Visibility is set to protected to force subclasses of DomainObject to
     * override this.
     *
     * @param cls
     *            class to delete instances of
     */
    public static void deleteAll ( final Class cls ) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final List<DomainObject> instances = session.createCriteria( cls ).list();
        for ( final DomainObject d : instances ) {
            session.delete( d );
        }
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Saves the DomainObject into the database. If the object instance does not
     * exist a new record will be created in the database. If the object already
     * exists in the DB, then the existing record will be updated.
     */
    public void save () {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate( this );
        session.getTransaction().commit();
        session.close();

    }

    /**
     * Deletes the selected DomainObject from the database. This is operation
     * cannot be reversed.
     */
    public void delete () {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete( this );
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Retrieves a specific instance of the DomainObject subtype by its
     * persistent ID
     *
     * @param cls
     *            class to retrieve instance of by id
     * @param id
     *            id of object
     * @return object with given id
     */
    public static DomainObject getById ( final Class cls, final Object id ) {
        DomainObject obj;
        try {
            obj = (DomainObject) cls.newInstance();
        }
        catch ( final Exception e ) {
            return null;
        }
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.load( obj, (Serializable) id );
        session.getTransaction().commit();
        session.close();
        return obj;
    }

    /**
     * Ability to get a DomainObject of any type by any field/value provided.
     * Will return null if the field is not valid for the DomainObject type or
     * no record exists with the value provided.
     *
     * @param cls
     *            class of DomainObject
     * @param field
     *            The field of the object (ie, name, id, etc) requested
     * @param value
     *            The value for the field in question
     * @return object associated with class and field
     */
    public static DomainObject getBy ( final Class cls, final String field, final String value ) {
        final List<Field> fields = Arrays.asList( cls.getDeclaredFields() );
        for ( final DomainObject d : getAll( cls ) ) {
            for ( final Field f : fields ) {
                f.setAccessible( true );
                try {
                    if ( f.get( d ).equals( value ) ) {
                        return d;
                    }
                }
                catch ( final Exception e ) {
                    // Ignore exception
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the ID of the DomainObject. May be a numeric ID assigned by the
     * database or another primary key that is user-assigned
     *
     * @return ID of the DomainObject.
     */
    abstract public Serializable getId ();

    /*
     * All of these are useful for creating the Criterion used to retrieve
     * DomainObjects from the database.
     */

    /**
     * Wrap a single Criterion into a SingletonList to pass it to a getWhere()
     * method
     *
     * @param c
     *            The Criterion to wrap
     * @return The List that results
     */
    protected static List<Criterion> createCriterionList ( final Criterion c ) {
        return Collections.singletonList( c );
    }

    /**
     * Creates a Criterion and wraps it in a SingletonList. Works like
     * {@link #createCriterion}, but the result comes out as a List to pass to
     * getWhere directly.
     *
     * @param field
     *            The field to create a restriction on
     * @param value
     *            The value to compare against
     * @return The List that results from creating a Criterion from these values
     *         and wrapping it
     */
    protected static List<Criterion> eqList ( final String field, final Object value ) {
        return createCriterionList( eq( field, value ) );
    }

    /**
     * Create an equals-relation Criterion between the field and the value
     * provided. This is used to retrieve DomainObjects from the database. Note
     * that the value provided must equal (value _and_ type) of the records that
     * are being retrieved. For example, to retrieve an OfficeVisit by the
     * Patient, you must pass in their User object, not their _username_ even
     * though the username is what goes in the table in the database.
     *
     * @param field
     *            The field to create a restriction on
     * @param value
     *            The value to compare against
     * @return The Criterion to create from these values
     */
    protected static Criterion eq ( final String field, final Object value ) {
        return Restrictions.eq( field, value );
    }

    /**
     * Creates a between-relation Criterion between the field and two values
     * provided.
     *
     * @param field
     *            Field to check equivalence on
     * @param lbound
     *            Lower bound for the range
     * @param ubound
     *            Upper bound for the range
     * @return Criterion created
     */
    protected static Criterion bt ( final String field, final Object lbound, final Object ubound ) {
        return Restrictions.between( field, lbound, ubound );
    }

}
