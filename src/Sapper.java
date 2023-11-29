import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.Objects;

import mainObjects.Boxes;
import mainObjects.Coordinates;
import mainObjects.Game;
import mainObjects.Ranges;

public class Sapper extends JFrame{
    public static void main(String[] args) {
        new Sapper();
    }

    private Game game;
    private JPanel panel;

    private JLabel statusLabel;
    private final int COLS = 9;
    private final int BOMBS = 10;
    private final int ROWS = 9;
    private final int IMAGE_SIZE = 50;

    private Sapper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setupImages();
        CreateStatusLabel();
        CreatePanel();
        CreateFrame();
    }

    private void CreatePanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinates coordinate : Ranges.getAllCoordinates()) {
                    g.drawImage((Image) game.getBox(coordinate).image, coordinate.x * IMAGE_SIZE, coordinate.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinates coordinates = new Coordinates(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coordinates);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coordinates);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }
                statusLabel.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        this.add(panel);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED -> {
                return "Be careful :)";
            }
            case BOMBED -> {
                return "You've lost :(";
            }
            case WINNER -> {
                return "You've won!";
            }
            default -> {
                return "";
            }
        }
    }

    private void CreateStatusLabel() {
        statusLabel = new JLabel("Game Started");
        add(statusLabel, BorderLayout.SOUTH);

    }

    private void CreateFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Сапёр");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }

    private void setupImages() {
        for (Boxes box : Boxes.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name) {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        return icon.getImage();
    }
}
