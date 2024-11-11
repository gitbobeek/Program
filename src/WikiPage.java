public class WikiPage {
    private String title;
    private int pageId;

    public WikiPage(String title, int pageId) {
        this.title = title;
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public int getPageId() {
        return pageId;
    }

    @Override
    public String toString() {
        return title + " (ID: " + pageId + ")";
    }
}
