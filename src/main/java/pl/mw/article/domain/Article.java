package pl.mw.article.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articleId", unique = true)
    private Long articleId;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "description", nullable = false)
    private String description;

    @Type(type = "text")
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "publishDate", nullable = false)
    private Long publishDate;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "article_author", joinColumns = {@JoinColumn(name = "articleId")}, inverseJoinColumns = {@JoinColumn(name="authorId")})
    private Set<Author> authors;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "article_keyword", joinColumns = {@JoinColumn(name = "articleId")}, inverseJoinColumns = {@JoinColumn(name="keywordId")})
    private Set<Keyword> keywords;

    /**
     * Use constructor with parameters instead. Hibernate needs default constructor
     */
    @Deprecated
    public Article() {

        articleId = Long.valueOf(0);
        header = null;
        description = null;
        text = null;
        publishDate = Long.valueOf(0);

        authors = new HashSet<>();
        keywords = new HashSet<>();
    }

    public Article(Long id, final String header, final String description, final String text, final Long publishDate) {
        this.articleId = id;
        this.header = header;
        this.description = description;
        this.text = text;
        this.publishDate = publishDate;

        authors = new HashSet<>();
        keywords = new HashSet<>();
    }

    public void copy(Article article){
        this.articleId = article.articleId;
        this.header = article.header;
        this.description = article.description;
        this.text = article.text;
        this.publishDate = article.publishDate;

        this.authors = article.authors;
        this.keywords = article.keywords;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Article article = (Article) o;

        return articleId.equals(article.articleId);

    }

    @Override
    public int hashCode() {
        return articleId.hashCode();
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    @JsonIgnore
    public Long getPublishDate() {
        return publishDate;
    }

    public String getDate(){
        return FastDateFormat.getInstance("yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT")).format(publishDate);
    }

    public void addAuthor(Author author){
        authors.add(author);
    }

    public void addKeyword(Keyword keyword){
        keywords.add(keyword);
    }

    public void addAuthors(Set<Author> authors){
        for(Author author : authors){
            addAuthor(author);
        }
    }

    public void addKeywords(Set<Keyword> keywords){
        for (Keyword keyword : keywords){
            addKeyword(keyword);
        }
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPublishDate(Long publishDate) {
        this.publishDate = publishDate;
    }
}
