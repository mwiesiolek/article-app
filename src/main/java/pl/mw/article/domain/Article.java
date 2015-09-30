package pl.mw.article.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "header", nullable = false)
    private final String header;

    @Column(name = "description", nullable = false)
    private final String description;

    @Type(type = "text")
    @Column(name = "text", nullable = false)
    private final String text;

    @Column(name = "publishDate", nullable = false)
    private final Long publishDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private final Set<Author> authors;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private final Set<Keyword> keywords;

    /**
     * Use constructor with parameters instead. Hibernate needs default constructor
     */
    @Deprecated
    public Article() {

        id = Long.valueOf(0);
        header = null;
        description = null;
        text = null;
        publishDate = Long.valueOf(0);

        authors = new HashSet<>();
        keywords = new HashSet<>();
    }

    public Article(final String header, final String description, final String text, final Long publishDate) {
        this.id = Long.valueOf(0);
        this.header = header;
        this.description = description;
        this.text = text;
        this.publishDate = publishDate;

        authors = new HashSet<>();
        keywords = new HashSet<>();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Article article = (Article) o;

        if (!id.equals(article.id)) return false;
        if (!header.equals(article.header)) return false;
        if (!description.equals(article.description)) return false;
        if (!text.equals(article.text)) return false;
        if (!publishDate.equals(article.publishDate)) return false;
        if (!authors.equals(article.authors)) return false;
        return keywords.equals(article.keywords);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + header.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + authors.hashCode();
        result = 31 * result + keywords.hashCode();
        return result;
    }

    public Long getId() {
        return id;
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

    public Long getPublishDate() {
        return publishDate;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }
}
