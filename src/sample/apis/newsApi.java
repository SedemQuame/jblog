package sample.apis;

import java.util.List;

public class newsApi {
    private String status;
    private int totalResults;
    private List<stories> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<stories> getArticles() {
        return articles;
    }
}
