import java.io.IOException;

class Program {
    public static void main(String[] args) {
        WikiSearch wikiSearch = new WikiSearch();
        wikiSearch.getRequest();

        try {
            wikiSearch.makeServerRequest();
            wikiSearch.showPage();
        } catch (IOException e) {
            System.err.println("Ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}