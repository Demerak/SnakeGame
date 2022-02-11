
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

public class SnakeGameModel {

    private int NUMBER_TILE_X;
    private int NUMBER_TILE_Y;
    private int NUMBER_TILE;
    private int snakeBodyTile = 6;

    private int[][] snakeBodyPos;

    private int applesEaten;
    private int[] applePosition = new int[2];

    private char direction = 'r';

    private Random random;

    public SnakeGameModel() {
        NUMBER_TILE_X = GraphicalView.NUMBER_TILE_X;
        NUMBER_TILE_Y = GraphicalView.NUMBER_TILE_Y;
        NUMBER_TILE = NUMBER_TILE_X * NUMBER_TILE_Y;

        snakeBodyPos = new int[NUMBER_TILE][2];

        random = new Random();
    }

    public int getSnakeBodyTile() {
        return snakeBodyTile;
    }

    public int getApplesEaten() {
        return applesEaten;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int[] newApple() {

        // apple row
        applePosition[0] = random.nextInt(NUMBER_TILE_Y);

        // apple column
        applePosition[1] = random.nextInt(NUMBER_TILE_X);

        System.out.println(applePosition[0] + " row");
        System.out.println(applePosition[1] + " col");

        return applePosition;

    }

    public int[][] move(char direction) {

        for (int i = snakeBodyTile; i > 0; i--) {
            snakeBodyPos[i][0] = snakeBodyPos[i - 1][0];
            snakeBodyPos[i][1] = snakeBodyPos[i - 1][1];
        }

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

    public boolean checkCollision() {

        // true if collision
        return snakeBodyPos[0][1] < 0 || snakeBodyPos[0][1] >= NUMBER_TILE_X
                || snakeBodyPos[0][0] < 0 || snakeBodyPos[0][0] >= NUMBER_TILE_Y;

    }

    public boolean checkApple() {
        // System.out.println(snakeBodyPos[0][0] + " " + applePosition[0]);
        boolean appleWasEaten = false;
        if (snakeBodyPos[0][0] == applePosition[0] && snakeBodyPos[0][1] == applePosition[1]) {
            applesEaten++;
            snakeBodyTile++;
            appleWasEaten = true;
        }
        return appleWasEaten;
    }

}