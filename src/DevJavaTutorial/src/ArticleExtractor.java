package DevJavaTutorial.src;

import java.io.IOException;
// Import the matcher
import java.util.regex.Matcher;
// Import the pattern
import java.util.regex.Pattern;

import java.util.ArrayList;


// A pattern is used in a matcher


public class ArticleExtractor {

    public static void old() throws IOException, InterruptedException {
            // Take a string "This sentence contains some letters, numbers (1234) and symbols ()"
    // Using Pattern.compile, create a pattern to search with

    // Do it here:
    // Find whether there is a match inside for the number "234"
    String pattern = "[(](1234)[)]";
    Pattern p = Pattern.compile(pattern);

    // Store the string in a variable: "This sentence contains some letters, numbers (1234) and symbols ()"
    // Do it here:
        String str = "This sentence contains some letters, numbers (1234) and symbols ()";

    // Now, search for 123 inside of the string "str" using the Matcher m under the p.matcher method
    // Do it here:
        Matcher m = p.matcher(str);
        //boolean b = Pattern.matches("1234","123");
        boolean b = m.find(); 
        //this is the result, if it is true, that means it has found the string!

    // Now print: b
        System.out.println(b);

    // Task 2: 
    // Extract: (1234) and symbols ()
        System.out.println("\nTask 2:");
        System.out.println(m.group(0));
        
        
    // Task 3:
    // Extract the string (1234) out of the string str
    // System to print: (1234) 
        System.out.println("\nTask 3:");
        System.out.println(m.group(1));
        
    // Task 4: Extract the URL: https://github.com/HackerNews/API\">API</a>
        String str1 = "<a href=\"https://github.com/HackerNews/API\">API</a>";
        String pattern1 = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(str1);
        boolean b1 = m1.find();
        System.out.println("\nTask 4:");
        System.out.println(m1.group());
    // Task 5: Extract all urls from this string: 
        String[] str2 = {"<a href=newsfaq.html>FAQ</a>" 
             , "<a href=https://github.com/HackerNews/API>API</a>"
        	 , "<a href=security.html>Security</a>"
        	 , "<a href=http://www.ycombinator.com/legal/>Legal</a>"
             , "<a href=http://www.ycombinator.com/apply/>Apply to YC</a>"
             , "<a href=mailto:hn@ycombinator.com>Contact</a>"};
        System.out.println("\nTask 5:");
        for (String str3 : str2) 
        	{
	        String pattern2 = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
	        Pattern p2 = Pattern.compile(pattern2);
	        Matcher m2 = p2.matcher(str3);
	        boolean b2 = m2.find();
	        System.out.println(m2.group());
        }

    // Regex basic codes:
    // . - wildcard, will match anything
    // * - will keep matching, e.g. a* will match aaaaaaaaaa, .* will match any string

    // Create an article from the first article - we can use Regex today
    // Next time we will use Jsoup!


    }
    public static void main(String[] args) throws IOException, InterruptedException {



    // Either one of these is ok:
    //Article a1 = new Article(response.body());
    // OR (comment out one)
    // Parser ap = new RegexArticleParser("https://news.ycombinator.com/");
    Parser ap = new JsoupArticleParser("https://news.ycombinator.com/");
    ArrayList<Article> articles = ap.parse();
    for (Article a:articles){
        System.out.println("--------------=------------");
        System.out.println(a);
    }

    //System.out.println(articles);

    /*
    Contents:

    Heading: SoundStream: An End-to-End Neural Audio Codec 
    Link: http://ai.googleblog.com/2021/08/soundstream-end-to-end-neural-audio.html
    Date Posted: 2021-08-15T02:42:07
    Content: Audio codecs are used to efficiently compress audio to reduce either storage requirements or network bandwidth. Ideally, audio codecs should be transparent to the end user, so that the decoded audio is perceptually indistinguishable from the original and the encoding/decoding process does not introduce perceivable latency.
    Image: https://1.bp.blogspot.com/-QziOS4QToRI/YRRARL7JKKI/AAAAAAAAIBM/Sl6W1OPsf0gDR6mn1mDLqaOo7aGhCk8YgCLcBGAsYHQ/s16000/image2.png
    */
    
    // Homework: (or next lession)
    // Extending this to extract all articles, into this array:
    // 
    // Article[] articles = // a list of articles
    // For each article in articles, print the article out
    // 

    }
}
