package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private Color color;
    private final Random random;

    public GamePanel() {
        random = new Random();
        MouseInputs mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        g.setColor(color);
        g.fillRect((int) xDelta, (int)yDelta, 50, 50);
    }

    private Color getRndColor() {
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }

    public void updateGameLogic() {
        updateRectangle();
    }

    private void updateRectangle() {
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color= getRndColor();

        }

        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color= getRndColor();
        }
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;

    }
}