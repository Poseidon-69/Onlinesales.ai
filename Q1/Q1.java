import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Q1 {
    public static void simulateRolls() {
        // Define probabilities for each sum of two dice
        Map<Integer, Integer> probabilities = new HashMap<>();
        probabilities.put(2, 3);
        probabilities.put(3, 4);
        probabilities.put(4, 8);
        probabilities.put(5, 12);
        probabilities.put(6, 16);
        probabilities.put(7, 17);
        probabilities.put(8, 14);
        probabilities.put(9, 11);
        probabilities.put(10, 8);
        probabilities.put(11, 5);
        probabilities.put(12, 2);

        // Simulate the rolls
        Map<Integer, Integer> rollResults = new HashMap<>();
        int numRolls = 1000;
        Random random = new Random();

        for (int i = 0; i < numRolls; i++) {
            // Generate two random numbers between 1 and 6 to represent dice rolls
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            int sum = dice1 + dice2;

            rollResults.put(sum, rollResults.getOrDefault(sum, 0) + 1);
        }

        // Print the results in sequence
        for (int sum = 2; sum <= 12; sum++) {
            int count = rollResults.getOrDefault(sum, 0);
            System.out.println("Sum " + sum + ": " + count + " occurrences");
        }
    }

    public static void main(String[] args) {
        simulateRolls();
    }
}
