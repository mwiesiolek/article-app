package pl.mw.article.enums;

/**
 * Created by mwiesiolek on 16/05/2015.
 */
public enum Alert {
	ERROR("errorMsg"), NOTIFICATION("notification"), SUCCESS("success");

	private String key;

	private Alert(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
