package utng.dates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import utng.configurations.HibernateUtil;

public abstract class DAO<T>{
    protected Session session;
    protected T model;

    public DAO(T model) {
        session = HibernateUtil.getSession();
        this.model=model;
    }
    public Long insert (T model) throws HibernateException{
        long id=0;
        Transaction tx = session.beginTransaction();
        try {
            Serializable result = session.save(model);
            id=(Long)result;
            tx.commit();
            session.clear();                    
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return id;
    }
    public void update(T model)
        throws HibernateException{
        Transaction tx = session.beginTransaction();
        try {
            session.merge(model);
            tx.commit();
            session.clear();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
    
    }
    
    public void delete (T model)
            throws HibernateException{
        Transaction tx = session.beginTransaction();
        try {
            session.delete(model);
            tx.commit();
            session.clear();
        } catch (HibernateException e) {
            tx.rollback();
            session.clear();
            throw e;
        }
    }
    
    public List<T> getAll()throws HibernateException{
        String entityName=model.getClass().getName();
        List<T> list = new ArrayList<T>();
        try {
            list=session.createQuery(
                " from "+entityName).list();
            session.clear();
        } catch (Exception e) {
            session.clear();
            throw new HibernateException("Error to consult: "+e);
        }
        return list;
    }
    
    protected T getOneById(Serializable id)throws HibernateException{
      T object = null;
      object=(T)session.get(model.getClass(),Long.valueOf(id.toString()));
      session.clear();
      return object;
    }
    
    protected T query (String sql,
            List<String> paramNames,
            List<Object>paramValues){
        Query query = session.createQuery(sql);
        for(int i=0; i<paramNames.size();i++){
            query.setParameter(paramNames.get(i), paramValues.get(i));
        }
        List<T> list = query.list();
        if(list.size()>0){
            return list.get(0);
        }else {
         return null;
        }
    }
    
    public Connection getConnection(){
        session = HibernateUtil.getSession();
        Connection c = null;
        try {
            c = session.getSessionFactory().getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }
    
    
    protected List<T> queryList (String sql,
            Class<?>entity,
            List<String> paramNames,
            List<Object>paramValues){
        SQLQuery query = session.createSQLQuery(sql);
        for(int i=0; i<paramNames.size();i++){
            query.setParameter(paramNames.get(i), paramValues.get(i));
        }
        List<T> list = query.list();
        if(list.size()>0){
            return list;
        }else {
         return null;
        }
    }
    
}
