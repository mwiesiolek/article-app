package pl.mw.article.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
public abstract class AbstractDAO<Type> {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveOrUpdate(Type type) {
        getSession().saveOrUpdate(type);
    }

    public void flush(){
        getSession().flush();
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
