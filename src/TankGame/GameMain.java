package TankGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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
        this.setSize(1300,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Recorder.saveRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
