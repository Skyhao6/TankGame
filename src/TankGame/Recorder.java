package TankGame;

import java.io.*;

public class Recorder {
    private static int hitNum = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static String recordFile = "d:\\myRecord.txt";

    public static int getHitNum() {
        return hitNum;
    }

    public static void setHitNum(int hitNum) {
        Recorder.hitNum = hitNum;
    }

    public static void addNum() {
        Recorder.hitNum++;
    }

    public static void saveRecord() throws IOException {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(hitNum + "\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bw.close();
        }
    }
}
