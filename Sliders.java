import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;;


public class Sliders extends JPanel{
    
    JFrame frame;
    JSlider slider;
    JLabel label1;
    JLabel label2;
    String newVariable;
    String newVariabletype;

    public Sliders (int min, int max, int initialValue, String variable, String variableType) {
        
        newVariable = variable;
        newVariabletype = variableType;
        setLayout(new FlowLayout());
        slider = new JSlider(JSlider.HORIZONTAL,min,max,initialValue);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setOpaque(true);

        label1 = new JLabel(newVariable);
        if (newVariabletype.equals("double")) {
            label2 = new JLabel("" + Constants.returnVariableDouble(newVariable));
        }
        if (newVariabletype.equals("int")) {
            label2 = new JLabel("" +Constants.returnVariableInt(newVariable));
        }
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setOpaque(true);
        add(label1);
        add(slider);
        add(label2);
        event e = new event();
        slider.addChangeListener(e);
    }
    
    public class event implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            
            if (newVariable.equals("NO_OF_AGENTS")) {
                Constants.changeVariable(newVariable, slider.getValue());
            }
            if (newVariable.equals("FPS")) {
                Constants.changeVariable(newVariable, slider.getValue());
            }
            if (newVariable.equals("TURN_SPEED")) {
                Constants.changeVariable(newVariable, (double) slider.getValue() / 20);
            }
            if (newVariable.equals("MOVE_SPEED")) {
                Constants.changeVariable(newVariable, (double) slider.getValue() / 20);
            }
            if (newVariable.equals("Diffuse Speed")) {
                Constants.changeVariable(newVariable, (double) slider.getValue() / 1000);
            }
            if (newVariable.equals("Evaporation Speed")) {
                Constants.changeVariable(newVariable, (double) slider.getValue() / 1000);
            }




            if (newVariabletype.equals("double")) {
                label2.setText("" + Constants.returnVariableDouble(newVariable));
            }
            if (newVariabletype.equals("int")) {
                label2.setText("" + Constants.returnVariableInt(newVariable));
            }
        }
    }
}
