package pl.mw.article.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import pl.mw.article.domain.Author;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Repository
public class AuthorDAO extends AbstractDAO<Author> {
    public Set<Author> findAll(final int from, final int number) {

        Criteria criteria = getSession().createCriteria(Author.class);
        criteria.setFirstResult(from);
        criteria.setMaxResults(number);

        return new LinkedHashSet<>(criteria.list());
    }

    public Author find(final Long id) {
        return (Author) getSession().get(Author.class, id);
    }

    public Set<Author> findAllWith(final Criterion criterion) {

        Criteria criteria = getSession().createCriteria(Author.class);
        criteria.add(criterion);

        return new LinkedHashSet<>(criteria.list());
    }

    public Set<Author> findAllWith(final Criterion criterion, final int from, final int number) {

        Criteria criteria = getSession().createCriteria(Author.class);
        criteria.add(criterion);
        criteria.setFirstResult(from);
        criteria.setMaxResults(number);

        return new LinkedHashSet<>(criteria.list());
    }

    public void delete(Author Author) {
        getSession().delete(Author);
    }

    public Long size() {
        return (Long) getSession().createCriteria(Author.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public Long size(final Criterion criterion) {
        Criteria criteria = getSession().createCriteria(Author.class);
        criteria.add(criterion);

        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public boolean checkIfExist(final Criterion criterion) {

        Criteria criteria = getSession().createCriteria(Author.class);
        criteria.add(criterion);
        Long result = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

        return result > 0;
    }
}
