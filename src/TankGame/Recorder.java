package TankGame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int hitNum = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static Vector<Enemy> enemies = new Vector<>();
    private static Vector<Node> nodes = new Vector<>();
    private static String recordFile = "src\\myRecord.txt";

    public static int getHitNum() {
        return hitNum;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    public static void setHitNum(int hitNum) {
        Recorder.hitNum = hitNum;
    }

    public static void setEnemies(Vector<Enemy> enemies) {
        Recorder.enemies = enemies;
    }

    public static Vector<Node> getNodesAndNum() throws IOException {
        br = new BufferedReader(new FileReader(recordFile));
        hitNum = Integer.parseInt(br.readLine());
        String line;
        while((line = br.readLine()) != null) {
            String[] info = line.split(" ");
            nodes.add(new Node(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2]));
        }
        br.close();
        return nodes;
    }

    public static void addNum() {
        Recorder.hitNum++;
    }

    public static void saveRecord() throws IOException {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(hitNum + "\r\n");
            for(int i = 0; i < enemies.size(); i++) {
                Enemy e = enemies.get(i);
                if(e.isLive()) {
                    String record = e.getX() + " " + e.getY() + " " + e.getDirection();
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bw.close();
        }
    }
}
