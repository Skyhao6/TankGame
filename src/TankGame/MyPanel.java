package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements Runnable, KeyListener {
    Hero hero = null;
    Vector<Enemy> enemies = new Vector<>();
    Vector<Explode> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    int enemiesSize = 3;

    public MyPanel() {
        hero = new Hero(400, 500);
        hero.setSpeed(3);
        for (int i = 0; i < enemiesSize; i++) {
            Enemy enemy = new Enemy(100 * (i + 1), 0);
            enemy.setEnemies(enemies);
            enemy.setDirection("DOWN");
            new Thread(enemy).start();
            shot st = new shot(enemy.getX() + 20, enemy.getY() + 60, enemy.getDirection());
            enemy.getShots().add(st);
            new Thread(st).start();
            enemies.add(enemy);
        }
        image1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\present\\ben.jpg");
        image2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\present\\2.jpg");
        image3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Desktop\\present\\3.jpg");
    }


    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("Times New Roman", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("Hit Tanks", 1020, 30);
        drawTank(1020, 60, g, "UP", 1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getHitNum()+"", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 0);
        }
        if (hero.getSt() != null && hero.getSt().isLive) {
            g.draw3DRect(hero.getSt().getX(), hero.getSt().getY(), 2, 2, false);
        }
        for (int i = 0; i < hero.shots.size(); i++) {
            shot st = hero.shots.get(i);
            if (st != null && st.isLive) {
                g.draw3DRect(st.getX(), st.getY(), 2, 2, false);
            } else {
                hero.shots.remove(st);
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            Explode explode = bombs.get(i);
            if (explode.life > 9) {
                g.drawImage(image1, explode.x, explode.y, 60, 60, this);
            } else if (explode.life > 6) {
                g.drawImage(image2, explode.x, explode.y, 60, 60, this);
            } else {
                g.drawImage(image3, explode.x, explode.y, 60, 60, this);

            }
            explode.lifeDown();
            if (explode.life == 0) {
                bombs.remove(explode);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy tank = enemies.get(i);
            if (tank.isLive()) {
                drawTank(tank.getX(), tank.getY(), g, tank.getDirection(), 1);
                for (int j = 0; j < tank.getShots().size(); j++) {
                    shot st = tank.getShots().get(j);
                    if (st.isLive) {
                        g.draw3DRect(st.getX(), st.getY(), 2, 2, false);
                    } else {
                        tank.getShots().remove(st);
                    }
                }
            } else {
                enemies.remove(tank);
            }
        }
    }

    /**
     * Draw a Tank on Panel
     *
     * @param x         x coordinate of Tank
     * @param y         y coordinate of Tank
     * @param g         Graphic g
     * @param direction Move direction
     * @param type      type of Tank(0:self and 1:enemies)
     */
    public void drawTank(int x, int y, Graphics g, String direction, int type) {
        switch (type) {
            case 0 -> g.setColor(Color.CYAN);
            case 1 -> g.setColor(Color.ORANGE);
        }

        switch (direction) {
            case "UP" -> { // UP
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y, x + 20, y + 30);
            }
            case "RIGHT" -> {
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
            }
            case "DOWN" -> {
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
            }
            case "LEFT" -> {
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
            }
        }
    }

    public void hitEnemy() {
        for (int i = 0; i < hero.shots.size(); i++) {
            shot st = hero.shots.get(i);
            if (st != null && st.isLive) {
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy enemy = enemies.get(j);
                    hitTank(st, enemy);
                }
            }
        }
    }

    public void hitTank(shot s, TankClass tank) {
        int shotX = s.getX();
        int shotY = s.getY();
        switch (tank.getDirection()) {
            case "UP":
            case "DOWN":
                if (shotX > tank.getX() && shotX < tank.getX() + 40
                        && shotY > tank.getY() && shotY < tank.getY() + 60) {
                    s.isLive = false;
                    tank.setLive(false);
                    enemies.remove(tank);
                    if(tank instanceof Enemy) Recorder.addNum();
                    Explode explode = new Explode(tank.getX(), tank.getY());
                    bombs.add(explode);
                }
                break;
            case "RIGHT":
            case "LEFT":
                if (shotX > tank.getX() && shotX < tank.getX() + 60
                        && shotY > tank.getY() && shotY < tank.getY() + 40) {
                    s.isLive = false;
                    tank.setLive(false);
                    enemies.remove(tank);
                    if(tank instanceof Enemy) Recorder.addNum();
                    Explode explode = new Explode(tank.getX(), tank.getY());
                    bombs.add(explode);
                }
                break;
        }
    }

    public void hitHero() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            for (int j = 0; j < enemy.getShots().size(); j++) {
                shot st = enemy.getShots().get(j);
                if (hero.isLive && st.isLive) {
                    hitTank(st, hero);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_DOWN -> {
                hero.setDirection("DOWN");
                if (hero.getY() + 60 < 750) {
                    hero.moveDown();
                }
            }
            case KeyEvent.VK_LEFT -> {
                hero.setDirection("LEFT");
                if (hero.getX() > 0) {
                    hero.moveLeft();
                }
            }
            case KeyEvent.VK_RIGHT -> {
                hero.setDirection("RIGHT");
                if (hero.getX() + 60 < 1000) {
                    hero.moveRight();
                }
            }
            case KeyEvent.VK_UP -> {
                hero.setDirection("UP");
                if (hero.getY() > 0) {
                    hero.moveUp();
                }
            }
            case KeyEvent.VK_SPACE -> {

                hero.fire();

            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitEnemy();
            hitHero();
            this.repaint();
        }
    }
}
