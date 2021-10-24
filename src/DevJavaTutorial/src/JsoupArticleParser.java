package DevJavaTutorial.src;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//After installing JSoup, these will work
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupArticleParser implements Parser {
    String url;
    HttpResponse<String> response;
    Document doc;

    public JsoupArticleParser(String url) throws IOException, MalformedURLException {
        // This is a test of Jsoup:

        // Homework: get this code working. 
        // You will need to have installed Jsoup using either Maven, Eclipse of by correctly linking a .jar file
        // Follow the guide either here: https://jsoup.org/download or here: https://www.tutorialspoint.com/jsoup/jsoup_environment.htm
        

        this.doc = Jsoup.connect(url).get();
        System.out.println(this.doc.title()); // Print out "Hacker News"
        // Document doc = Jsoup.connect("https://news.ycombinator.com/").get();
        // Elements headings = document.getElementsByTag("a");
        // for (Element heading : headings) {
        //         System.out.println(heading.attr("href"));
        // }
        
    }

    private String extractImage(String link) throws IOException, MalformedURLException {
        String result = "";
        Document doc5 = Jsoup.connect(link).get();
        Elements images = doc5.getElementsByTag("img");
        if (images.size() > 0) {
            Element image = images.get(0);
            result = link + "/" + image.attr("src");
        }
        // String pattern5 = "\\\"([^\\\"]*(?:gif|png|jpg|webp))\\\"";
        // for (Element image : images){
        //     Pattern p5 = Pattern.compile(pattern5);
        //     Matcher m5 = p5.matcher(image.text());
        //     boolean b5 = m5.find();
        //     if (b5==false) {
        //         result = null;
        //     } else
        //     result = m5.group(1);
        // }

        return result;
    }

    private String extractContent(String link) throws IOException, MalformedURLException {
        if (link == null) {
            throw new IOException("Link must not be null");
        }
        String result1 = "";
        try 
        {
            if (link.contains(".pdf"))
                throw new MalformedURLException();
            else
            {Document doc4 = Jsoup.connect(link).get();
            Elements content = doc4.select("meta[name=description]");
            if (content.size() > 0) {
                result1 = content.get(0).attr("content");
            }

            /*
<meta name="description" content="The smallest and fastest command-line coloring package on the internet. Trust me. - sindresorhus/yoctocolors: The smallest and fastest command-line coloring package on the internet. Trust me.">
            */
            // String pattern4 = "<meta [^=]*=\"(?:og:){0,1}description\" content=\"([^\"]*)\" ?\\/>";
            // for (Element c : content){
            //     Pattern p4 = Pattern.compile(pattern4);
            //     Matcher m4 = p4.matcher(c.text());
            //     boolean b4 = m4.find();
            //     if (b4==false) {
            //         result1 = null;
            //     } else
            //     result1 = m4.group(1); 
            // }}
        }}
        catch (MalformedURLException e) {
            System.out.println("Unable to resolve link: " + link);
            result1 = null;
        }
        return result1;
    }

    private ArrayList<String> extractTimePosteds() throws IOException {
        ArrayList<String> timePosteds = new ArrayList<String>();
        // Document doc3 = Jsoup.connect("https://news.ycombinator.com/").get();
        Elements timePosted = this.doc.getElementsByClass("age");
        for (Element time:timePosted)
            {
                timePosteds.add(time.attr("title"));
            }
        return timePosteds;
    }

    private ArrayList<String> extractLinks() throws IOException, MalformedURLException {
        
        ArrayList<String> links = new ArrayList<String>();
        
		
        Elements link = this.doc.getElementsByClass("storylink");
        // String pattern1 = "<td class=\"title\"><a[^>]*>([^<]*)<\\/a>";
        // Pattern p1 = Pattern.compile(pattern1);
        // Matcher m1 = p1.matcher(doc1.outerHtml());
        // while (m1.find())
        // {
        //     headings.add(m1.group(1));
        // }
        for (Element l: link) {
            links.add(l.attr("href"));
        }
        return links;
    }

    private ArrayList<String> extractHeadings() throws IOException {

        ArrayList<String> headings = new ArrayList<String>();
        // Document doc1 = Jsoup.connect("https://news.ycombinator.com/").get();
        Elements hd = this.doc.getElementsByClass("storylink");
        // String pattern1 = "<td class=\"title\"><a[^>]*>([^<]*)<\\/a>";
        // Pattern p1 = Pattern.compile(pattern1);
        // Matcher m1 = p1.matcher(doc1.outerHtml());
        // while (m1.find())
        // {
        //     headings.add(m1.group(1));
        // }
        for (Element e: hd) {
            headings.add(e.text());
        }
        //System.out.println("extractHeading");
        //System.out.println(m1.group(1)); // +1 is required because the first group is always the entire match.
        return headings;
    }

    public ArrayList<Article> parse() throws IOException, InterruptedException {
        // this.doc = Jsoup.connect("https://news.ycombinator.com/").get();
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