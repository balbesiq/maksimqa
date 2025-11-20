import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static String filename;
    static int found = 0, assignees = 0, comparisons = 0, recursion = 0, directories = 0, files=0, currentDepth =0,maxDepth=0;
    static long start, finish;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter root directory");
        String dir = sc.nextLine();

        System.out.println("Enter filename(with extention)");
        filename = sc.nextLine();

        start = System.nanoTime();
        dfs(dir);


        System.out.println(
                "\nAssignees: " + assignees+
                        "\nComparisons: "+comparisons+
                        "\nRecursion callls: "+recursion+
                        "\nDirectories visited: "+directories +
                        "\nFiles checked: "+files+
                        "\nObjects checked(directories+files): "+ (files+directories)+
                        "\nTime taken:"+((double)(finish-start)/1000000)+
                        "\nMaximum Recursion level: "+maxDepth
        );

        found = 0;
        assignees = 0;
        comparisons = 0;
        directories = 0;
        files=0;
        maxDepth=0;

        System.out.println("\n\n");

        start = System.nanoTime();
        bfs(dir);
        System.out.println(
                "\nAssignees: " + assignees+
                        "\nComparisons: "+comparisons+
                        "\nDirectories visited: "+directories +
                        "\nFiles checked: "+files+
                        "\nObjects checked(directories+files): "+ (files+directories)+
                        "\nTime taken:"+((double)(finish-start)/1000000)+
                        "\nMaximum Depth reached: "+maxDepth
        );
    }

    static void dfs(String dir){
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path obj : stream) {
                assignees++;

                comparisons++;
                if (found == 1) {
                    currentDepth--;

                    return;
                }

                comparisons++;
                if (Files.isDirectory(obj)) {
                    directories++;
                    recursion++;
                    dfs(obj.toAbsolutePath().toString());
                } else {
                    files++;
                    System.out.println(obj.toAbsolutePath());
                    comparisons++;
                    if (obj.getFileName().toString().equals(filename)) {
                        finish = System.nanoTime();
                        System.out.println(obj.toAbsolutePath() + " --- FILE FOUND ");
                        found++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении каталога: " + e.getMessage());
        }

        currentDepth--;
    }

    static void bfs(String startDir) {
        Queue<Pair<Path, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(Paths.get(startDir), 1));

        while (!queue.isEmpty()) {
            comparisons++;

            Pair<Path, Integer> currentPair = queue.poll();
            Path current = currentPair.getKey();
            int depth = currentPair.getValue();
            assignees++;
            directories++;

            if (depth > maxDepth) maxDepth = depth;

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(current)) {
                for (Path obj : stream) {
                    assignees++;

                    comparisons++;
                    if (Files.isDirectory(obj)) {
                        queue.add(new Pair<>(obj.toAbsolutePath(), depth + 1));
                    } else {
                        files++;

                        comparisons++;
                        if (obj.getFileName().toString().equals(filename)) {
                            finish = System.nanoTime();
                            System.out.println(obj.toAbsolutePath() + " --- FILE FOUND ");
                            return;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка при чтении каталога: " + e.getMessage());
            }
        }
    }

    static class Pair<K, V> {
        K key;
        V value;
        Pair(K key, V value) { this.key = key; this.value = value; }
        K getKey() { return key; }
        V getValue() { return value; }
    }
}