package objects.texture;

import mathematics.Direction;

public class Color {
    private float r;
    private float g;
    private float b;

    public Color(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(String color){
        switch(color){
            case "green":
                r = 0;
                g = 1;
                b = 0;
                break;
            default:
                r = 1;
                g = 1;
                b = 1;
        }
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

    public void getIllumination(Direction normal){

    }
}
