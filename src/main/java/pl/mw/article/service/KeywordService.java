package pl.mw.article.service;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mw.article.dao.KeywordDAO;
import pl.mw.article.domain.Author;
import pl.mw.article.domain.Keyword;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by mwiesiolek on 03.10.15.
 */
@Service
@Transactional
public class KeywordService {

	@Autowired
	private KeywordDAO keywordDAO;

	/**
	 * Saves or updates a keyword
	 *
	 * @param keyword
	 */
	public void saveOrUpdate(Keyword keyword) {
		keywordDAO.saveOrUpdate(keyword);
	}

	public boolean checkIfExist(final Criterion criterion) {
		return keywordDAO.checkIfExist(criterion);
	}

	public Long size(){
		return keywordDAO.size();
	}

	public Set<Keyword> findAll(final int from, final int number) {
		return keywordDAO.findAll(from, number);
	}
}
