import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String filename;
    static int found = 0, assignees = 0, comparisons = 0, recursion = 0, directories = 0, files=0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter root directory");
        String dir = sc.nextLine();

        System.out.println("Enter filename(with extention)");
        filename = sc.nextLine();

        browser(dir);

        System.out.println(
                "\n\nAssignees: " + assignees+
                "\nComparisons: "+comparisons+
                "\nRecursion callls: "+recursion+
                "\nDirectories visited: "+directories +
                "\nFiles checked: "+files+
                "\nObjects checked(directories+files): "+ (files+directories)
        );
    }

    static void browser(String dir){
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path obj : stream) {
                assignees++;

                comparisons++;
                if (found ==1) return;

                comparisons++;
                if(Files.isDirectory(obj)) {
                    directories++;
                    System.out.println("\nDirectory: " + obj);
                    System.out.println("Directories visited: "+directories+"\nFiles cheked: "+files);

                    recursion++;
                    browser(obj.toAbsolutePath().toString());
                }
                else {
                    files++;

                    comparisons++;
                    if (obj.getFileName().toString().equals(filename)) {
                        System.out.println(obj.toAbsolutePath() + " --- FILE FOUND ");
                        found++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении каталога: " + e.getMessage());
        }
    }
}