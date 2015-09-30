package pl.mw.article.domain;

import javax.persistence.*;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "firstName", nullable = false)
    private final String firstName;

    @Column(name = "surname", nullable = false)
    private final String surname;

    /**
     * Don't use it. Hibernate needs it
     */
    @Deprecated
    public Author() {
        id = Long.valueOf(0);
        firstName = null;
        surname = null;
    }

    public Author(final String firstName, final String surname) {
        id = Long.valueOf(0);
        this.firstName = firstName;
        this.surname = surname;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Author author = (Author) o;

        if (!id.equals(author.id)) return false;
        if (!firstName.equals(author.firstName)) return false;
        return surname.equals(author.surname);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }
}
