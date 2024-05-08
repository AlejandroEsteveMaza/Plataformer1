package main;

import java.awt.*;
import java.util.Random;

public class Rect {

    private int x,y,w,h;
    private int xDir = 1, yDir = 1;

    private Color color;
    private Random random;


    public Rect(int x, int y) {
        random = new Random();
        this.x = x;
        this.y = y;
        w = random.nextInt(50);
        h = w;

    }

    public void updateRectangle() {
        y += yDir;
        if (y > 400 || y < 0) {
            yDir *= -1;
            color = getRndColor();
        }

        x += xDir;
        if (x > 400 || x < 0) {
            xDir *= -1;
            color = getRndColor();
        }
    }

    public Color getRndColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }
}
