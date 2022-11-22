package TankGame;

public class shot implements Runnable {
    private int x;
    private int y;
    private String direction = "UP";

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    boolean isLive = true;
    private int speed = 8;

    public shot(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direction) {
                case "UP" -> y -= speed;
                case "DOWN" -> y += speed;
                case "LEFT" -> x -= speed;
                case "RIGHT" -> x += speed;
            }
            System.out.println("x = " + x + " y = " + y);
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                isLive = false;
                break;
            }
        }
    }
}
