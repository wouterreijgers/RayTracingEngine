package graphics;

import mathematics.Matrix;
import mathematics.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PointPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage frame;
	public Color[][] matrix;
	public int width, heigth;

    public PointPanel(int width, int heigth) {
        super(true);
        frame = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
        this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        this.matrix = new Color[width][heigth];
        this.width = width;
        this.heigth = heigth;
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
        frame.setRGB(x, y, new Color(r, g, b).getRGB());
    }

    public void drawPoint(int y, int x, Vector color)
    {
        if(color.getX()>1.0f){
            System.out.println("ERROR, R out of range, max should be 1.0f but is " + color.getX());
            color.setX(1.0f);
        }
        if(color.getY()>1.0f){
            System.out.println("ERROR, G out of range, max should be 1.0f but is " + color.getY());
            color.setY(1.0f);
        }
        if(color.getZ()>1.0f){
            System.out.println("ERROR, B out of range, max should be 1.0f but is " + color.getZ());
            color.setZ(1.0f);
        }
        matrix[x][y] = new Color((float)color.getX(), (float)color.getY(), (float)color.getZ());
        //frame.setRGB(x, y, new Color((float)color.getX(), (float)color.getY(), (float)color.getZ()).getRGB());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(frame, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public Color[][] getMatrix() {
        return matrix;
    }

    public void clear() {
        this.frame = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
        this.matrix = new Color[width][heigth] ;
    }
}