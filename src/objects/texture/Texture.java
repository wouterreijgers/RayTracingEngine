package objects.texture;

import mathematics.Vector;

public class Texture {
    public Color color;
    public Vector diffuse;
    public Vector specular;
    public Double specularExponent;
    public Double reflectionCoeff;

    public Texture(){
    }

    public Texture(Color color, Vector diffuse, Vector specular, Double specularExponent, Double reflectionCoeff){
        this.color = color;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularExponent = specularExponent;
        this.reflectionCoeff = reflectionCoeff;
    }

    public Vector getSpecular() {
        return specular;
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

    public Color getColor() {
        return color;
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
}
