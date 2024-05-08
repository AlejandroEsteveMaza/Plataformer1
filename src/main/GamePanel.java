package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private Color color;
    private Random random;
    private MouseInputs mouseInputs;
    private ArrayList<Rect> rects = new ArrayList<>();

    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        for (Rect rect: rects){
            rect.updateRectangle();
            rect.draw(g);
        }


        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 50, 50);
    }

    private void updateRectangle() {
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();

        }

        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();
        }
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;

    }
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }
    public Color getRndColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
    public void spawnRect(int x, int y) {
        rects.add(new Rect(x, y));
    }
}