import java.awt.Desktop;
import java.net.URI;

public class UrlParser {
    public void showPage(Integer pageId) {
        try {
            if (pageId != null) {
                URI uri = new URI("https://ru.wikipedia.org/?curid=" + pageId);

                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();

                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(uri);
                    } else {
                        System.out.println("Операция BROWSE не поддерживается.");
                    }

                } else {
                    System.out.println("Desktop не поддерживается.");
                }
            } else {
                System.out.println("Page ID не найден.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
