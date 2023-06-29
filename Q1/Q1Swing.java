import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Q1Swing {
    private JFrame frame;
    private JPanel panel;
    private JTextArea outputTextArea;
    private JButton simulateButton;

    public Q1Swing() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create the main JFrame
        frame = new JFrame("Dice Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main JPanel to hold the components
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the JTextArea for output
        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create the "Simulate" button
        simulateButton = new JButton("Simulate");
        simulateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateRolls();
            }
        });
        panel.add(simulateButton, BorderLayout.SOUTH);

        // Add the panel to the frame and display the GUI
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void simulateRolls() {
        // Define probabilities for each sum of two dice
        Map<String, Integer> probabilities = new HashMap<>();
        probabilities.put("2", 3);
        probabilities.put("3", 4);
        probabilities.put("4", 8);
        probabilities.put("5", 12);
        probabilities.put("6", 16);
        probabilities.put("7", 17);
        probabilities.put("8", 14);
        probabilities.put("9", 11);
        probabilities.put("10", 8);
        probabilities.put("11", 5);
        probabilities.put("12", 2);

        // Calculate the total probability
        int totalProbability = probabilities.values().stream().mapToInt(Integer::intValue).sum();

        // Normalize probabilities to get a cumulative distribution
        Map<String, Double> cumulativeProbabilities = new HashMap<>();
        double cumulativeSum = 0;
        for (Map.Entry<String, Integer> entry : probabilities.entrySet()) {
            double probability = entry.getValue() / (double) totalProbability;
            cumulativeSum += probability;
            cumulativeProbabilities.put(entry.getKey(), cumulativeSum);
        }

        // Simulate the rolls
        Map<String, Integer> rollResults = new HashMap<>();
        int numRolls = 1000;
        Random random = new Random();

        for (int i = 0; i < numRolls; i++) {
            // Generate two random numbers between 1 and 6 to represent dice rolls
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            int sum = dice1 + dice2;

            String result = Integer.toString(sum);
            rollResults.put(result, rollResults.getOrDefault(result, 0) + 1);
        }

        // Print the results
        outputTextArea.setText("");
        for (Map.Entry<String, Integer> entry : probabilities.entrySet()) {
            String sum = entry.getKey();
            int count = rollResults.getOrDefault(sum, 0);
            outputTextArea.append("Sum " + sum + ": " + count + " occurrences\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Q1Swing();
            }
        });
    }
}
