package DevJavaTutorial.src;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Article {
	// Here we can create the object with all the fields for an article!
	// now an article just contains the data and some very basic operations that an article can do

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
    
    
    @Override
    public String toString() {
        return "Heading: " + this.heading + "\nLink: " + link + "\nTime Posted: " + timePosted + "\nContent: " + content + "\nImage: " + image;
    }


    public Article(String heading, String link, String timePosted, String content, String image) throws IOException, InterruptedException {
    	this.heading = heading;
    	this.link = link;
    	this.timePosted = timePosted;
    	this.content = content;
    	this.image = image;
    }

}
