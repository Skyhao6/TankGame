package TankGame;

import java.util.Vector;

public class Enemy extends TankClass implements Runnable {
    private Vector<shot> shots = new Vector<>();
    private boolean isLive = true;
    String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Vector<shot> getShots() {
        return shots;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while (isLive) {
            if (shots.size() == 0) {
                shot st = null;
                switch (getDirection()) {
                    case "UP" -> st = new shot(getX() + 20, getY(), "UP");
                    case "RIGHT" -> st = new shot(getX() + 60, getY() + 20, "RIGHT");
                    case "LEFT" -> st = new shot(getX(), getY() + 20, "LEFT");
                    case "DOWN" -> st = new shot(getX() + 20, getY() + 60, "DOWN");
                }
                shots.add(st);
                new Thread(st).start();
            }
            int idx = (int) (Math.random() * 4);
            String dir = directions[idx];
            setDirection(dir);
            int cnt = (int) (Math.random() * (20) + 30);
            while (cnt-- > 0) {
                switch (getDirection()) {
                    case "UP":
                        if (getY() > 0) {
                            moveUp();
                        }
                        break;
                    case "DOWN":
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        break;
                    case "LEFT":
                        if (getX() > 0) {
                            moveLeft();
                        }
                        break;
                    case "RIGHT":
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
