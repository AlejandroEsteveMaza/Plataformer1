package main;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        stratGameLoop();
    }

    private void stratGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    private void updateGameLogic() {
        gamePanel.updateGameLogic();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long currentTime;
        long previousTime = System.nanoTime();

        double deltaU = 0;
        double deltaF = 0;

        int info_frames = 0;
        int info_updates = 0;
        long info_lastCheck = System.currentTimeMillis();

        while (true) {
            currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // Updates
            if (deltaU >= 1) {
                updateGameLogic();
                deltaU--;
                info_updates++;
            }

            // Render
            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
                info_frames++;
            }

            //FPS contador
            if (System.currentTimeMillis() - info_lastCheck >= 1000) {
                info_lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + info_frames + " | UPS: " + info_updates);
                info_frames = 0;
                info_updates = 0;
            }


        }

    }
}