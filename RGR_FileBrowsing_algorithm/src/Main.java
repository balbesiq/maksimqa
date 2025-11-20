import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    //Ініціалізація зміної для сберігання ім'я шуканого файлу
    static String filename;
    //Ініціалізація зміних для обчислення складності алгоритмів, та підрвхунку оглянутих об'єктів
    static int found = 0,assignees = 0,comparisons = 0,recursion = 0, directories = 0, files=0, currentDepth =0,maxDepth=0;
    //Ініціалізація змінних для відситеження часу виконання
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
                "DFS statistics" +
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
                "BFS statistics" +
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
        currentDepth++;//Відстеження рівня рекурсіх, який буде досягнено алгоритмом
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) { // Відкриття потоку читання данних
            for (Path obj : stream) { // Для кожного об'єкту у потоці
                assignees++;

                comparisons++;
                if (found == 1) { //Якщо файл знайдено повернення по рекурсійному зануренню
                    currentDepth--;
                    return;
                }

                comparisons++;
                if (Files.isDirectory(obj)) { //Якщо об'єкт - директроіія: рекурсивно викликаємо для нбого функції пошуку
                    directories++;
                    recursion++;
                    dfs(obj.toAbsolutePath().toString());
                } else {
                    files++;
                    comparisons++;
                    if (obj.getFileName().toString().equals(filename)) { //Якщо ім'я об'єкту співпадає з шуканим ім'я
                        finish = System.nanoTime();                      //Встановлюємо змінюєио значення маркеру found
                        System.out.println(obj.toAbsolutePath() + " --- FILE FOUND ");
                        found++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении каталога: " + e.getMessage());//Якщо каталог недоступний для читання через обмеження прав
        }
        currentDepth--;
    }

    static void bfs(String startDir) {
        Queue<Pair<Path, Integer>> queue = new ArrayDeque<>();//Створюємо чергу
        queue.add(new Pair<>(Paths.get(startDir), 1));//Додаємо до черги кореневий каталог

        while (!queue.isEmpty()) {
            comparisons++;

            Pair<Path, Integer> currentPair = queue.poll();//Вилучаємо каталог з черги для праці на поточному кроці
            Path current = currentPair.getKey();//Записуємо ім'я каталогу до змінної щоб відкрити поток для каталогу
            int depth = currentPair.getValue();//Отримуємо рівень вкладеності каталогу на поточному кроці
            assignees++;
            directories++;

            if (depth > maxDepth) maxDepth = depth;

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(current)) {//Відкриваємо поток читання для каталога на поточнмоу кроці
                for (Path obj : stream) {
                    assignees++;

                    comparisons++;
                    if (Files.isDirectory(obj)) {//Якщо об'єкт - директорія: додаємо до черги
                        queue.add(new Pair<>(obj.toAbsolutePath(), depth + 1));
                    } else {
                        files++;

                        comparisons++;
                        if (obj.getFileName().toString().equals(filename)) {//Якщо ім'я об'єкту співпадає з шуканим закінчуємо пошук
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

    static class Pair<K, V> {//Клас для зберігання інформації про каталоги у черзі, містить в собі ім'я каталог та йоро рівень вкладенності
        K key;
        V value;
        Pair(K key, V value) { this.key = key; this.value = value; }
        K getKey() { return key; }
        V getValue() { return value; }
    }
}