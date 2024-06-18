/** Project: Lab4
 * Purpose Details: To receive JSON
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/8
 * Last Date Changed: 6/9
 * Rev:

 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class GameB_JSON {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/game", new GameHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class GameHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                String receivedHMAC = exchange.getRequestHeaders().getFirst("HMAC");


                ObjectMapper objectMapper = new ObjectMapper();
                Game game = objectMapper.readValue(json, Game.class);


                String calculatedHMAC = null;
                try {
                    calculatedHMAC = HMACUtil.calculateHMAC(json);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


                String response = "Received game: " + game.toString() + "\n" +
                        "Received HMAC: " + receivedHMAC + "\n" +
                        "Calculated HMAC: " + calculatedHMAC;
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                System.out.println("Received game object: " + game);
                System.out.println("Received HMAC: " + receivedHMAC);
                System.out.println("Calculated HMAC: " + calculatedHMAC);
                if (calculatedHMAC.equals(receivedHMAC)) {
                    System.out.println("HMAC verification passed.");
                } else {
                    System.out.println("HMAC verification failed.");
                }
            }
        }
    }
}


