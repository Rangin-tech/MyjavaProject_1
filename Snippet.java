import java.io.Serializable;

public class Snippet implements Serializable {
    private String title, language, tags, code;

    public Snippet(String title, String language, String tags, String code) {
        this.title = title;
        this.language = language;
        this.tags = tags;
        this.code = code;
    }

    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public String getTags() { return tags; }
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    @Override
    public String toString() {
        return "Title: " + title + "\nLanguage: " + language + "\nTags: " + tags + "\nCode:\n" + code;
    }
}
