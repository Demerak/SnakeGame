import java.awt.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GraphicalView extends JFrame {

    private SnakeGameModel model;
    private SnakeGameController controller;

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int TILE_SIZE = 25;
    public static final int NUMBER_TILE_X = SCREEN_WIDTH / TILE_SIZE;
    public static final int NUMBER_TILE_Y = SCREEN_HEIGHT / TILE_SIZE;

    private JLabel[][] cells = new JLabel[NUMBER_TILE_X][NUMBER_TILE_Y];
    private JLabel scoreResult;

    public GraphicalView(SnakeGameModel model, SnakeGameController controller) {
        super("Snake Game");

        this.model = model;
        this.controller = controller;

        // Top Panel
        JPanel topPanel = new JPanel();
        scoreResult = new JLabel("Score: " + model.getApplesEaten());
        scoreResult.setFont(new Font("Serif", Font.BOLD, 25));
        scoreResult.setBackground(Color.WHITE);
        topPanel.add(scoreResult, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        // The actual Snake Game Board
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.BLACK);
        boardPanel.setLayout(new GridLayout(SCREEN_WIDTH / TILE_SIZE, SCREEN_HEIGHT / TILE_SIZE));
        for (int row = 0; row < NUMBER_TILE_X; row++) {
            for (int col = 0; col < NUMBER_TILE_Y; col++) {
                JLabel cell = new JLabel();
                cells[row][col] = cell;
                cell.setBackground(Color.BLACK);
                cell.setOpaque(true);
                boardPanel.add(cell);
            }
        }
        this.add(boardPanel, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setGameCell(Color c, int row, int col) {
        cells[row][col].setBackground(c);
    }

    public void setTopPanel() {
        if (controller.getRunning()) {
            scoreResult.setText("Score: " + model.getApplesEaten());
        } else {
            scoreResult.setText("Game Over");
        }

    }

}
