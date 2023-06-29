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

public class Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> expressions = new ArrayList<>();

        // Read expressions from the user until "end" is entered
        System.out.println("Enter expressions (enter 'end' to finish):");
        String expression = scanner.nextLine();
        while (!expression.equalsIgnoreCase("end")) {
            expressions.add(expression);
            expression = scanner.nextLine();
        }

        // Evaluate the expressions using the mathjs.org API
        try {
            List<String> results = evaluateExpressions(expressions);

            // Display the results
            System.out.println("Results:");
            for (int i = 0; i < expressions.size(); i++) {
                String result = results.get(i);
                System.out.println(expressions.get(i) + " => " + result);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while evaluating the expressions: " + e.getMessage());
        }

        scanner.close();
    }

    private static List<String> evaluateExpressions(List<String> expressions) throws IOException {
        List<String> results = new ArrayList<>();

        // Evaluate each expression using the mathjs.org API
        for (String expression : expressions) {
            // Construct the URL for the mathjs.org API with the expression parameter
            String url = "https://api.mathjs.org/v4/?expr=" + URLEncoder.encode(expression, StandardCharsets.UTF_8);;
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Send the HTTP request and read the response
            try (InputStream inputStream = conn.getInputStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 Scanner scanner = new Scanner(inputStreamReader)) {

                // Read the response content as a string
                String result = scanner.useDelimiter("\\A").next().trim();
                results.add(result);
            }
        }

        return results;
    }
}
