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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PointPlotter {
	
    private final PointPanel pointPanel;
    private ObjectCol[] objects;
    private int maxrecursionLevelReflect;
    private int recursionLevelReflect;
    private int maxrecursionLevelRefract;
    private int recursionLevelRefract;

    public PointPlotter(int width, int heigth, ObjectCol[] objects, int maxrecursionLevelReflect, int maxrecursionLevelRefract) {
    	 pointPanel = new PointPanel(width, heigth);

         JFrame frame = new JFrame("Ray tracing");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.add(pointPanel);
         frame.pack();
         frame.setVisible(true);
         this.objects = objects;
         this.maxrecursionLevelReflect = maxrecursionLevelReflect;
         this.maxrecursionLevelRefract = maxrecursionLevelRefract;

    	
    }

    public PointPanel getPointPanel() {
        return pointPanel;
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
        ObjectCol hitObj = null;
        for(ObjectCol obj:objects){
            Hitinfo temp = obj.isHit_light(ray);
            if ((temp.closestT() != null && temp.closestT() >= bias) && (lowestT == null || temp.closestT()  <= lowestT)){
                lowestT =temp.closestT();
                hitObj = obj;
            }
        }
        if(lowestT!=null && lowestT<1){
            return hitObj.getTexture().transparencyCoeff*hitObj.getTexture().transparencyCoeff;
        } else {
            return 1;
        }
    }

    public void drawPoint(int y, int x, Texture texture, ObjectCol obj) {
        this.recursionLevelReflect = 0;
        this.recursionLevelRefract = 0;
//        if(y == 450 && x == 450){
////            forceUpdate();
//            System.out.println("test");
//        }
        obj.ray.setInside(false);
        //pointPanel.drawPoint(y, x, Shade(texture, obj, obj.ray).getVector());
    }

    public Color Shade(Texture texture, ObjectCol obj, Ray r){
        Hitinfo hitinfo = obj.getHitinfo();
        Light light = obj.getLight();
        // The global illumination or the ambient color
        Vector global_Illumination = light.getIntensity().getVector();
        if (hitinfo.getAmountOfHits() != 0) {// Based on p641


            // Vector to the viewer.
            Vector v = r.getDirection().multiply(-1).normalise();

            // Set the emissive color of the object
            Vector color_vector = null;
            try {
                color_vector = obj.texture.getColor(obj.hitinfo.getPoint().getX(), obj.hitinfo.getPoint().getY(), obj.hitinfo.getPoint().getZ(), obj.hitinfo.getNormal());
            } catch (IOException e){
                e.printStackTrace();
            }


            // Ambient -> Based on the global illumination.
            color_vector = color_vector.multiply(global_Illumination);

            // Find the normal
            Direction m = new Direction(obj.getNormal().normalise());
            assert obj.transform.multiply(obj.transform_original).equals(new Matrix());

            // Check if the normal is ok
            if(m.dotproduct(r.getDirection())>0 && obj.name.equals("CubeBig")) {
                m = new Direction(m.multiply(-1));
            }

            // A ray from the hitpoint to the light
            Direction s_direction =  new Direction(new Direction(new Point((light.getPosition())), new Point(obj.getHitinfo().getPoint())).multiply(1));
            Direction s_direction2 =  new Direction(new Direction(new Point(obj.transform_original.multiply(light.getPosition())), new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint()))));
            double transpOtherObj = isClosestHit(new Ray(new Direction(s_direction2), new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint()))), 0.0001);
//            if(isClosestHit(new Ray(new Direction(s_direction2), new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint()))), 0.0001)>=1){
                //Find the lambert term
            s_direction = new Direction(s_direction.normalise());
            double mDots = s_direction.dotproduct(m);
            if (mDots>=0) {
                Vector diffuseColor = obj.getTexture().getDiffuse().multiply(light.getIntensity().getVector()).multiply(mDots); //TODO: Make sure that the diffuse factor is inside the obj class
                color_vector.add_color(diffuseColor.multiply(transpOtherObj));
                //Half way vector and a part of the phong term
                Vector h = v.sum(s_direction).normalise();
                double mDotH = h.dotproduct(m);
                if(mDotH>=0){
                    double phong = Math.pow(mDotH, obj.getTexture().getSpecularExponent());
                    Vector specColor = obj.getTexture().getSpecular().multiply(light.intensity.getVector());
                    color_vector.add_color(specColor.multiply(phong).multiply(transpOtherObj));
                }
            }
