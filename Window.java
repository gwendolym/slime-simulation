import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;

public class Window {
    
    public static void main(String[] args) {
        
        Sliders moveSpeedSlider = new Sliders(0, 40, (int) Constants.MOVE_SPEED / 10 ,"MOVE_SPEED","double");
        moveSpeedSlider.slider.setMajorTickSpacing(20);
        moveSpeedSlider.slider.setMinorTickSpacing(5);
        moveSpeedSlider.slider.setPaintTicks(true);
        moveSpeedSlider.slider.setPaintLabels(true);
        moveSpeedSlider.slider.setOpaque(true);


        Sliders turnSpeedSlider = new Sliders(0, 40, (int) Constants.TURN_SPEED / 10 ,"TURN_SPEED","double");
        turnSpeedSlider.slider.setMajorTickSpacing(20);
        turnSpeedSlider.slider.setMinorTickSpacing(5);
        turnSpeedSlider.slider.setPaintTicks(true);
        turnSpeedSlider.slider.setPaintLabels(true);
        turnSpeedSlider.slider.setOpaque(true);


        Sliders noOfAgentsSlider = new Sliders(0, 10000, Constants.NO_OF_AGENTS, "NO_OF_AGENTS","int");
        noOfAgentsSlider.slider.setMajorTickSpacing(5000);
        noOfAgentsSlider.slider.setMinorTickSpacing(500);
        noOfAgentsSlider.slider.setPaintTicks(true);
        noOfAgentsSlider.slider.setPaintLabels(false);
        noOfAgentsSlider.slider.setOpaque(true);


        Sliders fpsSlider = new Sliders(0,60, Constants.FPS, "FPS", "int");
        fpsSlider.slider.setMajorTickSpacing(10);
        fpsSlider.slider.setMinorTickSpacing(5);
        fpsSlider.slider.setPaintTicks(true);
        fpsSlider.slider.setPaintLabels(true);
        fpsSlider.slider.setOpaque(true);
        
        Sliders diffuseSlider = new Sliders(0, 1500,(int) (Constants.diffuseSpeed *1000), "Diffuse Speed", "double");
        diffuseSlider.slider.setMajorTickSpacing(500);
        diffuseSlider.slider.setMinorTickSpacing( 250);
        diffuseSlider.slider.setPaintTicks(true);
        diffuseSlider.slider.setPaintLabels(false);
        diffuseSlider.slider.setOpaque(true);

        Sliders evaporateSlider = new Sliders(0, 100, 0,"Evaporation Speed","double");
        evaporateSlider.slider.setMajorTickSpacing(10);
        evaporateSlider.slider.setMinorTickSpacing(5);
        evaporateSlider.slider.setPaintTicks(true);
        evaporateSlider.slider.setPaintLabels(false);
        evaporateSlider.slider.setOpaque(true);
        
        
        JFrame frame = new JFrame("Sliders Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6,1,10,10));

        frame.add(moveSpeedSlider);
        frame.add(turnSpeedSlider);
        //frame.add(noOfAgentsSlider);
        //frame.add(fpsSlider);
        frame.add(diffuseSlider);
        frame.add(evaporateSlider);


        frame.setSize(200,200);
        frame.pack();
        frame.setVisible(true);
        
        
        
        JFrame theWindow = new JFrame("Slimey Stuff again");
        Panel thePanel = new Panel();

        theWindow.setResizable(false);
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.add(thePanel);

        theWindow.pack();
        theWindow.setLocationRelativeTo(null);

        theWindow.setVisible(true);

        thePanel.playAnimation();
        
        
    }

}
