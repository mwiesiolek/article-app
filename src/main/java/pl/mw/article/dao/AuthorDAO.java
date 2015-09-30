package pl.mw.article.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Repository
public class AuthorDAO {

    @Autowired
    private SessionFactory sessionFactory;
}
