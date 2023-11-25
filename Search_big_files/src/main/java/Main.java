import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main
{
    private static long sizeMB;
    private static String pathFolder;
    static TreeMap<Double, String> listFiles = new TreeMap<>(Collections.reverseOrder());

    public static void main(String[] args)
    {
        pathFolder = args[0];
//        String pathFolder = "C:\\";
        try
        {
                sizeMB = Integer.parseInt(args[1]) * 1_048_576L;
        } catch (ArrayIndexOutOfBoundsException e) {}

//        sizeMB = 128_000_000L;
        String size;

        File path = new File(pathFolder);
        listFiles.putAll(new ForkJoinPool().invoke(new RecursiveSearchFiles(path, sizeMB)));

        for (Double key : listFiles.keySet())
        {
            if (key < 1048_576)
            {
                size = String.format("%.2f", key / 1048);
                System.out.println(size + " KB " + listFiles.get(key));
            }

            if (key >= 1048_576 && key < 1_073_741_824)
            {
                size = String.format("%.2f", key / 1048_576);
                System.out.println(size + " MB " + listFiles.get(key));
            }
            if (key >= 1_073_741_824 && key < 1_099_511_627_776L)
            {
                size = String.format("%.2f", key / 1_073_741_824);
                System.out.println(size + " GB " + listFiles.get(key));
            }
        }


    }

//    private static void searchFiles(File path)
//    {
//        try
//        {
//            for (File obj : path.listFiles())
//            {
//                if (obj.isDirectory())
//                {
//                    searchFiles(obj);
//                } else
//                {
//                    if (obj.length() >= sizeMB)
//                    {
//                        listFiles.put((double) obj.length(), obj.getAbsolutePath());
//                    }
//                }
//            }
//        } catch (NullPointerException ex)
//        {
//            System.out.println(ex.getMessage() + " " + obj);
//        }
//    }
}
