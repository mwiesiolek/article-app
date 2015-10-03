package pl.mw.article.viewmodel;

import pl.mw.article.domain.Keyword;
import pl.mw.article.domain.builder.KeywordBuilder;

/**
 * <p>DTO class between presentation layer and controller.</p>
 * <p>Created by mwiesiolek on 03.10.15.</p>
 */
public class KeywordView {
	private Long keywordId;
	private Boolean selected;
	private String word;

	@Deprecated
	public KeywordView() {
		//spring needs it, don't use it
	}

	public KeywordView(Long keywordId, Boolean selected, String word) {
		this.keywordId = keywordId;
		this.selected = selected;
		this.word = word;
	}

	public Keyword prepareKeyword(){
		return KeywordBuilder.aKeyword()
				.withWord(word)
				.withKeywordId(keywordId)
				.build();
	}

	public Long getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
