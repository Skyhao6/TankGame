package TankGame;

public class Explode {
    int x, y;
    int life = 9;
    boolean isLive = true;
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown() {
        if(life >0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
