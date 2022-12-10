import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Panel extends JPanel {
    
    private BufferedImage img;
    private Graphics2D g2;
    private boolean running;
    Agent[] agents = new Agent[Constants.NO_OF_AGENTS];

    public static double[][] trailmap = new double[Constants.CELL_WIDTH][Constants.CELL_HEIGHT];

    private int windowWidth = Constants.CELL_WIDTH * Constants.RESOLUTION;
    private int windowHeight = Constants.CELL_HEIGHT * Constants.RESOLUTION;


    private Dimension size = new Dimension(windowWidth, windowHeight);


    public Panel () {
        setPreferredSize(size);
    
        img = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) img.getGraphics();
        running = true;

        createAgents (agents, Constants.NO_OF_AGENTS);
    }

    // Game Loop
    public static double deltaTime;

    public void playAnimation () {
        
        double drawInterval = 1000000000 / Constants.FPS;

        deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        boolean frame = true;

        while (running) {

            currentTime = System.nanoTime();
            
            deltaTime += (currentTime - lastTime) / drawInterval;
            
            lastTime = currentTime;

            if (deltaTime >= 1) {
                
                update();
                if (frame) {
                draw();

                repaint();
                frame = false;
                }
                else {
                    frame = true;
                }
                deltaTime--;
            }

        }
    }


    // update based on time
    public void update() {
        

        for (int i = 0; i < agents.length; i++) {
            agents[i].update();
        }

    }

    // create image in memory
    public void draw() {
    
        g2.setColor(Constants.BACKGROUND);
        g2.fillRect(0, 0, windowWidth, windowHeight);

        for (int i = 0; i < trailmap.length; i++) {
            for (int j = 0; j < trailmap[0].length; j++) {
                
                if (trailmap[i][j] > 0 && trailmap[i][j] <= 1) {

                    float colorValue = (float) trailmap[i][j];

                    g2.setColor(new Color(0, 0, colorValue));
                    g2.fillRect((int) i * Constants.RESOLUTION, j * Constants.RESOLUTION, Constants.RESOLUTION, Constants.RESOLUTION);

                }

            }
        }
        processTrailMap();


        for (int i = 0; i < agents.length; i++) {
            agents[i].draw(g2);
       
        }

    }


    // hang the image on the window
    public void paintComponent (Graphics g) {
        g.drawImage(img, 0, 0, null);
    }


    // create agents 
    public void createAgents (Agent[] agents, int noOfAgents) {

        for (int i = 0; i < noOfAgents; i++) {
            agents[i] = new Agent();
        }
    }

    public void processTrailMap () {

        for (int i = 0; i < trailmap.length; i++) {
            for (int j = 0; j < trailmap[0].length; j++) {
                double originalValue = trailmap[i][j];
                
                double sum = 0;
                for (int offsetX = -1; offsetX <= 1; offsetX++) {
                    for (int offsetY = -1; offsetY <= 1; offsetY++) {
                        int sampleX = i + offsetX;
                        int sampleY = j + offsetY;

                        if (sampleX >= 0 && sampleX < Constants.CELL_WIDTH && sampleY >= 0 && sampleY < Constants.CELL_HEIGHT) {
                            sum += trailmap[sampleX][sampleY];
                        }
                    }
                }
                double blurResult = sum / 9; 

                double diffusedValue = lerp(originalValue, blurResult, Constants.diffuseSpeed * deltaTime);
                double diffuseAndEvaporatedValue = Math.max(0, diffusedValue - Constants.evaporateSpeed * deltaTime);
                trailmap[i][j] = Math.min(diffuseAndEvaporatedValue, 1);
            }
        }



    }
    private double lerp (double a, double b, double f) {
        return a * (1.0 - f) + (b * f);
    }

}
