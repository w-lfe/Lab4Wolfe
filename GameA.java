import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class GameA {
    private final static String QUEUE_NAME = "game_queue";

    /**
     *
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        Game game = new Game("Space Game", 100, 50, "OAKESCRUISER", "KAPTAIN KADIN", 100, 50);
        String flatFile = game.toFlatFile();
        System.out.println("Original: " + flatFile);

        String encryptedFlatFile = CaesarCipher.encrypt(flatFile, 3);
        System.out.println("Encrypted: " + encryptedFlatFile);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, encryptedFlatFile.getBytes());
            System.out.println(" [x] Sent '" + encryptedFlatFile + "'");
        }
    }
}

