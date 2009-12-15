package ar.com.nybble.futbol.dataSource.repositorio;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.dataSource.util.HibernateUtil;




/**
 * @author msaladino
 *
 */
public abstract class RepositoryImpl implements Repository {
    
    private Class persistentClass;
    
    protected RepositoryImpl(Class persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }
    
    protected Class getPersistentClass() {
        return this.persistentClass;
    }
    
    protected Session getSession() throws DataSourceException {
        return HibernateUtil.currentSession();
    }
    
    protected Criteria getCriteria() throws DataSourceException {
        return this.getSession().createCriteria(this.getPersistentClass());
    }
    
    public void create(Object entity) throws HibernateException, DataSourceException {
    	this.getSession().save(entity);      
    }
    
    public void update(Object entity) throws HibernateException, DataSourceException   {
    	this.getSession().saveOrUpdate(entity);	
    }

    
    protected Collection findBy(Criteria criteria) throws DataSourceException { 
        return criteria.list();
    }
    
    protected Collection findBy(Query query) throws DataSourceException {    
        return query.list();
    }
    
    protected Object findUniqueBy(Criteria criteria) throws DataSourceException {
        return criteria.uniqueResult();
    }
    
    protected Object findUniqueBy(Query query) throws DataSourceException {
        return query.uniqueResult();
    }
    
    public Collection findAll() throws DataSourceException {
        return this.findBy(this.getCriteria());
    }
    
    public Object findById(Serializable id) throws DataSourceException {
        Criteria criteria = this.getCriteria().add(Restrictions.eq("id", id));
        return this.findUniqueBy(criteria);
    }
    
    public Object findById(Long id) throws DataSourceException {
        Criteria criteria = this.getCriteria().add(Restrictions.eq("id", id));
        return this.findUniqueBy(criteria);
    }
}