package DevJavaTutorial.src;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

//After installing JSoup, these will work
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupArticleParser implements Parser {
    String url;
    HttpResponse<String> response;

    public JsoupArticleParser(String url) throws IOException, InterruptedException {
        // This is a test of Jsoup:

        // Homework: get this code working. 
        // You will need to have installed Jsoup using either Maven, Eclipse of by correctly linking a .jar file
        // Follow the guide either here: https://jsoup.org/download or here: https://www.tutorialspoint.com/jsoup/jsoup_environment.htm
    	String html = "<html><head><title>Sample Title</title></head>"
         + "<body><p>Sample Content</p></body></html>";
        Document document = Jsoup.parse(html);
        System.out.println(document.title());
        Elements paragraphs = document.getElementsByTag("p");
        for (Element paragraph : paragraphs) {
                System.out.println(paragraph.text());
        }
        
        /*
        this.url = url;

    	// Get the url and resolve the HTML
    	HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        */
        
    }

    private String extractImage(String link) throws IOException, InterruptedException {
        return null;
    }

    private String extractContent(String link) throws IOException, InterruptedException {
        return null;
    }

    private ArrayList<String> extractTimePosteds() {
        return new ArrayList<String>();
    }

    private ArrayList<String> extractLinks() {
        return new ArrayList<String>();
    }

    private ArrayList<String> extractHeadings() {
        return new ArrayList<String>();
    }

    public ArrayList<Article> parse() throws IOException, InterruptedException {
        ArrayList<String> headings = extractHeadings();

        // Can you now implement the extractLinks function?
        ArrayList<String> links = extractLinks();
        // ArrayList<String> timePosteds = extractTimePosteds();

        // For Homework, complete these 3 remaining fields of the array list!
        // For content, it will be different because it only gets a single one.
        ArrayList<String> timePosted = extractTimePosteds();

        /*
         * Now we will create a for loop to create many articles, instead of printing
         * just the headings
         */
        ArrayList<Article> ar = new ArrayList<Article>();
        for (int i = 0; i < (links.size() - 1); i++) {
            String content = extractContent(links.get(i));
            String image = extractImage(links.get(i));
            Article a = new Article(headings.get(i), links.get(i), timePosted.get(i), content, image);

            ar.add(a);
        }
        return ar;
    }

}