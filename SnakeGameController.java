import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

/**
 * The GraphicalView class is the controller of the game
 * 
 * @author Demerak
 */
public class SnakeGameController implements ActionListener {

    // Model
    private SnakeGameModel model;

    // View
    private GraphicalView view;

    private static final int GAME_SPEED = 100;

    // direction 'r', or 'l' or 'u' or 'd'
    private char direction = 'r';

    private boolean running = false;

    private Timer timer;

    // position of apple [row, column]
    int[] applePosition;

    /**
     * constructor
     */
    public SnakeGameController() {
        running = true;

        model = new SnakeGameModel();
        view = new GraphicalView(model, this);

        view.addKeyListener(new MyKeyAdapter());

        timer = new Timer(GAME_SPEED, this);
        timer.start();

        // set up the first apple
        applePosition = model.newApple();
        view.setGameCell(Color.RED, applePosition[0], applePosition[1]);
    }

    /**
     * getter for the variable running
     * 
     * @return
     *         the value of running
     */
    public boolean getRunning() {
        return running;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // get the snake position and number of body parts
        int[][] snakeBodyPos = model.move(direction);
        int snakeBodyParts = model.getSnakeBodyParts();

        // check if a collision occurred with wall or body parts
        running = !model.checkCollision();

        if (running) {
            for (int i = 0; i < snakeBodyParts; i++) {
                if (i == 0) {
                    view.setGameCell(new Color(34, 139, 34), snakeBodyPos[i][0], snakeBodyPos[i][1]);
                } else {
                    view.setGameCell(Color.GREEN, snakeBodyPos[i][0], snakeBodyPos[i][1]);
                }
            }
            // set the tile with the color black where the tail previously was
            view.setGameCell(Color.BLACK, snakeBodyPos[snakeBodyParts][0], snakeBodyPos[snakeBodyParts][1]);
            if (model.checkApple()) {
                applePosition = model.newApple();
                // set the new apple position
                view.setGameCell(Color.RED, applePosition[0], applePosition[1]);
            }
        } else {
            timer.stop();
        }
        view.setTopPanel();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    if (direction != 'l') {
                        direction = 'r';
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (direction != 'r') {
                        direction = 'l';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'd') {
                        direction = 'u';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'u') {
                        direction = 'd';
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new SnakeGameController();
    }
}
