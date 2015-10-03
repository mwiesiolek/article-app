package pl.mw.article.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keywordId", unique = true)
    private final Long keywordId;

    @Column(name = "word")
    private final String word;

    @ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY)
    private final Set<Article> articles;

    /**
     * Don't use it, hibernate needs it
     */
    @Deprecated
    public Keyword() {
        keywordId = Long.valueOf(0);
        word = null;

        articles = new HashSet<>();
    }

    public Keyword(final String word) {
        keywordId = Long.valueOf(0);
        this.word = word;

        articles = new HashSet<>();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Keyword keyword = (Keyword) o;

        return keywordId.equals(keyword.keywordId);

    }

    @Override
    public int hashCode() {
        return keywordId.hashCode();
    }

    @Override
    public String toString() {
        return word;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public String getWord() {
        return word;
    }

    public void addArticle(Article article){
        articles.add(article);
    }
}
