import java.util.Random;

/**
 * The SnakeGameModel class is the model of the game
 * 
 * @author Demerak
 */
public class SnakeGameModel {

    private int NUMBER_OF_ROW;
    private int NUMBER_OF_COL;
    private int NUMBER_OF_TILE;

    // number of body parts
    private int snakeBodyParts = 6;

    // snake body position [[head row, head column], [row2, col2] , ...]
    private int[][] snakeBodyPos;

    // number of apples eaten
    private int applesEaten;

    // position of apple [row, column]
    private int[] applePosition = new int[2];

    private Random random;

    /**
     * constructor
     */
    public SnakeGameModel() {
        NUMBER_OF_ROW = GraphicalView.NUMBER_TILE_Y;
        NUMBER_OF_COL = GraphicalView.NUMBER_TILE_X;
        NUMBER_OF_TILE = NUMBER_OF_COL * NUMBER_OF_ROW;
        snakeBodyPos = new int[NUMBER_OF_TILE][2];
        random = new Random();
    }

    /**
     * getter for the variable snakeBodyParts
     * 
     * @return
     *         the value of snakeBodyParts
     */
    public int getSnakeBodyParts() {
        return snakeBodyParts;
    }

    /**
     * getter for the variable applesEaten
     * 
     * @return
     *         the value of applesEaten
     */
    public int getApplesEaten() {
        return applesEaten;
    }

    /**
     * returns the new apple position that is
     * randomly generated.
     * 
     * @return
     *         the position of the new apple
     */
    public int[] newApple() {
        // apple row
        applePosition[0] = random.nextInt(NUMBER_OF_ROW);
        // apple column
        applePosition[1] = random.nextInt(NUMBER_OF_COL);

        return applePosition;

    }

    /**
     * returns the 2d array representing all of the
     * snake body positions.
     * 
     * @return
     *         the 2d array that represent the snake
     *         body position
     */
    public int[][] move(char direction) {

        // move all snake body positions by one index
        for (int i = snakeBodyParts; i > 0; i--) {
            snakeBodyPos[i][0] = snakeBodyPos[i - 1][0];
            snakeBodyPos[i][1] = snakeBodyPos[i - 1][1];
        }

        // set the new position of the snake head
        switch (direction) {
            case 'r':
                snakeBodyPos[0][1] = snakeBodyPos[0][1] + 1;
                break;
            case 'l':
                snakeBodyPos[0][1] = snakeBodyPos[0][1] - 1;
                break;
            case 'u':
                snakeBodyPos[0][0] = snakeBodyPos[0][0] - 1;
                break;
            case 'd':
                snakeBodyPos[0][0] = snakeBodyPos[0][0] + 1;
                break;
        }

        return snakeBodyPos;
    }

    /**
     * returns the state of the game. In other words
     * whether or not the snake has hit a wall of itself.
     * 
     * @return
     *         a boolean that is true if the game is over
     */
    public boolean checkCollision() {

        // true if a collision occurred with the game wall
        boolean hitGameWall = snakeBodyPos[0][1] < 0 || snakeBodyPos[0][1] >= NUMBER_OF_COL
                || snakeBodyPos[0][0] < 0 || snakeBodyPos[0][0] >= NUMBER_OF_ROW;

        // rue if a collision occurred with snake body
        boolean hitSnakeBody = false;
        for (int i = 1; i <= snakeBodyParts; i++) {
            if (snakeBodyPos[0][0] == snakeBodyPos[i][0] && snakeBodyPos[0][1] == snakeBodyPos[i][1]) {
                hitSnakeBody = true;
            }
        }

        return hitGameWall || hitSnakeBody;

    }

    /**
     * returns the whether or not an apple was eaten.
     * 
     * @return
     *         a boolean that is true a apple was eaten
     */
    public boolean checkApple() {
        boolean appleWasEaten = false;
        if (snakeBodyPos[0][0] == applePosition[0] && snakeBodyPos[0][1] == applePosition[1]) {
            applesEaten++;
            snakeBodyParts++;
            appleWasEaten = true;
        }

        return appleWasEaten;
    }
}