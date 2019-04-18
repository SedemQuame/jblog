package sample.model;

public class Story {
    private String title, category, introduction, body, conclusion;
    private int arthurId, storyId;


    /*Overloading Constructor*/
    public Story() {

    }

    public Story(String title, String category, String introduction, String body, String conclusion, int arthurId, int storyId) {
        this.title = title;
        this.category = category;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
        this.arthurId = arthurId;
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public int getArthurId() {
        return arthurId;
    }

    public void setArthurId(int arthurId) {
        this.arthurId = arthurId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }
}
