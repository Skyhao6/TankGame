package TankGame;

import javax.swing.*;

public class GameMain extends JFrame {
    private MyPanel mp = null;
    public static void main(String[] args) {
        GameMain gameMain = new GameMain();
    }

    public GameMain() {
        mp = new MyPanel();
        Thread t = new Thread(mp);
        t.start();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
