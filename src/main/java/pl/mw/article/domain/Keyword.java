package pl.mw.article.domain;

import javax.persistence.*;

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

        if (!id.equals(keyword.id)) return false;
        return word.equals(keyword.word);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + word.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }
}
