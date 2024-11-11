import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WikipediaAPI {

    private static final String API_URL = "https://ru.wikipedia.org/w/api.php?action=query&list=search&srsearch=%s&format=json&utf8=&srlimit=10";

    public List<WikiPage> makeServerRequest(String request) throws IOException {
        String urlString = String.format(API_URL, request);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return parseResults(response.toString());
    }

    private List<WikiPage> parseResults(String jsonResponse) {
        List<WikiPage> results = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray searchResults = jsonObject.getJSONObject("query").getJSONArray("search");

        for (int i = 0; i < searchResults.length(); i++) {
            JSONObject pageJson = searchResults.getJSONObject(i);
            String title = pageJson.getString("title");
            int pageId = pageJson.getInt("pageid");
            results.add(new WikiPage(title, pageId));
        }

        return results;
    }
}
