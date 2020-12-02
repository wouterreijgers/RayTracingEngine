package objects;

import mathematics.Point;
import objects.texture.Color;


public class Light {
    public Point position;
    public Color intensity;

    public Light(Point position, Color intensity){
        this.position = position;
        this.intensity = intensity;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Color getIntensity() {
        return intensity;
    }

    public void setIntensity(Color intensity) {
        this.intensity = intensity;
    }
}
