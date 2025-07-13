import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = { 21, 1, 20, 23 };
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day + 3);
        int recursiveDP = chooseHobbyRecursiveDP(startNumbers, day + 3, new int[day + 4]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive + " | RecursiveDP = " + recursiveDP);
        System.out.println();
    }

    // Итеративный вариант (дан по условию)
    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4;
            int prev = numbers.get(index - 1);
            int prePrePrev = numbers.get(index - 3);
            numbers.add((prev * prePrePrev) % 10 + 1);
        }
        return numbers.get(numbers.size() - 1);
    }

    // Простой рекурсивный вариант (без динамики)
    public static int chooseHobbyRecursive(int[] startNumbers, int day) {
        if (day < 4) {
            return startNumbers[day];
        }
        int prev = chooseHobbyRecursive(startNumbers, day - 1);
        int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3);
        return (prev * prePrePrev) % 10 + 1;
    }

    // Рекурсивный вариант с динамическим программированием (мемоизация)
    public static int chooseHobbyRecursiveDP(int[] startNumbers, int day, int[] memory) {
        if (day < 4) {
            return startNumbers[day];
        }
        if (memory[day] != 0) {
            return memory[day];
        }
        int prev = chooseHobbyRecursiveDP(startNumbers, day - 1, memory);
        int prePrePrev = chooseHobbyRecursiveDP(startNumbers, day - 3, memory);
        int result = (prev * prePrePrev) % 10 + 1;
        memory[day] = result;
        return result;
    }
}
