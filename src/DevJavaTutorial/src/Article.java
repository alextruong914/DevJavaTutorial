package DevJavaTutorial.src;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Article {
	// Here we can create the object with all the fields for an article!

    // Heading
	public String heading;
    // Link
    public String link;
    // Time posted
    // -- What type should this be?
    public String timePosted;
    // Content
    // -- This string might have heaps and heaps of text!
    public String content;
    // An image from the page
    // -- This is just a string for now
    public String image;
    // A constructor for the class
    
    private void extractHeading() {
        System.out.println("Not Implemented");
    }
    
    private void extractLink() {
        System.out.println("Not Implemented");
    }

    private void extractTimePosted() {
        System.out.println("Not Implemented");
    }
    
    private void extractContent() {
        System.out.println("Not Implemented");
    }
    
    private void extractImage() {
        /* Can be the first image on the page */
        System.out.println("Not Implemented");
    }

    private String url;

    @Override
    public String toString() {
        return "Heading: " + this.heading;
    }

    public Article(String url) throws IOException, InterruptedException {
        // This is a constructor
        // Handle either the input string or go get a url
    	this.url = url;

        // Get the url and resolve the HTML
    	HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpResponse<String> response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());

        // System.out.println(response.body());

        extractHeading();
        extractLink();
        extractTimePosted();
        extractContent();
        extractImage();
    }
    
}