//            }
            if(this.recursionLevelReflect>=this.maxrecursionLevelReflect || this.recursionLevelRefract>=this.maxrecursionLevelRefract){
                return new Color(color_vector);
            }
            Boolean reflect = false;
            if(obj.texture.getTransparencyCoeff()>0.2) {
                double c1 = 300000;
                double c2 = obj.getTexture().getC2();
                double criticAngle = obj.getTexture().getCriticAngle();
                double refractionIndex = 0;
                if (r.getInside()) {
                    refractionIndex = c1 / c2;
                    r.setInside(false);
                    m = new Direction(m.multiply(-1));
                    r.exitOldObject(obj);
                } else {
                    refractionIndex = c2 / c1;
                    r.setInside(true);
                    r.enterNewObject(obj);
                }

                Vector dir = r.getDirection().multiply(-1).normalise();
                double dotProd = m.dotproduct(dir);
                double cos = 1 - ((refractionIndex * refractionIndex) * (1 - (dotProd * dotProd)));
                Vector t = new Vector();
                if (cos > criticAngle) {
                    double cosSqrt = Math.sqrt(cos);
                    double prod = (refractionIndex * dotProd) - cosSqrt;
                    Vector t_1 = dir.multiply(refractionIndex);
                    Vector t_2 = m.multiply(prod);
                    t = t_2.sum(t_1.multiply(-1));
                    Ray refract = new Ray(
                            new Direction(obj.transform_original.multiply(t)),
                            new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint())), r.getInside(), r.getInsideArray());
                    double lowestT = Double.POSITIVE_INFINITY;
                    ObjectCol closestObj = new ObjectCol();
                    for (ObjectCol object : objects) {
                        if (refract.eye == null) {
                            System.out.println("hit.closestT()");
                        }
                        Hitinfo hit = object.isHit_light(refract);
                        //                    System.out.println(hit.closestT());
                        if (hit.getAmountOfHits() > 0) {
                            if (hit.closestT() < lowestT) {
                                lowestT = hit.closestT();
                                closestObj = new ObjectCol(object, hit, refract);
                            }
                        }
                    }
                    if (closestObj.getHitinfo() == null) {
                        return new Color(color_vector);
                    }
                    this.recursionLevelRefract += 1;
                    Ray reflTransform = new Ray(new Direction(closestObj.transform.multiply(refract.direction)), new Point(obj.transform.multiply(refract.eye)), refract.getInside(), refract.getInsideArray());
                    closestObj.eye = reflTransform.eye;
                    closestObj.ray.direction = reflTransform.direction;
                    color_vector = color_vector.multiply(1 - obj.getTexture().getTransparencyCoeff()).sum(Shade(closestObj.getTexture(), closestObj, reflTransform).getVector().multiply(obj.getTexture().getTransparencyCoeff()));
                }
                else{
                    reflect = true;
                }
            }
            if(obj.texture.getReflectionCoeff()>0.01 || reflect){
                Vector dir = r.getDirection();
                double dotProd = dir.dotproduct(m);
                Vector rightPart = m.multiply(2*dotProd);
                Vector rDir = dir.substract(rightPart);
                Ray refl = new Ray(
                        new Direction(obj.transform_original.multiply(rDir)),
                        new Point(obj.transform_original.multiply(obj.getHitinfo().getPoint())));
                double lowestT = Double.POSITIVE_INFINITY;
                ObjectCol closestObj = new ObjectCol();
                for(ObjectCol object: objects){
                    Hitinfo hit = object.isHit_light(refl);
//                    System.out.println(hit.closestT());
                    if(hit.getAmountOfHits()>0){
                        if (hit.closestT()<lowestT){
                            lowestT = hit.closestT();
                            closestObj = new ObjectCol(object, hit, refl);
                        }
                    }
                }
                if(closestObj.getHitinfo()==null){
//                    System.out.println("stop");
                    return new Color(color_vector);
                }
                this.recursionLevelReflect +=1;
                Ray reflTransform = new Ray(new Direction(closestObj.transform.multiply(refl.direction)), new Point(obj.transform.multiply(refl.eye)));
                closestObj.eye = reflTransform.eye;
                closestObj.ray.direction = reflTransform.direction;
                color_vector = color_vector.multiply(1-obj.getTexture().getReflectionCoeff()).sum(Shade(closestObj.getTexture(), closestObj, reflTransform).getVector().multiply(obj.getTexture().getReflectionCoeff()));
            }
            return new Color(color_vector);
        } else { // This means there is no hit so we apply the background color #TODO add background color as a config
            Vector color_vector = new Vector(1.0, 1.0, 1.0, 0);
            color_vector = color_vector.multiply(global_Illumination);
            return new Color(color_vector);
        }
    }


    public void clear() {
        pointPanel.clear();

    }
}




