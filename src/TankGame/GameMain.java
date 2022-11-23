package TankGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

public class GameMain extends JFrame {
    private MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        GameMain gameMain = new GameMain();
    }

    public GameMain() throws IOException {
        System.out.println("Start new game?");
        System.out.println("[y]yes, [r]retrieve");
        String key = scanner.next();
        mp = new MyPanel(key);
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
