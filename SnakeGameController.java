import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.Timer;

public class SnakeGameController implements ActionListener {

    // Model
    private SnakeGameModel model;

    // View
    private GraphicalView view;

    private static final int GAME_SPEED = 100;

    private char direction = 'r';
    private boolean running = false;

    private Timer timer;

    int[] applePosition;

    public SnakeGameController() {
        running = true;

        model = new SnakeGameModel();
        view = new GraphicalView(model, this);

        view.addKeyListener(new MyKeyAdapter());

        timer = new Timer(GAME_SPEED, this);
        timer.start();

        applePosition = model.newApple();

        view.setGameCell(Color.RED, applePosition[0], applePosition[1]);

    }

    public boolean getRunning() {
        return running;
    }

    public void update() {

        int[][] snakeBodyPos = model.move(direction);
        int snakeBodyTile = model.getSnakeBodyTile();

        running = !model.checkCollision();

        if (running) {

            for (int i = 0; i < snakeBodyTile; i++) {
                if (i == 0) {
                    view.setGameCell(new Color(34, 139, 34), snakeBodyPos[i][0], snakeBodyPos[i][1]);
                } else {
                    view.setGameCell(Color.GREEN, snakeBodyPos[i][0], snakeBodyPos[i][1]);
                }
            }
            view.setGameCell(Color.BLACK, snakeBodyPos[snakeBodyTile][0], snakeBodyPos[snakeBodyTile][1]);
        } else {
            timer.stop();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            update();
            if (model.checkApple()) {
                view.setGameCell(Color.BLACK, applePosition[0], applePosition[1]);
                applePosition = model.newApple();
                view.setGameCell(Color.RED, applePosition[0], applePosition[1]);
            }
            view.setTopPanel();
        }

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

        SnakeGameController controller = new SnakeGameController();

    }

}
