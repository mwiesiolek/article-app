package pl.mw.article.domain.builder;

import pl.mw.article.domain.Keyword;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
public class KeywordBuilder {
    private String word;

    private KeywordBuilder() {
    }

    public static KeywordBuilder aKeyword() {
        return new KeywordBuilder();
    }

    public KeywordBuilder withWord(String word) {
        this.word = word;
        return this;
    }

    public KeywordBuilder but() {
        return aKeyword().withWord(word);
    }

    public Keyword build() {
        Keyword keyword = new Keyword(word);
        return keyword;
    }
}
