import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Agent {
    private Point2D positionDouble;
    private Point positionInt;
    private double angle;
    private double[][] trailmap = new double[Constants.CELL_WIDTH][Constants.CELL_HEIGHT];


    public Agent () {

        
        this.positionDouble = new Point2D.Double(Constants.CELL_WIDTH / 2 + (Math.random()-0.5) * Constants.CELL_WIDTH / 2, Constants.CELL_HEIGHT / 2 + (Math.random() - 0.5) * Constants.CELL_HEIGHT / 2);
        this.positionInt = new Point((int) positionDouble.getX(),(int) positionDouble.getY());
        this.angle = Math.toRadians(Math.random() * 360);
        trailmap[positionInt.x][positionInt.y] = 1;
        
    }

    public Point sensorForward;
    public Point sensorLeft;
    public Point sensorRight;
    public ArrayList<Point> sensorpointsForward = new ArrayList<>();
    public ArrayList<Point> sensorpointsRight = new ArrayList<>();
    public ArrayList<Point> sensorpointsLeft = new ArrayList<>();
    public double senseForward;
    public double senseLeft;
    public double senseRight;
    
    public void update () {


        // Steering

        
        senseForward = sense(0);
        senseLeft = sense(Constants.SENSOR_ANGLE_OFFSET);
        senseRight = sense(-Constants.SENSOR_ANGLE_OFFSET);

        double randomSteerStrength = Math.random();
        if (senseForward > senseLeft && senseForward > senseRight) {
            this.angle += 0;
        }
        else if (senseRight > senseLeft) {
            this.angle -= randomSteerStrength * 1.5 * Constants.TURN_SPEED * Panel.deltaTime;
        }
        else if (senseLeft > senseRight) {
            this.angle += randomSteerStrength * 1.5 * Constants.TURN_SPEED * Panel.deltaTime;
        }
        else {
            this.angle += (randomSteerStrength - 0.5) * Constants.TURN_SPEED * Panel.deltaTime;
        }

        
        Point2D direction = new Point2D.Double(Math.cos(this.angle), Math.sin(this.angle));
        Point2D newpos = new Point2D.Double((positionDouble.getX() + direction.getX() * Constants.MOVE_SPEED * Panel.deltaTime),(positionDouble.getY() + direction.getY() * Constants.MOVE_SPEED * Panel.deltaTime));
        
        
        // Wall Checks
        if (newpos.getX() < 0 || newpos.getY() < 0) {
            newpos.setLocation(Math.max(0, newpos.getX()),Math.max(0, newpos.getY()));

            this.angle = Math.random() * 2 * Math.PI;
        }

        if (newpos.getX() >= Constants.CELL_WIDTH || newpos.getY() >= Constants.CELL_HEIGHT) {
            newpos.setLocation(Math.min(newpos.getX(), Constants.CELL_WIDTH - 1), Math.min(newpos.getY(), Constants.CELL_HEIGHT - 1));
            this.angle = Math.random() * 2 * Math.PI;
        }


        // Add new position and trail
        this.positionDouble = newpos;
        this.positionInt.x = (int) this.positionDouble.getX();
        this.positionInt.y = (int) this.positionDouble.getY();
        Panel.trailmap[positionInt.x][positionInt.y] = 1;

    }


    public void draw (Graphics2D g2) {
        g2.setColor(Constants.AGENT_COLOR);
        
    }
    /*
    private Point findsensorCenter (double sensorAngleInput) {
        
        double sensorAngle = this.angle + sensorAngleInput;
        Point2D sensorDirection = new Point2D.Double(Math.cos(sensorAngle), Math.sin(sensorAngle));

        Point2D sensorCenterDouble = new Point2D.Double(this.positionDouble.getX() + sensorDirection.getX() * Constants.SENSOR_OFFSET_DST, this.positionDouble.getY() + sensorDirection.getY() * Constants.SENSOR_OFFSET_DST);
        Point sensorCenterInt = new Point((int) sensorCenterDouble.getX(), (int) sensorCenterDouble.getY());
        double sum = 0;
        return sensorCenterInt;
    }
    */

    public double getAngle () {
        return this.angle;
    }
    private double sense (double sensorAngleInput) {
        
        double sensorAngle = this.angle + sensorAngleInput;
        Point2D sensorDirection = new Point2D.Double(Math.cos(sensorAngle), Math.sin(sensorAngle));

        Point2D sensorCenterDouble = new Point2D.Double(this.positionDouble.getX() + sensorDirection.getX() * Constants.SENSOR_OFFSET_DST, this.positionDouble.getY() + sensorDirection.getY() * Constants.SENSOR_OFFSET_DST);
        Point sensorCenterInt = new Point((int) sensorCenterDouble.getX(), (int) sensorCenterDouble.getY());
        double sum = 0;
        
        for (int offsetX = -Constants.SENSOR_SIZE; offsetX <= Constants.SENSOR_SIZE; offsetX++) {
            for (int offsetY = -Constants.SENSOR_SIZE; offsetY <= Constants.SENSOR_SIZE; offsetY++) {
                int Xpos = sensorCenterInt.x + offsetX;
                int Ypos = sensorCenterInt.y + offsetY;
            
                if (Xpos >= 0 && Xpos < Constants.CELL_WIDTH && Ypos >= 0 && Ypos < Constants.CELL_HEIGHT) {
                    sum += Panel.trailmap[Xpos][Ypos];
                }
            }
        }

        return sum;
    }
    /*
    private ArrayList<Point> sensorPoints (double sensorAngleInput) {
        
        double sensorAngle = this.angle + sensorAngleInput;
        Point2D sensorDirection = new Point2D.Double(Math.cos(sensorAngle), Math.sin(sensorAngle));

        Point2D sensorCenterDouble = new Point2D.Double(this.positionDouble.getX() + sensorDirection.getX() * Constants.SENSOR_OFFSET_DST, this.positionDouble.getY() + sensorDirection.getY() * Constants.SENSOR_OFFSET_DST);
        Point sensorCenterInt = new Point((int) sensorCenterDouble.getX(), (int) sensorCenterDouble.getY());
        double sum = 0;
        ArrayList<Point> pointslist = new ArrayList<>();
        int i = 0;
        
        for (int offsetX = -Constants.SENSOR_SIZE; offsetX <= Constants.SENSOR_SIZE; offsetX++) {
            for (int offsetY = -Constants.SENSOR_SIZE; offsetY <= Constants.SENSOR_SIZE; offsetY++) {
                int Xpos = sensorCenterInt.x + offsetX;
                int Ypos = sensorCenterInt.y + offsetY;
            
                if (Xpos >= 0 && Xpos < Constants.CELL_WIDTH && Ypos >= 0 && Ypos < Constants.CELL_HEIGHT) {
                    sum +=Panel.trailmap[Xpos][Ypos];

                    pointslist.add(new Point((int) Xpos, (int) Ypos));
                    i++;

                }
            }
        }

        return pointslist;
    }
    */
}
