import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.awt.Desktop;

public class WikiSearch {
    private String encodedRequest;
    private Integer pageId;

    public void getRequest() {
        Scanner in = new Scanner(System.in);
        String request = in.nextLine();
        encodedRequest = URLEncoder.encode(request, StandardCharsets.UTF_8);
    }

  public void makeServerRequest() throws IOException {
    String URLstring = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch="
            + encodedRequest;

    URL url = new URL(URLstring);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine;
    StringBuilder response = new StringBuilder();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    JSONObject jsonObject = new JSONObject(response.toString());
    JSONArray searchResults = jsonObject.getJSONObject("query").getJSONArray("search");
    if (!searchResults.isEmpty()) {
      pageId = searchResults.getJSONObject(0).getInt("pageid");
    } else {
      System.out.println("Нет результатов для поиска.");
    }
  }

  public void showPage() {
    try {
      if (pageId != null) {
        String WIKI_PAGE = "https://ru.wikipedia.org/?curid=";
        URI uri = new URI(WIKI_PAGE + pageId);

        if (Desktop.isDesktopSupported()) {
          Desktop desktop = Desktop.getDesktop();

          if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(uri);
          } else {
            System.out.println("Операция BROWSE не поддерживается.");
          }

        } else {
          System.out.println("Desktop не поддерживается на этой платформе.");
        }
      } else {
        System.out.println("Page ID не найден.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    WikiSearch wikiSearch = new WikiSearch();
    wikiSearch.getRequest();
    try {
      wikiSearch.makeServerRequest();
      wikiSearch.showPage();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
