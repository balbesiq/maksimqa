import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter root directory");
        String rdir = sc.nextLine();

        System.out.println("Enter filename(with extention)");
        filename = sc.nextLine();

        browser(rdir);

        System.out.println("Assignees: " + assignees+"  Comparissons: "+comparissons+"  Recursion callls: "+recursion);
    }
    static String filename;
    static int found = 0, assignees = 0, comparissons = 1, recursion = 1;

    static void browser(String dir){

        Path dirPath = Paths.get(dir);
        assignees++;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            assignees++;

            for (Path entry : stream) {

                if (found ==1) return;
                comparissons++;

                comparissons++;
                if(Files.isDirectory(entry)) {
                    System.out.println("Directory: " + entry);
                    recursion++;
                    browser(entry.toAbsolutePath().toString());
                }

                comparissons++;
                if(entry.getFileName().toString().equals(filename)) {
                    System.out.println(entry.toAbsolutePath().toString() + " --- FILE FOUND ");
                    found++;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении каталога: " + e.getMessage());
        }
    }
}