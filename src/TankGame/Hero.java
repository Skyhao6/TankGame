package TankGame;

import java.util.Vector;

public class Hero extends TankClass {
    private shot st = null;
    Vector<shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    public shot getSt() {
        return st;
    }

    public void fire() {
        if(shots.size() == 5 || !isLive) return;
        switch (getDirection()) {
            case "UP" -> st = new shot(getX() + 20, getY(), "UP");
            case "RIGHT" -> st = new shot(getX() + 60, getY() + 20, "RIGHT");
            case "LEFT" -> st = new shot(getX(), getY() + 20, "LEFT");
            case "DOWN" -> st = new shot(getX() + 20, getY() + 60, "DOWN");
        }
        shots.add(st);
        new Thread(st).start();
    }
}
