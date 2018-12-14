import java.io.*;
import java.util.*;

/**
 * A folder in which you want to count the lines is being set in the constructor.
 */
public class Counter {
    private File folder;
    private List<String> result;
    private Queue<File> fileTree;
    private int count = 0;

    public Counter() {
        folder = new File("D:\\IdeaProjects\\epam-javacore\\task07\\seabattle\\src\\main\\java\\com\\epam\\game");
        result = new ArrayList<>();
        fileTree = new PriorityQueue<>();
    }

    public void calculate() {
        getFolders();
        Queue<String> fileQueue = new PriorityQueue<>(result);
        while (!fileQueue.isEmpty()) {
            File currentFile = new File(fileQueue.remove());
            count += countLinesInFile(currentFile);
        }
    }

    private int countLinesInFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int result = 0;
        try {
            while (reader.readLine() != null) {
                result += 1;
            }
            System.out.printf("Строк: %4d; Файл: " + file.getAbsolutePath(), result);
            System.out.println();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void getFolders() {
        Collections.addAll(fileTree, folder.listFiles());
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                result.add(currentFile.getAbsolutePath());
            }
        }
    }

    public int getCount() {
        return count;
    }
}