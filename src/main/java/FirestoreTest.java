import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FirestoreTest {

    private static final String API_KEY = "";
    private static final String PROJECT_ID = "";

    // Method to add a document to Firestore
    public static void addDocument() throws Exception {
        String urlString = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/users?key=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        // JSON data to add to Firestore
        String jsonInputString = "{\"fields\": {\"name\": {\"stringValue\": \"Test User\"}, \"age\": {\"integerValue\": \"25\"}}}";

        // Send the JSON data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read response
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    // Method to get documents from Firestore
    public static void getDocuments() throws Exception {
        String urlString = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/users?key=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        // Read response
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Adding document to Firestore:");
            addDocument();  // Adds a test document

            System.out.println("\nFetching documents from Firestore:");
            getDocuments();  // Fetches all documents in the "users" collection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
