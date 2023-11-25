import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class CoolNumbers {

    String number;

    ArrayList<String> arrayNumbers = new ArrayList<>();
    HashSet<String> hashSetNumbers = new HashSet<>();
    TreeSet<String> treeSetNumbers = new TreeSet<>();
    String[] arrayNumberSymbol = {"A", "B", "C", "E", "H", "K", "M", "O", "P", "T", "X", "Y"};

    public String generateCoolNumbers() {
        long start = System.nanoTime();
        while (hashSetNumbers.size() < 2000001) {
            int n, d;

            double a, b, c;
            String randomLetters1, randomLetters2, randomLetters3;
            a = Math.random() * 11;
            b = Math.random() * 11;
            c = Math.random() * 11;

            d = (int) Math.round(Math.random() * 199);
            n = (int) Math.round(Math.random() * 9);

            randomLetters1 = arrayNumberSymbol[(int) Math.round(a)];
            randomLetters2 = arrayNumberSymbol[(int) Math.round(b)];
            randomLetters3 = arrayNumberSymbol[(int) Math.round(c)];
            number = randomLetters1 + n + n + n + randomLetters2 + randomLetters3 + d;
            addArrayNumber(number);
            addHashSetNumbers(number);
            addTreeSetNumbers(number);

        }
        System.out.println(System.nanoTime() - start);
        return number;
    }

    private void addArrayNumber(String number) {
        arrayNumbers.add(number);
    }

    private void addHashSetNumbers(String number) {
        hashSetNumbers.add(number);
    }

    private void addTreeSetNumbers(String number) {
        treeSetNumbers.add(number);
    }

    public void printLists() {
        System.out.println(arrayNumbers.size());
        System.out.println(hashSetNumbers.size());
        System.out.println(treeSetNumbers.size());
    }


}



