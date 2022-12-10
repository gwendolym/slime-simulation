import java.awt.*;
import java.io.IOException;
import java.lang.Math;
import java.io.IOException;
import javax.swing.CellEditor;
public class Constants {

    public static final Color AGENT_COLOR = new Color (0,0,255);
    public static Color BACKGROUND = new Color(0,0,0);

    // speed
    public static double MOVE_SPEED = 1;
    public static double TURN_SPEED = 0.25;

    // size
    public static int CELL_WIDTH = 200;
    public static int CELL_HEIGHT = 250;
    public static int RESOLUTION = 4;

    public static int NO_OF_AGENTS = 1000;

    public static int FPS = 50;

    public static double diffuseSpeed = 0.75;
    public static double evaporateSpeed = 0.006;

    // SENSOR CONSTANTS
    public static final double SENSOR_ANGLE_OFFSET = Math.toRadians(45); // in radians
    public static final double SENSOR_OFFSET_DST = 4;
    public static final int SENSOR_SIZE = 1;

    public static void changeVariable (String name, int newInt){
        if (name.equals("CELL_WIDTH")) {
            CELL_WIDTH = newInt;
        }
        if (name.equals("CELL_HEIGHT")) {
            CELL_HEIGHT = newInt;
        }
        if (name.equals("NO_OF_AGENTS")) {
            NO_OF_AGENTS = newInt;
        }
        if (name.equals("FPS")) {
            FPS = newInt;
        }
    }
    public static void changeVariable (String name, double newDouble){
        if (name.equals("MOVE_SPEED")) {
            MOVE_SPEED = newDouble;
        }
        if (name.equals("TURN_SPEED")) {
            TURN_SPEED = newDouble;
        }
        if (name.equals("Diffuse Speed")) {
            diffuseSpeed = newDouble;
        }
        if (name.equals("Evaporation Speed")) {
            evaporateSpeed = newDouble;
        }

    }

    public static double returnVariableDouble (String name) {
        if (name.equals("MOVE_SPEED")) {
            return MOVE_SPEED;
        }
        if (name.equals("TURN_SPEED")) {
            return TURN_SPEED;
        }
        if (name.equals("Diffuse Speed")) {
            return diffuseSpeed;
        }
        if (name.equals("Evaporation Speed")) {
            return evaporateSpeed;
        }
        return 0;

    }

    public static int returnVariableInt (String name) {
        if (name.equals("NO_OF_AGENTS")) {
            return NO_OF_AGENTS;
        }
        if (name.equals("FPS")) {
            return FPS;
        }

        return 0;

    }
}
