package pl.mw.article.service;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mw.article.dao.ArticleDAO;
import pl.mw.article.dao.AuthorDAO;
import pl.mw.article.dao.KeywordDAO;
import pl.mw.article.domain.Article;
import pl.mw.article.domain.Author;
import pl.mw.article.domain.Keyword;
import pl.mw.article.viewmodel.ArticleView;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private KeywordDAO keywordDAO;

    /**
     * Persists new article with given collections in many to many relation
     * @param article
     * @param authors
     * @param keywords
     */
    public void addWithJoins(Article article, Set<Author> authors, Set<Keyword> keywords){
        checkNotNull(authors);
        checkNotNull(keywords);

        for(Author author : authors){
            article.addAuthor(author);
        }

        for(Keyword keyword : keywords){
            article.addKeyword(keyword);
        }

        articleDAO.saveOrUpdate(article);
    }

    /**
     * Find all articles in given range with given criteria
     * @param criterion
     * @param from
     * @param number
     * @return
     */
    public Set<Article> findAllWith(final Criterion criterion, final int from, final int number) {
        return articleDAO.findAllWith(criterion, from, number);
    }

    /**
     * Finds all articles in given range
     * @param from
     * @param number
     * @return
     */
    public Set<Article> findAll(final int from, final int number) {
        return articleDAO.findAll(from, number);
    }

    /**
     * Find article with given id
     * @param id
     * @return
     */
    public Article find(final Long id){
        return articleDAO.find(id);
    }

    /**
     * Saves or updates an article
     * @param article
     */
    public void  saveOrUpdate(Article article){
        articleDAO.saveOrUpdate(article);
    }

    /**
     * Removes article with given id
     * @param id
     */
    public void remove(Long id){

        Article article = articleDAO.find(id);
        articleDAO.delete(article);
    }

    public Long size(){
        return articleDAO.size();
    }

    public void saveOrUpdate(ArticleView articleView) {
        Article article = articleView.prepareArticle();

        Set<Author> authors = articleView.selectedAuthors();
        Set<Keyword> keywords = articleView.selectedKeywords();

        Set<Author> fromDBAuthors = new HashSet<>();
        for(Author author : authors){
            Author fromDB = authorDAO.find(author.getAuthorId());
            fromDBAuthors.add(fromDB);
        }

        Set<Keyword> fromDBKeywords = new HashSet<>();
        for(Keyword keyword : keywords){
            Keyword fromDB = keywordDAO.find(keyword.getKeywordId());
            fromDBKeywords.add(fromDB);
        }

        article.addAuthors(fromDBAuthors);
        article.addKeywords(fromDBKeywords);

        Article fromDB = find(article.getArticleId());
        fromDB.copy(article);

        saveOrUpdate(fromDB);
    }
}
