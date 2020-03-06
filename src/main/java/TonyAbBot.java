package tonyabbot;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * TonyAbBot - a robot by (your name here)
 */
public class TonyAbBot extends Robot
{
 // Scan every three seconds
    private Long lastScanTick = null;
    private Long lastBearingTick = null;

    public void run() {
        while (true) {
            System.out.println("Running!");
            runPeriodicals();

            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    private void runPeriodicals() {
        if (this.lastScanTick == null || super.getTime() > this.lastScanTick + 3) {
            super.scan();
            System.out.println("Scanning");
            this.lastScanTick = super.getTime();
        }
    }

    private void decideBearing() {
        if (this.lastBearingTick == null || super.getTime() > this.lastBearingTick + 5) {

        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {

        final double power =
                Math.max(1, Math.random() * Rules.MAX_BULLET_POWER);

        fire(power);
    }
}
