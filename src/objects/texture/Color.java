package objects.texture;

import mathematics.Direction;
import mathematics.Vector;

public class Color{
    public float r;
    public float g;
    public float b;

    public Color(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }


    public Color(String color){
        switch(color){
            case "blue":
                r = 0.2f;
                g = 0.2f;
                b = 0.5f;
                break;
            case "purple":
                r = 0.3f;
                g = 0.2f;
                b = 0.3f;
                break;
            case "red":
                r = 0.4f;
                g = 0.1f;
                b = 0.05f;
                break;
            case "grey":
                r = 0.3f;
                g = 0.3f;
                b = 0.3f;
                break;
            case "white>":
                r = 0.5f;
                g = 0.5f;
                b = 0.5f;
                break;
            default:
                r = 1;
                g = 1;
                b = 1;
        }
    }

    public Color(Vector color_vector) {
        this.r = (float) color_vector.getX();
        this.g = (float) color_vector.getY();
        this.b = (float) color_vector.getZ();
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public Vector getVector() { return new Vector(r, g, b, 0);}

    public void getIllumination(Direction normal){

    }
}
