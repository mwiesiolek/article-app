package pl.mw.article.viewmodel;

import pl.mw.article.domain.Article;
import pl.mw.article.domain.Author;
import pl.mw.article.domain.Keyword;
import pl.mw.article.domain.builder.ArticleBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>DTO class between presentation layer and controller.</p>
 * <p>Created by mwiesiolek on 03.10.15.</p>
 */
public class ArticleView {

	private String header;
	private String description;
	private String text;
	private Long publishDate;
	private Long articleId;

	private Map<String, AuthorView> authors;
	private Map<String, KeywordView> keywords;

	@Deprecated
	public ArticleView() {
		//needed by spring, don't use it

		authors = new HashMap<>();
		keywords = new HashMap<>();
	}

	public ArticleView(Article article, Set<Author> authors, Set<Keyword> keywords) {
		this.header = article.getHeader();
		this.description = article.getDescription();
		this.text = article.getText();
		this.publishDate = article.getPublishDate();
		this.articleId = article.getArticleId();

		this.authors = new HashMap<>();
		this.keywords = new HashMap<>();

		initAuthors(article, authors);
		initKeywords(article, keywords);
	}

	private void initKeywords(Article article, Set<Keyword> keywords) {
		for(Keyword keyword : keywords){
			KeywordView keywordView = new KeywordView(keyword.getKeywordId(), article.getKeywords().contains(keyword), keyword.getWord());
			this.keywords.put(String.valueOf(keyword.getKeywordId()), keywordView);
		}
	}

	private void initAuthors(Article article, Set<Author> authors) {
		for(Author author : authors){
			AuthorView authorView = new AuthorView(author.getAuthorId(), article.getAuthors().contains(author), author.getSignature());
			this.authors.put(String.valueOf(author.getAuthorId()), authorView);
		}
	}

	public Article prepareArticle() {
		return ArticleBuilder.anArticle()
				.withPublishDate(publishDate)
				.withDescription(description)
				.withHeader(header)
				.withText(text)
				.withArticleId(articleId)
				.build();
	}

	public Set<Author> selectedAuthors(){

		Set<Author> authors = new HashSet<>();
		for(Map.Entry<String, AuthorView> entry : this.authors.entrySet()){
			if(entry.getValue().getSelected()){
				authors.add(entry.getValue().prepareAuthor());
			}
		}

		return authors;
	}

	public Set<Keyword> selectedKeywords(){

		Set<Keyword> keywords = new HashSet<>();
		for(Map.Entry<String, KeywordView> entry : this.keywords.entrySet()){
			if(entry.getValue().getSelected()){
				keywords.add(entry.getValue().prepareKeyword());
			}
		}

		return keywords;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Long publishDate) {
		this.publishDate = publishDate;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Map<String, AuthorView> getAuthors() {
		return authors;
	}

	public void setAuthors(Map<String, AuthorView> authors) {
		this.authors = authors;
	}

	public Map<String, KeywordView> getKeywords() {
		return keywords;
	}

	public void setKeywords(Map<String, KeywordView> keywords) {
		this.keywords = keywords;
	}
}
