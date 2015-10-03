package pl.mw.article.viewmodel.json;

import pl.mw.article.domain.Article;

import java.util.Set;

/**
 * <p>DTO between presentation and controller layers.</p>
 * <p>Created by mwiesiolek on 03.10.15.</p>
 */
public class ArticleWrapper {

	private Set<Article> articles;
	private long size;
	private double number;

	public ArticleWrapper(Set<Article> articles, long size, double number) {
		this.articles = articles;
		this.size = size;
		this.number = number;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public long getNumberOfPages() {
		return (long)Math.ceil(size / number);
	}
}
