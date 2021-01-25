package util;

import graphics.PointPanel;
import graphics.PointPlotter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.media.MediaLocator;
import javax.swing.JFrame;

public class Image {
    public int id ;
    public Vector<String> imgLst = new Vector<>();
    public Image(){
        this.id = 0;
    }

    public void createImage(PointPanel plotter){
        Color[][] imageMatrix = plotter.getMatrix();
        try {
            BufferedImage image = new BufferedImage(imageMatrix.length, imageMatrix[0].length, BufferedImage.TYPE_INT_RGB);
            for(int i=0; i<imageMatrix.length; i++) {
                for(int j=0; j<imageMatrix[i].length; j++) {
                    Color a = imageMatrix[j][i];
                    image.setRGB(j,i,a.getRGB());
                }
            }
            File output = new File("images/image" + id + ".jpg");
            id +=1;
            assert image != null;
            ImageIO.write(image, "jpg", output);
            imgLst.add("images/image" + id + ".jpg");

        }

        catch(Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public void addImage(){
        
    }
    public void createVideo() throws MalformedURLException {
        String fileName = "video.mp4";
        JpegImagesToMovie imageToMovie = new JpegImagesToMovie();
        MediaLocator oml;
        if ((oml = imageToMovie.createMediaLocator(fileName)) == null) {
            System.err.println("Cannot build media locator from: " + fileName);
            System.exit(0);
        }
        int interval = 50;
        imageToMovie.doIt(900, 900, (1000 / interval), imgLst, oml);
    }
}
