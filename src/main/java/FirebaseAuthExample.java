import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FirebaseAuthExample {

    private static final String API_KEY = "";

    // Sign up a new user with email and password
    public static void signUpUser(String email, String password) throws Exception {
        String urlString = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        // JSON payload for sign up
        String jsonInputString = String.format("{\"email\": \"%s\", \"password\": \"%s\", \"returnSecureToken\": true}", email, password);

        // Send JSON data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            System.err.println("Sign-up failed: " + e.getMessage());
        }
    }

    // Log in an existing user with email and password
    public static void loginUser(String email, String password) throws Exception {
        String urlString = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        // JSON payload for login
        String jsonInputString = String.format("{\"email\": \"%s\", \"password\": \"%s\", \"returnSecureToken\": true}", email, password);

        // Send JSON data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // Replace with test email and password
            String testEmail = "testuser2@example.com";
            String testPassword = "testpassword123";

            // Sign up the user
            System.out.println("Signing up user:");
            signUpUser(testEmail, testPassword);

            // Log in the user
            System.out.println("\nLogging in user:");
            loginUser(testEmail, testPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
