import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Q2Swing {
    private List<String> expressions;
    private List<String> results;
    private JFrame frame;
    private JPanel panel;
    private JTextArea outputTextArea;
    private JTextField inputTextField;
    private JButton evaluateButton;

    public Q2Swing() {
        expressions = new ArrayList<>();
        results = new ArrayList<>();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Expression Evaluator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        inputTextField = new JTextField(20);
        panel.add(inputTextField, BorderLayout.NORTH);

        evaluateButton = new JButton("Evaluate");
        evaluateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                evaluateExpression();
            }
        });
        panel.add(evaluateButton, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void evaluateExpression() {
        String expression = inputTextField.getText().trim();
        if (expression.equalsIgnoreCase("end")) {
            inputTextField.setEditable(false);
            evaluateButton.setEnabled(false);
            evaluateAllExpressions();
        } else {
            expressions.add(expression);
            inputTextField.setText("");

            try {
                // Encode the expression for URL
                String encodedExpression = URLEncoder.encode(expression, StandardCharsets.UTF_8);

                // Evaluate the expression using the mathjs.org API
                String result = evaluateExpression(encodedExpression);
                results.add(result);

                outputTextArea.append(expression + " => " + result + "\n");
            } catch (IOException ex) {
                results.add("An error occurred while evaluating the expression: " + ex.getMessage());
                outputTextArea.append("An error occurred while evaluating the expression: " + ex.getMessage() + "\n");
            }
        }
    }

    private void evaluateAllExpressions() {
        for (int i = 0; i < expressions.size(); i++) {
            String expr = expressions.get(i);
            String result = results.get(i);
            outputTextArea.append(expr + " => " + result + "\n");
        }
    }

    private String evaluateExpression(String encodedExpression) throws IOException {
        // Construct the URL for the mathjs.org API with the encoded expression parameter
        String url = "https://api.mathjs.org/v4/?expr=" + encodedExpression;
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Send the HTTP request and read the response
        try (InputStream inputStream = conn.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(inputStreamReader)) {

            // Read the response content as a string
            return scanner.useDelimiter("\\A").next().trim();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Q2Swing();
            }
        });
    }
}
