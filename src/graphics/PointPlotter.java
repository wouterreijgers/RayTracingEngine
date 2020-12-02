package graphics;

import mathematics.Direction;
import mathematics.Vector;
import objects.Light;
import objects.ObjectCol;
import objects.Ray;
import objects.texture.Color;
import util.Hitinfo;

import javax.swing.*;

public class PointPlotter {
	
    private final PointPanel pointPanel;
    private Light light;
    
    public PointPlotter(int width, int heigth) {
    	 pointPanel = new PointPanel(width, heigth);

         JFrame frame = new JFrame("Ray tracing");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.add(pointPanel);
         frame.pack();
         frame.setVisible(true);

    	
    }

    public void setLight(Light light) {
        this.light = light;
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

    public void drawPoint(int y, int x, Color color, ObjectCol obj) {
        float r = color.getR();
        float g = color.getG();
        float b = color.getB();

        Vector toViewer = obj.getNormal().multiply(-1).normalise();
        Direction normal = new Direction(obj.getTransform().multiply( obj.getNormal() ).normalise());

        Direction newDir = new Direction( obj.getHitinfo().getPoint(), light.getPosition());
        Ray ray = new Ray(newDir, obj.getHitinfo().getPoint());

        Hitinfo hitinfo = new Hitinfo();
        // Calculate hitpoint
        //hitinfo.addHit(t2, p2, new Direction(1, 0, 0));

        double product = normal.dotproduct(newDir);
        if ( product < 0 )
            normal = new Direction(normal.multiply( -1 ));

        double intensity = product / Math.abs(normal.getnorm() * newDir.getnorm());

        // Only light up if the hit point is facing the light
        if (intensity > 0 && intensity<1) {
            r = (float) (r * intensity * light.getIntensity().getR());
            g = (float) (g * intensity * light.getIntensity().getG());
            b = (float) (b * intensity * light.getIntensity().getB());
        } else {
            r = 0;
            g = 0;
            b = 0;
        }


        pointPanel.drawPoint(y, x, r, g, b);

    }
}




