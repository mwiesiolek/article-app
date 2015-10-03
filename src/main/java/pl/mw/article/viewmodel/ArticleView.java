package pl.mw.article.viewmodel;

import pl.mw.article.domain.Article;
import pl.mw.article.domain.builder.ArticleBuilder;

/**
 * Created by mwiesiolek on 03.10.15.
 */
public class ArticleView {

	private String header;
	private String description;
	private String text;
	private Long publishDate;
	private Long articleId;

	@Deprecated
	public ArticleView() {
		//needed by spring, don't use it
	}

	public ArticleView(Article article) {
		this.header = article.getHeader();
		this.description = article.getDescription();
		this.text = article.getText();
		this.publishDate = article.getPublishDate();
		this.articleId = article.getArticleId();
	}

	public Article prepareArticle(){
		return ArticleBuilder.anArticle()
				.withPublishDate(publishDate)
				.withDescription(description)
				.withHeader(header)
				.withText(text)
				.build();
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
}
