import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class GameB {
    private final static String QUEUE_NAME = "game_queue";

    /**
     *
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String encryptedFlatFile = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + encryptedFlatFile + "'");

            String decryptedFlatFile = CaesarCipher.decrypt(encryptedFlatFile, 3);
            System.out.println("Decrypted: " + decryptedFlatFile);

            Game game = Game.fromFlatFile(decryptedFlatFile);
            System.out.println("Received game object: " + game);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
