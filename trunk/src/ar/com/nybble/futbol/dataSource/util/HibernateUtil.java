package ar.com.nybble.futbol.dataSource.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;





/*
 * Clase de utilidad para obtener la sesion de hibernate.
 * 
 * @author Chuidiang
 * 
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    /**
     * Variable <code>ThreadLocal</code> donde se guardan las
     * <code>Session</code> y <code>Transaction</code> para cada archivo de
     * configuración.
     */
    private static final ThreadLocal sessions = new ThreadLocal();
    /**
     * Mapa donde se guardan las <code>SessionFactory</code> para cada uno de
     * los archivos de configuración.
     */
    private static final Map sessionFactories = new HashMap();
    
    /**
     * Clave que identifica el archivo de configuracion por defecto.
     */
    public static final String DEFAULT = "hibernate.cfg.xml";
    
    /*
     * Sufijo que se agrega a la clave para identificar la <code> Transaction
     * </code> .
     */
    private static final String TRANSACTION_SUFIX = ".transaction";
    

    static {
        try {
            // Si no ponemos fichero, intenta cargar "/hibernate.cfg.xml" en el
            // raiz
            sessionFactory = new AnnotationConfiguration().configure(
                    new File("hibernate.cfg.xml")).buildSessionFactory();
            sessionFactories.put(DEFAULT, sessionFactory);
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
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
    
    
    /*
     * Obtiene la <code> SessionFactory </code> identificada con la clave.
     *
     * @param key clave que identifica la <code> SessionFactory </code> .
     * @return <code> SessionFactory </code> identificada con la clave.
     */
    private static SessionFactory getSessionFactory(String key) {
        return (SessionFactory) sessionFactories.get(key);
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
    

    
    
}
