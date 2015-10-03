package pl.mw.article.viewmodel;

import pl.mw.article.domain.Author;
import pl.mw.article.domain.builder.AuthorBuilder;

/**
 * <p>DTO class between presentation layer and controller.</p>
 * <p>Created by mwiesiolek on 03.10.15.</p>
 */
public class AuthorView {
	private Long authorId;
	private Boolean selected;
	private String signature;

	@Deprecated
	public AuthorView() {
		//spring needs it, don't use it
	}

	public AuthorView(Long authorId, Boolean selected, String signature) {
		this.authorId = authorId;
		this.selected = selected;
		this.signature = signature;
	}

	public Author prepareAuthor(){

		String[] split = signature.split(" ");

		return AuthorBuilder.anAuthor()
				.withFirstName(split[0])
				.withSurname(split[1])
				.withAuthorId(authorId)
				.build();
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
