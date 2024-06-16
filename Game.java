/** Project: Lab4
 * Purpose Details: Game database for other classes to extract information from
 * Course: IST 242
 * Author: Kadin
 * Date Developed: Lab1?
 * Last Date Changed: 6/9
 * Rev: bare bones code to eliminate risk for error

 */

public class Game {
    private String name;
    private int score;
    private int level;
    private String shipType;
    private String playerName;
    private int health;
    private int attack;

    public Game() {}

    public Game(String name, int score, int level, String shipType, String playerName, int health, int attack) {
        this.name = name;
        this.score = score;
        this.level = level;
        this.shipType = shipType;
        this.playerName = playerName;
        this.health = health;
        this.attack = attack;
    }

    /**
     *
     * @return
     */
    public String toFlatFile() {
        return String.join(",",
                name, String.valueOf(score), String.valueOf(level), shipType, playerName,
                String.valueOf(health), String.valueOf(attack));
    }

    /**
     *
     * @param flatFile
     * @return
     */
    public static Game fromFlatFile(String flatFile) {
        String[] fields = flatFile.split(",");
        return new Game(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), fields[3],
                fields[4], Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", level=" + level +
                ", shipType='" + shipType + '\'' +
                ", playerName='" + playerName + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                '}';
    }

    /**
     *
     * @return
     */


    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *
     * @return
     */
    public String getShipType() {
        return shipType;
    }

    /**
     *
     * @param shipType
     */
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    /**
     *
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @return
     */
    public int getAttack() {
        return attack;
    }

    /**
     *
     * @param attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }
}
