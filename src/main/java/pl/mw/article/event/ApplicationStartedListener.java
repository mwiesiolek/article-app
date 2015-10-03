package pl.mw.article.event;

import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import pl.mw.article.domain.Author;
import pl.mw.article.domain.Keyword;
import pl.mw.article.domain.builder.AuthorBuilder;
import pl.mw.article.domain.builder.KeywordBuilder;
import pl.mw.article.service.AuthorService;
import pl.mw.article.service.KeywordService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mwiesiolek on 03.10.15.
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ContextStartedEvent> {

	private static final List<String> authors = new LinkedList<String>(){{
		add("John Smith");
		add("Jan Kowalski");
	}};

	private static final List<String> keywords = new LinkedList<String>(){{
		add("word_1");
		add("word_2");
	}};

	@Autowired
	private AuthorService authorService;

	@Autowired
	private KeywordService keywordService;

	@Override
	public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
		addAuthors();
		addKeywords();
	}

	private void addKeywords() {
		for(String keyword : keywords){
			SimpleExpression criteria = Restrictions.eq("word", keyword);

			boolean result = keywordService.checkIfExist(criteria);
			if(!result){
				Keyword newKeyword = KeywordBuilder.aKeyword()
						.withWord(keyword)
						.build();

				keywordService.saveOrUpdate(newKeyword);
			}
		}
	}

	private void addAuthors() {
		for(String author : authors){

			String[] split = author.split(" ");

			SimpleExpression firstNameCriteria = Restrictions.eq("firstName", split[0]);
			SimpleExpression surnameCriteria = Restrictions.eq("surname", split[1]);

			LogicalExpression and = Restrictions.and(firstNameCriteria, surnameCriteria);

			boolean result = authorService.checkIfExist(and);
			if(!result){
				Author newAuthor = AuthorBuilder.anAuthor()
						.withFirstName(split[0])
						.withSurname(split[1])
						.build();

				authorService.saveOrUpdate(newAuthor);
			}
		}
	}
}
