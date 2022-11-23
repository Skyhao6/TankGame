package TankGame;

import java.util.Vector;

public class Enemy extends TankClass implements Runnable {
    private Vector<shot> shots = new Vector<>();
    Vector<Enemy> enemies = new Vector<>();
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

    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }

    public boolean isTouchedEnemy() {
        String dir = this.getDirection();
        switch (dir) {
            case "UP":
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy e = enemies.get(i);
                    if (this == e) continue;
                    if (e.getDirection().equals("UP") || e.getDirection().equals("DOWN")) {
                        if (this.getX() >= e.getX()
                                && this.getX() <= e.getX() + 40
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 60) {
                            return true;
                        }
                        if (this.getX() + 40 >= e.getX()
                                && this.getX() + 40 <= e.getX() + 40
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 60) {
                            return true;
                        }
                    }
                    if (e.getDirection().equals("LEFT") || e.getDirection().equals("RIGHT")) {
                        if (this.getX() >= e.getX()
                                && this.getX() <= this.getX() + 60
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 40 >= e.getX()
                                && this.getX() + 40 <= this.getX() + 60
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case "RIGHT":
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy e = enemies.get(i);
                    if (this == e) continue;
                    if (e.getDirection().equals("UP") || e.getDirection().equals("DOWN")) {
                        if (this.getX() + 60 >= e.getX()
                                && this.getX() + 60 <= e.getX() + 40
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 60) {
                            return true;
                        }
                        if (this.getX() + 60 >= e.getX()
                                && this.getX() + 60 <= e.getX() + 40
                                && this.getY() + 40 >= e.getY()
                                && this.getY() + 40 <= e.getY() + 60) {
                            return true;
                        }
                    }
                    if (e.getDirection().equals("LEFT") || e.getDirection().equals("RIGHT")) {
                        if (this.getX() + 60 >= e.getX()
                                && this.getX() + 60 <= this.getX()
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 60 >= e.getX()
                                && this.getX() + 60 <= this.getX()
                                && this.getY() + 40 >= e.getY()
                                && this.getY() + 40 <= e.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case "DOWN":
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy e = enemies.get(i);
                    if (this == e) continue;
                    if (e.getDirection().equals("UP") || e.getDirection().equals("DOWN")) {
                        if (this.getX() >= e.getX()
                                && this.getX() <= e.getX() + 40
                                && this.getY() + 60 >= e.getY()
                                && this.getY() + 60 <= e.getY() + 60) {
                            return true;
                        }
                        if (this.getX() + 40 >= e.getX()
                                && this.getX() + 40 <= e.getX() + 40
                                && this.getY() + 60 >= e.getY()
                                && this.getY() + 60 <= e.getY() + 60) {
                            return true;
                        }
                    }
                    if (e.getDirection().equals("LEFT") || e.getDirection().equals("RIGHT")) {
                        if (this.getX() >= e.getX()
                                && this.getX() <= this.getX() + 60
                                && this.getY() + 60 >= e.getY()
                                && this.getY() + 60 <= e.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 40 >= e.getX()
                                && this.getX() + 40 <= this.getX() + 60
                                && this.getY() + 60 >= e.getY()
                                && this.getY() + 60 <= e.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case "LEFT":
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy e = enemies.get(i);
                    if (this == e) continue;
                    if (e.getDirection().equals("UP") || e.getDirection().equals("DOWN")) {
                        if (this.getX() >= e.getX()
                                && this.getX() <= e.getX() + 40
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 60) {
                            return true;
                        }
                        if (this.getX() >= e.getX()
                                && this.getX() <= e.getX() + 40
                                && this.getY() + 40 >= e.getY()
                                && this.getY() + 40 <= e.getY() + 60) {
                            return true;
                        }
                    }
                    if (e.getDirection().equals("LEFT") || e.getDirection().equals("RIGHT")) {
                        if (this.getX() >= e.getX()
                                && this.getX() <= this.getX() + 60
                                && this.getY() >= e.getY()
                                && this.getY() <= e.getY() + 40) {
                            return true;
                        }
                        if (this.getX() >= e.getX()
                                && this.getX() <= this.getX() + 60
                                && this.getY() + 40 >= e.getY()
                                && this.getY() + 40 <= e.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
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
                        if (getY() > 0 && !isTouchedEnemy()) {
                            moveUp();
                        }
                        break;
                    case "DOWN":
                        if (getY() + 60 < 750 && !isTouchedEnemy()) {
                            moveDown();
                        }
                        break;
                    case "LEFT":
                        if (getX() > 0 && !isTouchedEnemy()) {
                            moveLeft();
                        }
                        break;
                    case "RIGHT":
                        if (getX() + 60 < 1000 && !isTouchedEnemy()) {
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
