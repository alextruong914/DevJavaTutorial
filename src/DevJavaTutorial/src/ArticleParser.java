package DevJavaTutorial.src;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleParser {
	private HttpResponse<String> response;
	
	private String extractHeading() {
		return this.extractHeading(0);
	}
	private String extractHeading(int index) {
		// using the parsed respsonse
		String str1 = response.body();
        String pattern1 = "<td class=\"title\"><a[^>]*>([^<]*)<\\/a>";
        
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(str1);
        boolean b1 = m1.find();
        //System.out.println("extractHeading");
        //System.out.println(m1.group(index + 1)); // +1 is required because the first group is always the entire match.
        return m1.group(index + 1);
    }
	
	private String extractLink() {
		return this.extractLink(0);
	}
    private String extractLink(int index) {
    	// using the parsed response
		String str2 = response.body();
        String pattern2 = "<td class=\\\"title\\\"><a href=\"([^\"]*).*\">[^<]*<\\/a>";
        
        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(str2);
        boolean b2 = m2.find();
        //System.out.println("extractLink");
        //System.out.println(m1.group(index + 1)); // +1 is required because the first group is always the entire match.
        return m2.group(index + 1);
    }

    private String extractTimePosted() {
		return this.extractTimePosted(0);
	}
    
	private String extractTimePosted(int index) {
		// using the parsed respsonse
		// Do this for homework
//      System.out.println("Not Implemented");
		String str3 = response.body();
        String pattern3 = "<span class=\\\"age\\\" title=\\\"([^\\\"]*).*\\\"><a href=\\\"([^\\\"]*).*\\\">[^<]*<\\/a><\\/span>";
        
        Pattern p3 = Pattern.compile(pattern3);
        Matcher m3 = p3.matcher(str3);
        boolean b3 = m3.find();
        return m3.group(index + 1);
    }
	private String extractContent() throws IOException, InterruptedException {
		return this.extractContent(0);
	}
    
    private String extractContent(int index) throws IOException, InterruptedException {
    	// Do this for homework
    	// Note, this one is harder because it will require you to do another HTTP get on this.link
    	
    	/*
    	 * HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(this.link))
            .GET()
            .build();

        this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
    	 */
    	HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(this.link))
            .GET()
            .build();

        this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        String str4 = this.response.body();

        String pattern4 = "<meta name=\\\"description\\\" content=\\\"([^\\\"]*)\\\">";
        Pattern p4 = Pattern.compile(pattern4);
        Matcher m4 = p4.matcher(str4);
        boolean b4 = m4.find();
        return m4.group(index + 1);
 
    }
    
    private String extractImage() throws IOException, InterruptedException {
		return this.extractImage(0);
	}
    
    private String extractImage(int index) throws IOException, InterruptedException {
        /* Can be the first image on the page */
    	// This also needs to use the Get above in extractContent()
    	//Do this for homework
    	HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(this.link))
            .GET()
            .build();

        this.response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        String str5 = this.response.body();
        String pattern5 = "<meta \\w*=\\\"\\w*:image\\\" content=\\\"([^\\\"]*)\\\">";
        Pattern p5 = Pattern.compile(pattern5);
        Matcher m5 = p5.matcher(str5);
        boolean b5 = m5.find();
        if (b5==false) {
        	return null;
        } else
        return m5.group(index + 1);
    }

    private String url;
	private String link;
	private String heading;
	private String timePosted;
	private String image;
	private String content;

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
    
    public Article parse(int index) throws IOException, InterruptedException {

        // System.out.println(response.body());
        
        this.heading = extractHeading();
        this.link = extractLink();
        this.timePosted = extractTimePosted();
        this.content = extractContent();
        this.image = extractImage();
    	
    	System.out.println(index);
    	return new Article(this.heading, this.link, this.timePosted, this.content, this.image);
    }

}
