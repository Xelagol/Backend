import java.util.*;
import java.util.concurrent.RecursiveTask;


public class RecursiveHtml extends RecursiveTask <Map<String, List<String>>> {

    public String getPath() {
        return path;
    }

    private String path;
    private int depth;
    List<RecursiveHtml> tasks = new ArrayList<>();
    static List<String> haveUrls = new ArrayList<>();
    static boolean stop = false;
    static Map<String, List<String>> allMap = new HashMap<>();

    public RecursiveHtml(String path, int depth) {
        this.path = path;
        this.depth = depth;
    }


    @Override
    protected Map<String, List<String>> compute() {
        if (haveUrls.contains(path)) {
            return new HashMap<>();
        }
        try {
            haveUrls.add(path);
            depth++;
            ParseHTML parseHTML = new ParseHTML(path, depth);
            List<String> urls = parseHTML.parseHTML();
            allMap.put(path, urls);
            for (String url : urls) {
//                Thread.sleep(100);
                RecursiveHtml recursiveHtml = new RecursiveHtml(url, depth);
                recursiveHtml.fork();
                tasks.add(recursiveHtml);
            }

            for (RecursiveHtml task : tasks) {
                allMap.putAll(task.join());
            }
            System.out.println(allMap);






        } catch (Exception e) {
        }
        return allMap;
    }
}
