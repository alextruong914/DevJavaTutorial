package DevJavaTutorial.src;

import java.io.IOException;
// Import the matcher
import java.util.regex.Matcher;
// Import the pattern
import java.util.regex.Pattern;

import java.util.ArrayList;


// A pattern is used in a matcher


public class ArticleExtractor {
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
