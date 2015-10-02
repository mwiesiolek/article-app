package pl.mw.article.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorId", unique = true)
    private final Long authorId;

    @Column(name = "firstName", nullable = false)
    private final String firstName;

    @Column(name = "surname", nullable = false)
    private final String surname;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private final Set<Article> articles;

    /**
     * Don't use it. Hibernate needs it
     */
    @Deprecated
    public Author() {
        authorId = Long.valueOf(0);
        firstName = null;
        surname = null;

        articles = new HashSet<>();
    }

    public Author(final String firstName, final String surname) {
        authorId = Long.valueOf(0);
        this.firstName = firstName;
        this.surname = surname;

        articles = new HashSet<>();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Author author = (Author) o;

        return authorId.equals(author.authorId);

    }

    @Override
    public int hashCode() {
        return authorId.hashCode();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void addArticle(Article article){
        articles.add(article);
    }
}
