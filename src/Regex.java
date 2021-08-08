import java.io.IOException;
// Import the matcher
import java.util.regex.Matcher;
// Import the pattern
import java.util.regex.Pattern;


// A pattern is used in a matcher


public class Regex {
    public static void main(String[] args) throws IOException, InterruptedException {

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
        String pattern1 = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
        
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

    
    }
}
