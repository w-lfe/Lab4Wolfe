import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameA_JSON {
    public static void main(String[] args) {
        try {
            Game game = new Game("Space Game", 100, 50, "OAKESCRUISER", "KAPTAIN KADIN", 100, 50);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(game);
            String hmac = HMACUtil.calculateHMAC(json);

            URL url = new URL("http://localhost:8080/game");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("HMAC", hmac);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            conn.disconnect();
            System.out.println("Game object sent via Web Service: " + json);
            System.out.println("HMAC: " + hmac);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

