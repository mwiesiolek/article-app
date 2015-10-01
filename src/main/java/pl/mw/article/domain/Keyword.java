package pl.mw.article.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "word")
    private final String word;

    @ManyToMany(mappedBy = "keywords", fetch = FetchType.EAGER)
    private Set<Article> articles;

    /**
     * Don't use it, hibernate needs it
     */
    @Deprecated
    public Keyword() {
        id = Long.valueOf(0);
        word = null;
    }

    public Keyword(final String word) {
        id = Long.valueOf(0);
        this.word = word;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Keyword keyword = (Keyword) o;

        return id.equals(keyword.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }
}
