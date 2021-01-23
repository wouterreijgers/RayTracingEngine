package objects.texture;

import mathematics.Vector;

public class Texture {
    public Color color;
    public Vector diffuse;

    public Texture(){
    }

    public Texture(Color color, Vector diffuse){
        this.color = color;
        this.diffuse = diffuse;
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
