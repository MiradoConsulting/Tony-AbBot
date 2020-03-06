package tonyabbot;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * TonyAbBot - a robot by (your name here)
 */
public class TonyAbBot extends Robot
{    // Scan every three seconds
    private Long lastScanTick = null;
    private Long lastBearingTick = null;
    private Long decideAimTick = null;
    private boolean aimLeft = true;

    private boolean backOut = false;

    public void run() {
        turnGunRight(60);
        while (true) {
            System.out.println("Running!");
            runScan();
            decideBearing();
            decideAim();
            ahead(200);
            //back(100);
        }
    }

    private void runScan() {
        if (this.lastScanTick == null || super.getTime() > this.lastScanTick + 2) {
            super.scan();
            System.out.println("Scanning");
            this.lastScanTick = super.getTime();
        }
    }

    private void decideAim() {

        if (this.decideAimTick == null || super.getTime() > this.decideAimTick + 5) {
            System.out.println("deciding new aim");

            if (aimLeft) {
                turnGunLeft(120);
            } else {
                turnGunRight(120);

            }
            aimLeft = !aimLeft;
            this.decideAimTick = super.getTime();
        }
    }

    private void decideBearing() {

        if (this.lastBearingTick == null || super.getTime() > this.lastBearingTick + 3) {
            System.out.println("Deciding new bearing");
            double dirDelta = 45.0 * Math.random();
            if (Math.random() > 0.5) {
                turnRight(dirDelta);
            } else {
                turnLeft(dirDelta);
            }
            this.lastBearingTick = super.getTime();
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        final double power =
                Math.max(1, Math.random() * Rules.MAX_BULLET_POWER);
        fire(power);
    }
}
