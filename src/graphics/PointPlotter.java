package graphics;

import objects.texture.Color;

import javax.swing.*;

public class PointPlotter {
	
    private final PointPanel pointPanel;
    
    public PointPlotter(int width, int heigth) {
    	 pointPanel = new PointPanel(width, heigth);

         JFrame frame = new JFrame("Ray tracing");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.add(pointPanel);
         frame.pack();
         frame.setVisible(true);
    	
    }
    
    /**
     * Draws a white dot at the specified location
     * @param y y-coördinate of the dot (heigth)
     * @param x x-coördinate of the dot (width)
     */
    public void drawPoint(int y, int x)
    {
        pointPanel.drawPoint(y, x, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Draws a coloured dot at the specified location
     * @param y y-coördinate of the dot (heigth)
     * @param x x-coördinate of the dot (width)
     * @param r red color component [0.0, 1.0]
     * @param g green color component [0.0, 1.0]
     * @param b blue color component [0.0, 1.0]
     */
    public void drawPoint(int y, int x, float r, float g, float b)
    {
        pointPanel.drawPoint(y, x, r, g, b);
    }

    public void forceUpdate()
    {
        pointPanel.repaint();
    }

    public void drawPoint(int y, int x, Color color) {
        pointPanel.drawPoint(y, x, color.getR(), color.getG(), color.getB());
    }
}




