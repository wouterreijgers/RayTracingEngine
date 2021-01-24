package graphics;

import mathematics.Direction;
import mathematics.Matrix;
import mathematics.Point;
import mathematics.Vector;
import objects.Light;
import objects.ObjectCol;
import objects.Ray;
import objects.texture.Color;
import objects.texture.Texture;
import util.HitPoint;
import util.Hitinfo;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class PointPlotter {
	
    private final PointPanel pointPanel;
    private ObjectCol[] objects;
    private Light light_original;
    
    public PointPlotter(int width, int heigth, ObjectCol[] objects) {
    	 pointPanel = new PointPanel(width, heigth);

         JFrame frame = new JFrame("Ray tracing");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.add(pointPanel);
         frame.pack();
         frame.setVisible(true);
         this.objects = objects;
    	
    }

    public void setLight(Light light) {
        this.light_original = light;
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

    public double isClosestHit(Ray ray, double bias){

        Double lowestT = null;
        for(ObjectCol obj:objects){
            Hitinfo temp = obj.isHit_light(ray);
            if ((temp.closestT() != null && temp.closestT() >= bias) && (lowestT == null || temp.closestT()  <= lowestT)){
                lowestT =temp.closestT();
            }
        }

        return lowestT != null ? lowestT:1;

    }

    public void drawPoint(int y, int x, Texture texture, ObjectCol obj) {
        if(y == 500 && x == 350){
            forceUpdate();
            System.out.println("test");
        }
        pointPanel.drawPoint(y, x, Shade(texture, obj).getVector());

    }

    public Color Shade(Texture texture, ObjectCol obj){
        Hitinfo hitinfo = obj.getHitinfo();
        Light light = obj.getLight();
        // The global illumination or the ambient color
        Vector global_Illumination = light.getIntensity().getVector();

        // TODO: place inside the material
        double specularComponent = 64;
        Vector specular = new Vector(0.7, 0.7, 0.7, 0);

        if (hitinfo.getAmountOfHits() != 0) {// Based on p641


            // Vector to the viewer.
            Vector v = obj.ray.getDirection().multiply(-1).normalise();

            // Set the emissive color of the object
            Vector color_vector = texture.getColor().getVector();

            // Ambient -> Based on the global illumination.
            color_vector = color_vector.multiply(global_Illumination);

            // Find the normal
            Direction m = new Direction(obj.getNormal().normalise());
            assert obj.transform.multiply(obj.transform_original).equals(new Matrix());

            // Check if the normal is ok
            if(m.dotproduct(obj.ray.getDirection())>0) {
                m = new Direction(m.multiply(-1));
            }

            // A ray from the hitpoint to the light
            Direction s_direction =  new Direction(new Direction(new Point((light.getPosition())), new Point(obj.getHitinfo().getPoint())).multiply(1));
            Direction s_direction2 =  new Direction(new Direction(new Point(obj.transform_original.multiply(light.getPosition())), new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint()))));

            if(isClosestHit(new Ray(new Direction(s_direction2), new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint()))), 0.0001)>=1){
                //Find the lambert term
                s_direction = new Direction(s_direction.normalise());
                double mDots = s_direction.dotproduct(m);
                if (mDots>=0) {
                    Vector diffuseColor = obj.getTexture().getDiffuse().multiply(light.getIntensity().getVector()).multiply(mDots); //TODO: Make sure that the diffuse factor is inside the obj class
                    color_vector.add_color(diffuseColor);
                    //Half way vector and a part of the phong term
                    Vector h = v.sum(s_direction).normalise();
                    double mDotH = h.dotproduct(m);
                    if(mDotH>=0){
                        double phong = Math.pow(mDotH, obj.getTexture().getSpecularExponent());
                        Vector specColor = obj.getTexture().getSpecular().multiply(light.intensity.getVector());
                        color_vector.add_color(specColor.multiply(phong));
                    }
                }
            }
            if(obj.texture.getReflectionCoeff()>0.2){

            }
            return new Color(color_vector);
        } else { // This means there is no hit so we apply the background color #TODO add background color as a config
            Vector color_vector = new Vector(1.0, 1.0, 1.0, 0);
            color_vector = color_vector.multiply(global_Illumination);
            return new Color(color_vector);
        }
    }


}




