package objects.texture;

import mathematics.Vector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    public Color color;
    public Vector diffuse;
    public Vector specular;
    public Double specularExponent;
    public Double reflectionCoeff;
    public Double transparencyCoeff;
    public Double c2;
    public double criticAngle;
    public String advancedTexture;
    public BufferedImage image;
    public BufferedImage imageDesert;
    public BufferedImage imageEarth;
    public BufferedImage imageSpace;
    public BufferedImage imageMoon;

    public Texture(){
    }

    public Texture(Color color, Vector diffuse, Vector specular, double specularExponent, double reflectionCoeff, double transparencyCoeff, double c2, double criticAngle){
        this.color = color;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularExponent = specularExponent;
        this.reflectionCoeff = reflectionCoeff;
        this.transparencyCoeff = transparencyCoeff;
        this.c2 = c2;
        this.criticAngle = criticAngle;
        this.advancedTexture = null;

    }
    public Texture(Color color, double diffuse, double specular, double specularExponent, double reflectionCoeff, double transparencyCoeff, double c2, double criticAngle){
        this.color = color;
        this.diffuse = new Vector(diffuse, diffuse, diffuse, 0);
        this.specular = new Vector(specular, specular, specular, 0);
        this.specularExponent = specularExponent;
        this.reflectionCoeff = reflectionCoeff;
        this.transparencyCoeff = transparencyCoeff;
        this.c2 = c2;
        this.criticAngle = criticAngle;
        this.advancedTexture = null;
    }

    public Texture(Color color, double diffuse, double specular, double specularExponent, double reflectionCoeff, double transparencyCoeff, double c2, double criticAngle, String advancedTexture) {
        this.color = color;
        this.diffuse = new Vector(diffuse, diffuse, diffuse, 0);
        this.specular = new Vector(specular, specular, specular, 0);
        this.specularExponent = specularExponent;
        this.reflectionCoeff = reflectionCoeff;
        this.transparencyCoeff = transparencyCoeff;
        this.c2 = c2;
        this.criticAngle = criticAngle;
        this.advancedTexture = advancedTexture;
//        try {
//            this.image = ImageIO.read(new File("texture1.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            this.imageDesert = ImageIO.read(new File("texture4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            this.imageEarth = ImageIO.read(new File("texture5.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            this.imageSpace = ImageIO.read(new File("textureStar.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            this.imageMoon = ImageIO.read(new File("textureMoon.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public Texture(String material){
        switch(material) {
            case "glass":
                this.color = new Color("grey");
                this.diffuse = new Vector(0.3, 0.3, 0.3, 0);
                this.specular = new Vector(0.0, 0.0, 0.0, 0);
                this.specularExponent = 0.20;
                this.reflectionCoeff = 0.1;
                this.transparencyCoeff = 0.9;
                this.c2 = 0.52 * 300000;
                this.criticAngle = 0.01;
                this.advancedTexture = null;
                break;
        };
    }

    public Vector getSpecular() {
        return specular;
    }

    public Double getC2() {
        return c2;
    }

    public void setC2(Double c2) {
        this.c2 = c2;
    }

    public double getCriticAngle() {
        return criticAngle;
    }

    public void setCriticAngle(double criticAngle) {
        this.criticAngle = criticAngle;
    }

    public void setSpecular(Vector specular) {
        this.specular = specular;
    }

    public Double getSpecularExponent() {
        return specularExponent;
    }

    public void setSpecularExponent(Double specularExponent) {
        this.specularExponent = specularExponent;
    }

    public Double getReflectionCoeff() {
        return reflectionCoeff;
    }

    public void setReflectionCoeff(Double reflectionCoeff) {
        this.reflectionCoeff = reflectionCoeff;
    }

    public Vector getColor(double x, double y, double z, Vector normal) throws IOException {
        if(advancedTexture == null){
            return color.getVector();
        } else {
            int width3, height3, u3, v3,rgb3;
            double red3, green3, blue3;
            Vector vec3;
            switch(advancedTexture) {
                case "checkboard":
                    int jump = ((int) Math.round(x/0.5) + (int) Math.round(y/0.5) + (int) Math.round(z/(0.5*0.707))) % 2;
                    if (Math.abs(jump) == 0) {
                        return color.getVector().multiply(1);
                    } else if (Math.abs(jump) == 1) {
                        return color.getVector().multiply(0.5);
                    } else {
                        System.out.println("ERROR" + jump);
                        return null;
                    }
                case "circles":
                    double r = (Math.sqrt(z*z + y*y + x*x));
                    int rings = (int) Math.round(r/0.05) % 2;
                    return color.getVector().multiply(0.7 + 0.2*rings%2);
                case "image":
                    int width = image.getWidth();
                    int height = image.getHeight();
                    int u, v;
                    Vector vec = new Vector(x, y, z, 0);
                    if(normal.getX()!=0){
                        u = (int) (vec.getZ() * (width-1));
                        v = (int) (vec.getY() * (height-1));
                    } else if (normal.getY()!=0){
                        u = (int) (vec.getX() * (width-1));
                        v = (int) (vec.getZ() * (height-1));
                    } else {
                        u = (int) (vec.getX() * (width-1));
                        v = (int) (vec.getY() * (height-1));
                    }

                    int rgb = image.getRGB(Math.abs(u), Math.abs(v));
                    double red = ((rgb >> 16 ) & 0x000000FF)/255.0;
                    double green = ((rgb >> 8 ) & 0x000000FF)/255.0;
                    double blue = ((rgb) & 0x000000FF)/255.0;

                    return new Vector(red*red/2, green*green/2, blue*blue/2, 0);
                case "desert":
                    int width2 = imageDesert.getWidth();
                    int height2 = imageDesert.getHeight();
                    int u2, v2;
                    Vector vec2 = new Vector(x, y, z, 0);
                    if(normal.getX()!=0){
                        u = (int) (vec2.getZ() * (width2-1));
                        v = (int) (vec2.getY() * (height2-1));
                    } else if (normal.getY()!=0){
                        u = (int) (vec2.getX() * (width2-1));
                        v = (int) (vec2.getZ() * (height2-1));
                    } else {
                        u = (int) (vec2.getX() * (width2-1));
                        v = (int) (vec2.getY() * (height2-1));
                    }

                    int rgb2 = imageDesert.getRGB(Math.abs(u), Math.abs(v));
                    double red2 = ((rgb2 >> 16 ) & 0x000000FF)/255.0;
                    double green2 = ((rgb2 >> 8 ) & 0x000000FF)/255.0;
                    double blue2 = ((rgb2) & 0x000000FF)/255.0;

                    return new Vector(red2, green2, blue2, 0);
                case "mars":
                    width3 = imageEarth.getWidth();
                    height3 = imageEarth.getHeight();
                    vec3 = new Vector(x, y, z, 0);
                    if(normal.getX()!=0){
                        u3 = (int) (vec3.getZ() * (width3-1));
                        v3 = (int) (vec3.getY() * (height3-1));
                    } else if (normal.getY()!=0){
                        u3 = (int) (vec3.getX() * (width3-1));
                        v3 = (int) (vec3.getZ() * (height3-1));
                    } else {
                        u3 = (int) (vec3.getX() * (width3-1));
                        v3 = (int) (vec3.getY() * (height3-1));
                    }

                    rgb3 = imageEarth.getRGB(Math.abs(u3), Math.abs(v3));
                    red3 = ((rgb3 >> 16 ) & 0x000000FF)/255.0;
                    green3 = ((rgb3 >> 8 ) & 0x000000FF)/255.0;
                    blue3 = ((rgb3) & 0x000000FF)/255.0;

                    return new Vector(red3*0.7, green3*0.7, blue3*0.7, 0);
                case "space":
                    width3 = imageSpace.getWidth();
                    height3 = imageSpace.getHeight();
                    vec3 = new Vector(x, y, z, 0);
                    if(normal.getX()!=0){
                        u3 = (int) (vec3.getZ() * (width3-1));
                        v3 = (int) (vec3.getY() * (height3-1));
                    } else if (normal.getY()!=0){
                        u3 = (int) (vec3.getX() * (width3-1));
                        v3 = (int) (vec3.getZ() * (height3-1));
                    } else {
                        u3 = (int) (vec3.getX() * (width3-1));
                        v3 = (int) (vec3.getY() * (height3-1));
                    }

                    rgb3 = imageSpace.getRGB(Math.abs(u3), Math.abs(v3));
                    red3 = ((rgb3 >> 16 ) & 0x000000FF)/255.0;
                    green3 = ((rgb3 >> 8 ) & 0x000000FF)/255.0;
                    blue3 = ((rgb3) & 0x000000FF)/255.0;

                    return new Vector(red3, green3, blue3, 0);
                case "moon":
                    width3 = imageMoon.getWidth();
                    height3 = imageMoon.getHeight();
                    vec3 = new Vector(x, y, z, 0);
                    if(normal.getX()!=0){
                        u3 = (int) (vec3.getZ() * (width3-1));
                        v3 = (int) (vec3.getY() * (height3-1));
                    } else if (normal.getY()!=0){
                        u3 = (int) (vec3.getX() * (width3-1));
                        v3 = (int) (vec3.getZ() * (height3-1));
                    } else {
                        u3 = (int) (vec3.getX() * (width3-1));
                        v3 = (int) (vec3.getY() * (height3-1));
                    }

                    rgb3 = imageMoon.getRGB(Math.abs(u3), Math.abs(v3));
                    red3 = ((rgb3 >> 16 ) & 0x000000FF)/255.0;
                    green3 = ((rgb3 >> 8 ) & 0x000000FF)/255.0;
                    blue3 = ((rgb3) & 0x000000FF)/255.0;

                    return new Vector(red3, green3, blue3, 0);

                default:
                    return color.getVector();
            }
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Vector diffuse) {
        this.diffuse = diffuse;
    }

    public Double getTransparencyCoeff() {
        return transparencyCoeff;
    }

    public void setTransparencyCoeff(Double transparencyCoeff) {
        this.transparencyCoeff = transparencyCoeff;
    }
}
