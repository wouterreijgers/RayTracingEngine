package objects;

import mathematics.Direction;
import mathematics.Matrix;
import mathematics.Point;
import mathematics.Vector;
import objects.texture.Color;
import util.Hitinfo;

public class ObjectCol{
    public ObjectShapeIF object;
    public Matrix transform;
    public Hitinfo hitinfo;
    public Point eye;
    public Ray ray;
    public Color color;

    public ObjectCol(ObjectShapeIF object, Matrix transform, Hitinfo hitinfo, Point eye, Color color){
        this.object = object;
        this.hitinfo = hitinfo;
        this.transform = transform;
        this.eye = eye;
        this.ray = new Ray();
        this.color = color;
    }

    public void isHit(Vector vec){
        Direction direction = new Direction(transform.multiply(vec));
        ray.setDirection(direction);
        hitinfo = object.isHit(ray);
    }

    public ObjectShapeIF getObject() {
        return object;
    }

    public void setObject(ObjectShapeIF object) {
        this.object = object;
    }

    public Matrix getTransform() {
        return transform;
    }

    public void setTransform(Matrix transform) {
        this.transform = transform;
    }

    public Hitinfo getHitinfo() {
        return hitinfo;
    }

    public void setHitinfo(Hitinfo hitinfo) {
        this.hitinfo = hitinfo;
    }

    public void inverses() {
        this.transform = transform.getInverse();
        this.eye = new Point(transform.multiply(eye.getVector()));
        this.ray.setStart(eye);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Direction getNormal() {
        return hitinfo.getNormal();
    }
}
