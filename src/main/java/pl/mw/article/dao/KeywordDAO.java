package pl.mw.article.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import pl.mw.article.domain.Keyword;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Repository
public class KeywordDAO extends AbstractDAO<Keyword> {

    public Set<Keyword> findAll(final int from, final int number) {

        Criteria criteria = getSession().createCriteria(Keyword.class);
        criteria.setFirstResult(from);
        criteria.setMaxResults(number);

        return new LinkedHashSet<>(criteria.list());
    }

    public Keyword find(final Long id) {
        return (Keyword) getSession().get(Keyword.class, id);
    }

    public Set<Keyword> findAllWith(final Criterion criterion) {

        Criteria criteria = getSession().createCriteria(Keyword.class);
        criteria.add(criterion);

        return new LinkedHashSet<>(criteria.list());
    }

    public Set<Keyword> findAllWith(final Criterion criterion, final int from, final int number) {

        Criteria criteria = getSession().createCriteria(Keyword.class);
        criteria.add(criterion);
        criteria.setFirstResult(from);
        criteria.setMaxResults(number);

        return new LinkedHashSet<>(criteria.list());
    }

    public void delete(Keyword Keyword) {
        getSession().delete(Keyword);
    }

    public Long size() {
        return (Long) getSession().createCriteria(Keyword.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public Long size(final Criterion criterion) {
        Criteria criteria = getSession().createCriteria(Keyword.class);
        criteria.add(criterion);

        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public boolean checkIfExist(final Criterion criterion) {

        Criteria criteria = getSession().createCriteria(Keyword.class);
        criteria.add(criterion);
        Long result = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

        return result > 0;
    }
}
