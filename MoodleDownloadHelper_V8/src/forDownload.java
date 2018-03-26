import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class forDownload {

    private static final String USERNAME = "ya721pau";
    private static final String PASSWORD = "htwg-gai-22";
    private static final String GET_URL  = "https://moodle.htwg-konstanz.de/moodle/index.php";
    private static final String POST_URL = "https://moodle.htwg-konstanz.de/moodle/course/view.php?id=2931";

    public static void main(String[] args) {
        String cookies = doGet(GET_URL);
        doPost(POST_URL, cookies);
    };

    /**
     * Send the initial GET request which will forward to the login page
     * and retrieve cookies sent by the server.
     * Cookies are formatted according to HTTP specification so they can be
     * passed to the next request Cookie header.
     * @param getURL URL to get
     */
    public static String doGet(String getURL) {
        StringBuilder formattedCookies = new StringBuilder();
        try {
            URL url = new URL(getURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            Map<String, List<String>> headers = conn.getHeaderFields();
            List<String> cookies = headers.get("Set-Cookie");
            ListIterator<String> it = cookies.listIterator();
            while (it.hasNext()) {
                String[] parts = it.next().split("; ");
                formattedCookies.append(parts[0]);
                if (it.hasNext()) {
                    formattedCookies.append("; ");
                }
            }
            System.out.println("\n\nGET OUTPUT");
            printContent(is);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return formattedCookies.toString();
    }

    /**
     * Post the form parameters and get page content.
     * @param postURL URL to post to
     * @param cookies The cookies to send
     */
    public static void doPost(String postURL, String cookies) {
        try {
            String postData = String.format("j_username=%s&j_password=%s",
                URLEncoder.encode(USERNAME, "UTF-8"), PASSWORD);
            URL url = new URL(postURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Cookie", cookies);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(postData);
            out.close();
            InputStream is = conn.getInputStream();
            System.out.println("\n\nPOST OUTPUT");
            printContent(is);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }    

    public static void printContent(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}