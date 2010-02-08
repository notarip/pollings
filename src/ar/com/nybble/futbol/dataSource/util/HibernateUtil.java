package ar.com.nybble.futbol.dataSource.util;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 *
 * @author msaladino
 */
public class HibernateUtil {
    
    /**
     * Clave que identifica el archivo de configuracion por defecto.
     */
    public static final String DEFAULT = "hibernate.cfg.xml";
    
    /**
     * Clave que identifica el archivo de configuracion de test.
     */
    public static final String TEST = "hibernate-test.cfg.xml";
    
    /*
     * Sufijo que se agrega a la clave para identificar la <code> Transaction
     * </code> .
     */
    private static final String TRANSACTION_SUFIX = ".transaction";
    
    /**
     * Mapa donde se guardan las <code>SessionFactory</code> para cada uno de
     * los archivos de configuración.
     */
    private static final Map sessionFactories = new HashMap();
    
    /**
     * Variable <code>ThreadLocal</code> donde se guardan las
     * <code>Session</code> y <code>Transaction</code> para cada archivo de
     * configuración.
     */
    private static final ThreadLocal sessions = new ThreadLocal();
    
    /*
     * Bloque estático de inicialización.
     */
    static {
        
        /*
         * Inicialización de la sesión por defecto.
         */
        try {
            SessionFactory sessionFactory = new AnnotationConfiguration().configure(new File(DEFAULT))
            .buildSessionFactory();
            sessionFactories.put(DEFAULT, sessionFactory);
        } catch (Throwable e) {
            try {
				throw new DataSourceException(e);
			} catch (DataSourceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
        /*
         * Inicialización de la sesión de TEST.
         */
        try {
            SessionFactory sessionFactory = new Configuration().configure(TEST)
            .buildSessionFactory();
            sessionFactories.put(TEST, sessionFactory);
        } catch (Throwable t) {
            // Si no existe el archivo de configuración de test, no se
            // conseidera un error. Para cada try/catch sólo deberá lanzarse
            // DataSourceException en caso de que la existencia del archivo sea
            // requerida.
        }
    }
    
    /**
     * Inicializa una <code>SessionFactory</code> a partir de un archivo de
     * configuración.
     *
     * @param key
     *            nombre del archivo de configuración.
     * @throws DataSourceException 
     */
    public static void initialize(String key) throws DataSourceException {
        try {
            SessionFactory sessionFactory = new Configuration().configure(key)
            .buildSessionFactory();
            sessionFactories.put(key, sessionFactory);
        } catch (Throwable t) {
            throw new DataSourceException(t);
        }
    }
    
    /*
     * Obtiene la <code> SessionFactory </code> identificada con la clave.
     *
     * @param key clave que identifica la <code> SessionFactory </code> .
     * @return <code> SessionFactory </code> identificada con la clave.
     */
    private static SessionFactory getSessionFactory(String key) {
        return (SessionFactory) sessionFactories.get(key);
    }
    
    /*
     * Obtiene la <code> SessionFactory </code> por defecto.
     *
     * @return <code> SessionFactory </code> por defecto.
     */
    private static SessionFactory getSessionFactory() {
        return getSessionFactory(DEFAULT);
    }
    
    /**
     * Obtiene una nueva <code>Session</code> a partir de la
     * <code>SessionFactory</code> identificada con la clave.
     *
     * @param key
     *            key clave que identifica a la <code>SessionFactory</code>.
     * @return una nueva <code>Session</code>.
     */
    public static Session openSession(String key) {
        return getSessionFactory(key).openSession();
    }
    
    /**
     * Obtiene una nueva <code>Session</code> a partir de la
     * <code>SessionFactory</code> por defecto.
     *
     * @return una nueva <code>Session</code>.
     */
    public static Session openSession() {
        return getSessionFactory().openSession();
    }
    
    /**
     * Obtiene la <code>Session</code> actual, o una nueva en caso de que no
     * hubiese ninguna, a partir de la <code>SessionFactory</code>
     * identificada con la clave. La <code>Session</code> es asociada al
     * thread que hace la llamada.
     *
     * @param key
     *            clave que identifica a la <code>SessionFactory</code>.
     * @return una nueva <code>Session</code>.
     * @throws <code>DataSourceException</code> en caso de no poder crear una
     *             nueva <code>Session</code>
     */
    public static Session currentSession(String key) throws DataSourceException {
        Map map = getThreadLocalMap();
        Session session = (Session) map.get(key);
        if (session == null) {
            session = openSession(key);
            map.put(key, session);
            sessions.set(map);
        }
        return session;
    }
    
    /**
     * Cierra la <code>Session</code> actual, para el thread que hace la
     * llamada.
     *
     * @param key
     *            clave que identifica la <code>Session</code> que se quiere
     *            cerrar.
     * @throws <code>DataSourceException</code>
     */
    public static void closeSession(String key) throws DataSourceException {
        try {
            Map map = getThreadLocalMap();
            Session session = (Session) map.get(key);
            if (session != null) {
                session.close();
                map.remove(key);
                sessions.set(map);
            }
        } catch (HibernateException e) {
            throw new DataSourceException(e);
        }
    }
    
    /**
     * Obtiene la <code>Session</code> actual, o una nueva en caso de que no
     * hubiese ninguna, a partir de la <code>SessionFactory</code> por
     * defecto.
     *
     * @return <code>Session</code> actual, para el thread que hace la
     *         llamada.
     * @throws <code>DataSourceException</code>
     */
    public static Session currentSession() throws DataSourceException {
        return currentSession(DEFAULT);
    }
    
    /**
     * Cierra la <code>Session</code> actual para el thread que hace la
     * llamada.
     *
     * @throws <code>DataSourceException</code> en caso de no poder cerrar la
     *             <code>Session</code>.
     */
    public static void closeSession() throws DataSourceException {
        closeSession(DEFAULT);
    }
    
    /**
     * Inicia una <code>Transaction</code> en el thread actual, sobre la
     * <code>Session</code> dada.
     *
     * @param key
     *            clave que identifica la <code>Session</code> sobre la que se
     *            quiere iniciar una <code>Transaction</code>.
     * @throws DataSourceException
     */
    public static void beginTransaction(String key) throws DataSourceException {
        Map map = (Map) getThreadLocalMap();
        Transaction t = (Transaction) map.get(key.concat(TRANSACTION_SUFIX));
        try {
            if (t == null) {
                t = currentSession(key).beginTransaction();
                map.put(key.concat(TRANSACTION_SUFIX), t);
                sessions.set(map);
            }
        } catch (HibernateException e) {
            throw new DataSourceException(e);
        }
    }
    
    /**
     * Aplica los cambios de la <code>Transaction</code> asociada al thread
     * actual, sobre la <code>Session</code> dada. Si anteriormente estos
     * cambios hubiesen sido aplicados o revertidos, no tiene efecto.
     *
     * @param key
     *            clave que identifica la <code>Session</code> sobre la que se
     *            quieren aplicar los cambios.
     * @throws DataSourceException
     */
    public static void commitTransaction(String key) throws DataSourceException {
        Map map = (Map) getThreadLocalMap();
        Transaction t = (Transaction) map.get(key.concat(TRANSACTION_SUFIX));
        try {
            if (t != null && !t.wasCommitted() && !t.wasRolledBack()) {
                t.commit();
            }
            map.remove(key.concat(TRANSACTION_SUFIX));
            sessions.set(map);
        } catch (HibernateException e) {
            throw new DataSourceException(e);
        }
    }
    
    /**
     * Revierte los cambios de la <code>Transaction</code> asociada al thread
     * actual, sobre la <code>Session</code> dada. Si anteriormente estos
     * cambios hubiesen sido aplicados o revertidos, no tiene efecto.
     *
     * @param key
     *            clave que identifica la <code>Session</code> sobre la que se
     *            quieren revertir los cambios.
     * @throws DataSourceException
     */
    public static void rollbackTransaction(String key)
    throws DataSourceException {
        Map map = (Map) getThreadLocalMap();
        Transaction t = (Transaction) map.get(key.concat(TRANSACTION_SUFIX));
        try {
            map.remove(key.concat(TRANSACTION_SUFIX));
            sessions.set(map);
            if (t != null && !t.wasCommitted() && !t.wasRolledBack()) {
                t.rollback();
            }
        } catch (HibernateException e) {
            throw new DataSourceException(e);
        }
    }
    
    /**
     * Inicia una <code>Transaction</code> asociada al thread actual, sobre la
     * <code>Session</code> por defecto.
     *
     * @throws DataSourceException
     */
    public static void beginTransaction() throws DataSourceException {
        beginTransaction(DEFAULT);
    }
    
    /**
     * Aplica los cambios de la <code>Transaction</code> asociada al thread
     * actual, sobre la <code>Session</code> por defecto. Si anteriormente
     * estos cambios hubiesen sido aplicados o revertidos, no tiene efecto.
     *
     * @throws DataSourceException
     */
    public static void commitTransaction() throws DataSourceException {
        commitTransaction(DEFAULT);
    }
    
    /**
     * Revierte los cambios de la <code>Transaction</code> asociada al thread
     * actual, sobre la <code>Session</code> por defecto. Si anteriormente
     * estos cambios hubiesen sido aplicados o revertidos, no tiene efecto.
     *
     * @throws DataSourceException
     */
    public static void rollbackTransaction() throws DataSourceException {
        rollbackTransaction(DEFAULT);
    }
    
    /*
     * Método de utilidad que devuelve el map asociado al thread actual o uno
     * nuevo en caso de que no lo hubiera.
     */
    private static Map getThreadLocalMap() {
        Map map = (Map) sessions.get();
        if (map == null) {
            map = new HashMap();
            sessions.set(map);
        }
        return map;
    }
}