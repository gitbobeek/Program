import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputOutput {
    private final Scanner scanner = new Scanner(System.in);

    public String getRequest() {
        System.out.print("Введите запрос для поиска: ");
        String request = scanner.nextLine();
        return getEncodedRequest(request);
    }

    public String getEncodedRequest(String req) {
        return URLEncoder.encode(req, StandardCharsets.UTF_8);
    }

    public void displayResults(List<WikiPage> results) {
        System.out.println("Найденные статьи:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getTitle());
        }
    }

    public int getUserSelection(int maxIndex) {
        System.out.print("Выберите номер статьи для открытия (1-" + maxIndex + "): ");
        int selection = scanner.nextInt();
        return selection - 1;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
