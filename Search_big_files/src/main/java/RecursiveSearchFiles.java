import java.io.File;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class RecursiveSearchFiles extends RecursiveTask<TreeMap<Double, String>>
{
    private File path;
    private static long sizeMB;
    TreeMap<Double, String> listFiles = new TreeMap<>();
    ArrayList<RecursiveSearchFiles> tasks = new ArrayList<>();

    public RecursiveSearchFiles(File path, long sizeMB)
    {
        this.path = path;
        this.sizeMB = sizeMB;
    }

    @Override
    protected TreeMap<Double, String> compute()
    {
        try
        {
            for (File obj : path.listFiles())
            {
                if (obj.isDirectory())
                {
                    RecursiveSearchFiles parseFolders = new RecursiveSearchFiles(obj, sizeMB);
                    parseFolders.fork();
                    tasks.add(parseFolders);
                } else
                {
                    if (obj.length() >= sizeMB)
                    {
                        listFiles.put((double) obj.length(), obj.getAbsolutePath());
                    }
                }
            }
        } catch (NullPointerException ex)
        {
            ex.getStackTrace();
        }
        for (RecursiveSearchFiles task : tasks)
        {
            TreeMap<Double, String> tsk = task.join();
            listFiles.putAll(tsk);
        }

        return listFiles;
    }
}
