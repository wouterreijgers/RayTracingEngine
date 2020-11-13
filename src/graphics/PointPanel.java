package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PointPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BufferedImage frame;

    public PointPanel(int width, int heigth) {
        super(true);
        frame = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
        this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
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

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(frame, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}