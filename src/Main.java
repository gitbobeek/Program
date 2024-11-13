import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleInputOutput consoleIO = new ConsoleInputOutput();
        WikipediaAPI wikiApi = new WikipediaAPI();
        UrlParser urlParser = new UrlParser();
        String encodedRequest = consoleIO.getRequest();

        try {
            List<WikiPage> results = wikiApi.makeServerRequest(encodedRequest);
            if (!results.isEmpty()) {
                consoleIO.displayResults(results);

                int selectedIndex = consoleIO.getUserSelection(results.size());
                if (selectedIndex >= 0 && selectedIndex < results.size()) {
                    int selectedPageId = results.get(selectedIndex).getPageId();
                    urlParser.showPage(selectedPageId);
                } else {
                    consoleIO.displayMessage("Неверный выбор статьи.");
                }
            } else {
                consoleIO.displayMessage("Нет результатов для поиска.");
            }
        } catch (IOException e) {
            consoleIO.displayMessage("Произошла ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}
