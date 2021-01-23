package graphics;

import mathematics.Direction;
import mathematics.Point;
import mathematics.Vector;
import objects.Light;
import objects.ObjectCol;
import objects.Ray;
import objects.texture.Color;
import objects.texture.Texture;
import util.Hitinfo;

import javax.swing.*;

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
        for(ObjectCol obj:objects){
            obj.isHit_light(ray);
        }
        Double lowestT = null;
        for(ObjectCol obj:objects){
            if ((obj.getHitinfo().closestT() != null && obj.getHitinfo().closestT() >= bias) && (lowestT == null || obj.getHitinfo().closestT()  <= lowestT)){
                lowestT = obj.getHitinfo().closestT();
            }
        }
//        System.out.println(lowestT);
        return lowestT != null ? lowestT:1;

    }

    public void drawPoint(int y, int x, Texture texture, ObjectCol obj) {
        Hitinfo hitinfo = obj.getHitinfo();
        Light light = obj.getLight();
        // The global illumination or the ambient color
        Vector global_Illumination = light.getIntensity().getVector();

        // TODO: place inside the material
        double specularComponent = 0.1;
        Vector specular = new Vector(0.1, 0.1, 0.1, 0);

        if (hitinfo.getAmountOfHits() != 0) {// Based on p641


            // Vector to the viewer.
            Vector v = obj.ray.getDirection().multiply(1).normalise();

            // Set the emissive color of the object
            Vector color_vector = texture.getColor().getVector();

            // Ambient -> Based on the global illumination.
            color_vector = color_vector.multiply(global_Illumination);

            // Find the normal
            Direction m = new Direction(obj.getNormal().normalise());

            // A ray from the hitpoint to the light
//            Direction s_direction2 =  new Direction(new Direction(new Point(obj.getTransform().multiply(light.getPosition())), new Point(obj.getHitinfo().getPoint())).normalise());
            Direction s_direction =  new Direction(new Direction(new Point(light.getPosition()), new Point(obj.getHitinfo().getPoint())).normalise());
            Direction s_direction2 =  new Direction(new Direction(new Point(obj.transform_original.multiply(light.getPosition())), new Point(obj.getHitinfo().getPoint())).normalise());

            if(isClosestHit(new Ray(s_direction2, new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint()))), 0.001)>=1){
                //Find the lambert term
                double mDots = s_direction.dotproduct(m);
                if (mDots>0) {
                    Vector diffuseColor = obj.getTexture().getDiffuse().multiply(light.getIntensity().getVector()).multiply(mDots); //TODO: Make sure that the diffuse factor is inside the obj class
                    color_vector.add_color(diffuseColor);
                    //Half way vector and a part of the phong term
                    Vector h = v.sum(s_direction).normalise();
                    double mDotH = h.dotproduct(m);
                    if(mDotH>=0){
                        double phong = Math.pow(mDotH, specularComponent);
                        Vector specColor = specular.multiply(light.getIntensity().getVector().multiply(phong));
                        color_vector.add_color(specColor);
                    }
                }
            }






//        // If the hitpoint is the closest point
//        if(this.isClosestHit(ray, 0.001)>=1){
//            double mDots = newDir.dotproduct(normal);
//            if ( mDots < 0 )
//                normal = new Direction(normal.multiply( -1 ));
////
//            double intensity = mDots / Math.abs(normal.getnorm() * newDir.getnorm());
//
//            // Only light up if the hit point is facing the light
//            if (intensity > 0 ) {
//                r = (float) (r + Math.max(r * intensity * light.getIntensity().getR(), 0));
//                g = (float) (g + Math.max(g *intensity * light.getIntensity().getG(), 0));
//                b = (float) (b + Math.max(b * intensity * light.getIntensity().getB(), 0));
//            }
//
//            // phong
//            // Halfway vector
//            Direction halfway = new Direction( newDir.sum(toViewer ).normalise() );
//            double mDoth = halfway.dotproduct( normal );
//
//            if (mDoth > 0) // If the hit point is facing the light
//            {
//                double phong = Math.pow(mDoth, 1.0);
//                r = (float) (r + r * phong * light.getIntensity().getR());
//                g = (float) (g + g * phong * light.getIntensity().getG());
//                b = (float) (b + b * phong * light.getIntensity().getB());
//            }
//            r = Math.min(r, 1.0f);
//            g = Math.min(g, 1.0f);
//            b = Math.min(b, 1.0f);
//
            pointPanel.drawPoint(y, x, color_vector);
//        }
        } else { // This means there is no hit so we apply the background color #TODO add background color as a config
            Vector color_vector = new Vector(1.0, 1.0, 1.0, 0);
            color_vector = color_vector.multiply(global_Illumination);
            pointPanel.drawPoint(y, x, color_vector);
        }


    }
}




