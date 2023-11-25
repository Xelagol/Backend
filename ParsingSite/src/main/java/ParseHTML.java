import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class ParseHTML {
    private String path;
    private int depth;
    private String tab = "";
    List<String> urls = new ArrayList<>();
    List<String> haveUrl = new ArrayList<>();

    public ParseHTML(String path, int depth) throws Exception {
        this.path = path;
        this.depth = depth;
//        parseHTML();
    }


    public List<String> parseHTML() throws Exception {
        if (!haveUrl.contains(path)) {
            Document doc = Jsoup.connect(path).ignoreHttpErrors(true).get();
            Elements link = doc.select("a");
            haveUrl.add(path);
            for (int i = 0; i < depth; i++) {
                tab += "\t";
            }
            for (Element headline : link) {
                String absHref = headline.attr("abs:href");
                if (absHref.matches(".+skillbox\\.ru.*") && absHref.matches(".*/") && !haveUrl.contains(absHref)) {
//                    if (absHref.matches(".+lenta\\.ru.*") && absHref.matches(".*/") && !haveUrl.contains(absHref)) {

                    urls.add(tab + absHref);
                    haveUrl.add(absHref);
                }
            }
            return urls;
        }
        return new ArrayList<>();
    }

    public List<String> getUrls() {
        return urls;
    }
}
