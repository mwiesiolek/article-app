package pl.mw.article.domain.builder;

import pl.mw.article.domain.Article;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
public class ArticleBuilder {
    private String header;
    private String description;
    private String text;
    private Long publishDate;

    private ArticleBuilder() {
    }

    public static ArticleBuilder anArticle() {
        return new ArticleBuilder();
    }

    public ArticleBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public ArticleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ArticleBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ArticleBuilder withPublishDate(Long publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public ArticleBuilder but() {
        return anArticle().withHeader(header).withDescription(description).withText(text).withPublishDate(publishDate);
    }

    public Article build() {
        Article article = new Article(header, description, text, publishDate);
        return article;
    }
}
