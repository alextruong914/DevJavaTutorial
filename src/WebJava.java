import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class WebJava {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. First create a HttpClient in java
        HttpClient myClient = HttpClient.newHttpClient();
        // Now we have a client, we need to pass it a URL and get it to do something
        // A body handler is just an Enum (Generically typed  from HttpResponse.BodyHandler<T>

        //Create a request
        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://news.ycombinator.com"))
            //Hover your mouse above URL and select import
            .GET()
            .build();
        // 2. Retrieve the webpage data from hacker news https://news.ycombinator.com
        HttpResponse<String> response = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());

        
        // 3. Print this data to the console 
        System.out.println(response.statusCode());
        System.out.println(response.body());

        // System.out.println("Hello, World!"); 
    }
}
