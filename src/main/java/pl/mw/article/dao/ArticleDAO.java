package pl.mw.article.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mw.article.domain.Article;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Repository
public class ArticleDAO extends AbstractDAO<Article>{

    public Set<Article> findAll(final int from, final int number) {

        Criteria criteria = getSession().createCriteria(Article.class);
        criteria.setFirstResult(from);
        criteria.setMaxResults(number);

        return new LinkedHashSet<>(criteria.list());
    }

    public Article find(final Long id) {
        return (Article) getSession().get(Article.class, id);
    }

    public Set<Article> findAllWith(final Criterion criterion) {

        Criteria criteria = getSession().createCriteria(Article.class);
        criteria.add(criterion);
        criteria.addOrder(Order.desc("id"));
        criteria.createAlias("authors", "a", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("keywords", "k", JoinType.LEFT_OUTER_JOIN);

        return new LinkedHashSet<>(criteria.list());
    }

    public Set<Article> findAllWith(final Criterion criterion, final int from, final int number) {

        Criteria criteria = getSession().createCriteria(Article.class);
        criteria.add(criterion);
        criteria.setFirstResult(from);
        criteria.setMaxResults(number);
        criteria.addOrder(Order.desc("id"));
        criteria.createAlias("authors", "authors", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("keywords", "keywords", JoinType.LEFT_OUTER_JOIN);

        return new LinkedHashSet<>(criteria.list());
    }

    public void delete(Article article){
        getSession().delete(article);
    }

    public Long size() {
        return (Long) getSession().createCriteria(Article.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public Long size(final Criterion criterion) {
        Criteria criteria = getSession().createCriteria(Article.class);
        criteria.add(criterion);

        return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public boolean checkIfExist(final Criterion criterion) {

        Criteria criteria = getSession().createCriteria(Article.class);
        criteria.add(criterion);
        Long result = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();

        return result > 0;
    }
}
