package pl.mw.article.domain.builder;

import pl.mw.article.domain.Author;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
public class AuthorBuilder {
    private String firstName;
    private String surname;

    private AuthorBuilder() {
    }

    public static AuthorBuilder anAuthor() {
        return new AuthorBuilder();
    }

    public AuthorBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AuthorBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public AuthorBuilder but() {
        return anAuthor().withFirstName(firstName).withSurname(surname);
    }

    public Author build() {
        Author author = new Author(firstName, surname);
        return author;
    }
}
