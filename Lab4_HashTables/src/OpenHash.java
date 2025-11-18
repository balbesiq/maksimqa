import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class OpenHash {
    static int comp =0,ass=0, m = 13,a=0,b=0;
    public static void main(String[] args) {

        String[] words = {"Хто", "грошима", "величається", "той", "без", "душі", "зостачається"};

        List<List<String>> table = buildOpenHashTable(words, 0);
        displayHashTable(table);
        search(table);

        System.out.print("\n\n\n");
        ass=0;
        comp=0;

        m=16;

        List<List<String>> table2 = buildOpenHashTable(words, 1);
        displayHashTable(table2);
        search(table2);
    }

    static Map<Character, Integer> letters = Map.ofEntries(
            Map.entry('А', 1), Map.entry('Б', 2), Map.entry('В', 3), Map.entry('Г', 4),
            Map.entry('Ґ', 5), Map.entry('Д', 6), Map.entry('Е', 7), Map.entry('Є', 8),
            Map.entry('Ж', 9), Map.entry('З', 10), Map.entry('И', 11), Map.entry('І', 12),
            Map.entry('Ї', 13), Map.entry('Й', 14), Map.entry('К', 15), Map.entry('Л', 16),
            Map.entry('М', 17), Map.entry('Н', 18), Map.entry('О', 19), Map.entry('П', 20),
            Map.entry('Р', 21), Map.entry('С', 22), Map.entry('Т', 23), Map.entry('У', 24),
            Map.entry('Ф', 25), Map.entry('Х', 26), Map.entry('Ц', 27), Map.entry('Ч', 28),
            Map.entry('Ш', 29), Map.entry('Щ', 30), Map.entry('Ь', 31),
            Map.entry('Ю', 32), Map.entry('Я', 33)
    );

    static int hashFromMapDivide(String word) {
        int sum=0;
        for(Character ch: word.toCharArray()) {
            Integer pos = letters.get(ch);
            ass++;

            comp++;
            if(pos!=null) {
                sum+=pos;
            }
        }
        if(a<7) {
            System.out.println("word: " + word + " sum: " + sum);
            a++;
        }
        return sum%m;
    }

    static double A = 0.6180339887;

    static int hashFromMapMultiply(String word) {
        int sum=0;
        for(Character ch: word.toCharArray()) {
            Integer pos = letters.get(ch);
            ass++;

            comp++;
            if(pos!=null) {
                sum+=pos;
            }
        }
        if(b<7) {
            System.out.println("word: " + word + " sum: " + sum);
            b++;
        }
        return (int) Math.floor(m * ((sum * A) % 1));
    }

    static List<List<String>> buildOpenHashTable(String[] words, int a ) {
        List<List<String>> hashTable = new ArrayList<>(m);
        int address=0;
        for (int i = 0; i < m; i++) {
            hashTable.add(new ArrayList<>());
        }

        for (String word : words) {
            word = word.toUpperCase();

            ass++;
            address = (a == 0) ? hashFromMapDivide(word) : hashFromMapMultiply(word);

            ass++;
            hashTable.get(address).add(word);
        }

        return hashTable;
    }

    static void search(List<List<String>> table) {
        int totalComparisons = 0;
        int maxComp = 0;
        String maxWord = "";

        for (List<String> chain : table) {
            for (int i = 0; i < chain.size(); i++) {
                String word = chain.get(i);
                int compForWord = i + 1;
                totalComparisons += compForWord;
                System.out.println("Word: " + word + " Comparisons: " + compForWord);

                if (compForWord > maxComp) {
                    maxComp = compForWord;
                    maxWord = word;
                }
            }
        }

        double average = table.stream().mapToInt(List::size).sum() == 0 ? 0 :
                (double) totalComparisons / table.stream().mapToInt(List::size).sum();

        System.out.println("Word with most comparisons: " + maxWord + " (" + maxComp + ")");
        System.out.println("Average comparisons: " + average);
    }

    static void displayHashTable(List<List<String>> hashTable) {
        System.out.println("\nHash Table for m = "+ m);
        for (int i = 0; i < hashTable.size(); i++) {
            System.out.println("Index:"+i+" element: "+hashTable.get(i));
        }
        System.out.println("Statistics for table creation:");
        System.out.println("Assignees: "+ass+ " Comparisons:"+comp+"\n");
    }
}