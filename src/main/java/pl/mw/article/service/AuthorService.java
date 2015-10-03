package pl.mw.article.service;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mw.article.dao.AuthorDAO;
import pl.mw.article.domain.Author;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by mwiesiolek on 03.10.15.
 */
@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorDAO authorDAO;

	/**
	 * Saves or updates an author
	 *
	 * @param author
	 */
	public void saveOrUpdate(Author author) {
		authorDAO.saveOrUpdate(author);
	}

	public boolean checkIfExist(final Criterion criterion) {
		return authorDAO.checkIfExist(criterion);
	}

	public Long size(){
		return authorDAO.size();
	}

	public Set<Author> findAll(final int from, final int number) {
		return authorDAO.findAll(from, number);
	}
}
