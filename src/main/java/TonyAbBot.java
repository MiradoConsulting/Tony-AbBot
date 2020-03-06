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
    private Long colourTick = null;
    private Long moveTick = null;
    private boolean aimLeft = true;
    private boolean backOut = false;

    public void run() {
        turnGunRight(60);
        while (true) {
            System.out.println("Running!");
            move();
            runScan();
            colorify();
            decideBearing();
            decideAim();
        }
    }

    private Color getColour() {
        Random r = new Random();
        return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    private void colorify() {
        if (this.colourTick == null || super.getTime() > this.colourTick + 2) {
            setColors(getColour(), getColour(), getColour());
            this.colourTick = super.getTime();
        }
    }

    private void move() {
        if (this.moveTick == null || super.getTime() > this.moveTick + 2) {
            ahead(100 + 200 * Math.random());
            System.out.println("Moving");
            this.moveTick = super.getTime();
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

            if (backOut) {
                double dirDelta = 80.0 + 40.0 * Math.random();
                if (Math.random() > 0.5) {
                    turnRight(dirDelta);
                } else {
                    turnLeft(dirDelta);
                }
                backOut = false;
            } else {
                double dirDelta = 60.0 * Math.random();
                if (Math.random() > 0.5) {
                    turnRight(dirDelta);
                } else {
                    turnLeft(dirDelta);
                }
            }
            this.lastBearingTick = super.getTime();
        }
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        super.onHitWall(event);
        backOut = true;
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        final double power =
                Math.max(2, Math.random() * Rules.MAX_BULLET_POWER);
        fire(power);
    }

}
