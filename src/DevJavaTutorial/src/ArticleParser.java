package DevJavaTutorial.src;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class ArticleParser {
	private HttpResponse<String> response;
	
	private ArrayList<String> extractHeadings() {
		// using the parsed respsonse
        ArrayList<String> headings = new ArrayList<String>();
		String str1 = response.body();
        String pattern1 = "<td class=\"title\"><a[^>]*>([^<]*)<\\/a>";
        

        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(str1);
        boolean b1 = m1.find();
        while (m1.find())
        {
            headings.add(m1.group(1));
        }
        //System.out.println("extractHeading");
        //System.out.println(m1.group(1)); // +1 is required because the first group is always the entire match.
        return headings;
    }
	
    private ArrayList<String> extractLinks() {
    	// using the parsed response
        ArrayList<String> links = new ArrayList<String>();
		String str2 = response.body();
        String pattern2 = "<td class=\\\"title\\\"><a href=\"([^\"]*).*\">[^<]*<\\/a>";

        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(str2);
        boolean b2 = m2.find();

        while (m2.find())
        {
            links.add(m2.group(1));
        }
        //System.out.println("extractHeading");
        //System.out.println(m1.group(1)); // +1 is required because the first group is always the entire match.
        return links;
    }

	private ArrayList<String> extractTimePosteds() {
		// using the parsed respsonse
        ArrayList<String> timePosted = new ArrayList<String>();

		// Do this for homework
        //System.out.println("Not Implemented");
		String str3 = response.body();
        String pattern3 = "<span class=\\\"age\\\" title=\\\"([^\\\"]*).*\\\"><a href=\\\"([^\\\"]*).*\\\">[^<]*<\\/a><\\/span>";
        
        Pattern p3 = Pattern.compile(pattern3);
        Matcher m3 = p3.matcher(str3);
        boolean b3 = m3.find();

        while (m3.find())
        {
            timePosted.add(m3.group(1));
        }
        return timePosted;
    }
    
    private String extractContent(String link) throws IOException, InterruptedException {
    	// Do this for homework
    	// Note, this one is harder because it will require you to do another HTTP get on this.link
    	if (link == null) {
            throw new IOException("Link must not be null");
        }
    	/*
    	 * HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(this.link))
            .GET()
            .build();

        this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
    	 */
    	try {
            HttpClient myClient = HttpClient.newHttpClient();
            HttpRequest myRequest = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();
            this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
            String str4 = this.response.body();
            // System.out.println(str4);
            // String pattern4 = "<meta name=\\\"description\;\\" content=\\\"([^\\\"]*)\\\">";
            String pattern4 = "<meta [^=]*=\"(?:og:){0,1}description\" content=\"([^\"]*)\" ?\\/>";
            Pattern p4 = Pattern.compile(pattern4);
            Matcher m4 = p4.matcher(str4);
            boolean b4 = m4.find();
            if (b4==false) {
                return null;
            } else
            return m4.group(1);
        }
        catch (ConnectException e) {
            System.out.println("Unable to resolve link: " + link);
            return null;
        }
 
    }
    
    private String extractImage(String link) throws IOException, InterruptedException {
        /* Can be the first image on the page */
    	// This also needs to use the Get above in extractContent()
    	//Do this for homework
    	HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(link))
            .GET()
            .build();

        this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        String str5 = this.response.body();
        String pattern5 = "\\\"([^\\\"]*(?:gif|png|jpg|webp))\\\"";
        Pattern p5 = Pattern.compile(pattern5);
        Matcher m5 = p5.matcher(str5);
        boolean b5 = m5.find();
        if (b5==false) {
        	return null;
        } else
        return m5.group(1);
    }

    private String url;

    public ArticleParser(String url) throws IOException, InterruptedException {
        // This is a constructor
        // Handle either the input string or go get a url
    	this.url = url;

    	// Get the url and resolve the HTML
    	HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        
    }
    
    public ArrayList<Article> parse() throws IOException, InterruptedException {
        // Parse all of the matches we can find, until we run out
        // System.out.println(response.body());
        
        // Count the total number of matches we find
        int counter = 0;

        // We will assume that the number of matches of all these is the same
        // If there are different numbers of matches, it will create errors!
        ArrayList<String> headings = extractHeadings();

        // Can you now implement the extractLinks function?
        ArrayList<String> links = extractLinks();
        // ArrayList<String> timePosteds = extractTimePosteds();
        
        // For Homework, complete these 3 remaining fields of the array list!
        // For content, it will be different because it only gets a single one. 
        ArrayList<String> timePosted = extractTimePosteds();
    	
        /*
        Now we will create a for loop to create many articles, instead of printing just the headings
        */
        ArrayList<Article> ar = new ArrayList<Article>();
        for (int i = 0; i<(links.size()-1); i++) {
            String content = extractContent(links.get(i));
            String image = extractImage(links.get(i));
            Article a = new Article(headings.get(i), links.get(i), timePosted.get(i), content, image);

            ar.add(a);
        }
    	return ar;
    }

}
