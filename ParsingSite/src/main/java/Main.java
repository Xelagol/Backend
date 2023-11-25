import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static void main(String[] args) throws Exception {
        String path = "https://skillbox.ru/";
//        String path = "https://lenta.ru/";

        int depth = 0;
        Map<String, List<String>> pool = new ForkJoinPool().invoke(new RecursiveHtml(path, depth));

        ParseHTML parseHTML = new ParseHTML(path, depth);
        List<String> listUrls = parseHTML.getUrls();

        PrintWriter writer = new PrintWriter("ParsingSite/src/main/data/urls.txt");

        try {
            writer.write(path + "\n");
            pool.forEach((k, v) -> {
                writer.write(k + "\n");
                v.forEach(val ->
                        writer.write(val + "\n"));
            });
            //
//           pool.forEach((k, v)  -> {
//
////               writer.write("\t" + k + "\n");
//
//              v.forEach(val ->
//                   writer.write("\t\t" + val+ "\n")
//               );
//           });

            writer.flush();
            writer.close();
        } catch (Exception e) {
        }
    }
}