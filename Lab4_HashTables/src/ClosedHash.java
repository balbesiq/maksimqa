import java.util.Map;

public class ClosedHash{
    static int ass = 0, comp = 0,a=0,b=0;
    static int m = 13;

    public static void main(String[] args) {
        String[] words = {"Хто", "грошима", "величається", "той", "без", "душі", "зостачається"};

        String[] table = buildClosedHashTable(words, 0);
        displayHashTable(table);
        search(table, 0);
        System.out.println("\n\n\n");

        ass = 0;
        comp = 0;

        m = 16;

        String[] table2 = buildClosedHashTable(words, 1);
        displayHashTable(table2);
        search(table2, 1);
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
        int sum = 0;
        for (Character ch : word.toCharArray()) {
            Integer pos = letters.get(ch);
            ass++;
            comp++;
            if (pos != null) sum += pos;
        }
        if(a<7) {
            System.out.println("word: " + word + " sum: " + sum);
            a++;
        }
        return sum % m;
    }

    static double A = 0.6180339887;

    static int hashFromMapMultiply(String word) {
        int sum = 0;
        for (Character ch : word.toCharArray()) {
            Integer pos = letters.get(ch);
            ass++;
            comp++;
            if (pos != null) sum += pos;
        }
        if(b<7) {
            System.out.println("word: " + word + " sum: " + sum);
            b++;
        }
        return (int) Math.floor(m * ((sum * A) % 1));
    }

    static String[] buildClosedHashTable(String[] words, int a) {
        String[] hashTable = new String[m];

        for (String word : words) {
            word = word.toUpperCase();
            int startAddress = (a == 0) ? hashFromMapDivide(word) : hashFromMapMultiply(word);
            boolean inserted = false;

            for (int i = 0; i < m; i++) {
                int address = (startAddress + i) % m;
                ass++;
                comp++;
                if (hashTable[address] == null) {
                    hashTable[address] = word;
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                System.out.println("Can't add word, table already full: " + word);
            }
        }

        return hashTable;
    }

    static void search(String[] table, int a) {
        int totalComparisons = 0;
        int maxComp = 0;
        String maxWord = "";
        int wordCount = 0;

        for (String word : table) {
            if (word == null) continue;
            wordCount++;

            int startAddress = (a == 0) ? hashFromMapDivide(word) : hashFromMapMultiply(word);
            int i = 0;
            int comparisons = 0;

            while (i < m) {
                int j = (startAddress + i) % m;
                comparisons++;

                if (table[j] != null && table[j].equals(word)) {
                    break; // слово найдено
                }
                i++;
            }

            System.out.println("Word: " + word + " Comparisons: " + comparisons);

            totalComparisons += comparisons;

            if (comparisons > maxComp) {
                maxComp = comparisons;
                maxWord = word;
            }
        }

        double average = (wordCount == 0) ? 0 : (double) totalComparisons / wordCount;

        System.out.println("Word with most comparisons: " + maxWord + " (" + maxComp + ")");
        System.out.println("Average comparisons: " + average);
    }

    static void displayHashTable(String[] hashTable) {
        System.out.println("\nHash Table for m = "+ m);
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println("Index:" + i + " element: " + (hashTable[i] == null ? "[]" : hashTable[i]));
        }
        System.out.println("Statistics for table creation:");
        System.out.println("Assignees: " + ass + " Comparisons: " + comp+"\n");
    }
}