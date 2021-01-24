package objects;

import mathematics.Direction;
import mathematics.Matrix;
import mathematics.Point;
import mathematics.Vector;
import objects.texture.Color;
import objects.texture.Texture;
import util.Hitinfo;

public class ObjectCol{
    public String name;
    public ObjectShapeIF object;
    public Matrix transform;
    public Matrix transform_original;
    public Hitinfo hitinfo;
    public Point eye;
    public Ray ray;
    public Texture texture;
    public Light light;

    public ObjectCol(ObjectShapeIF object, Matrix transform, Hitinfo hitinfo, Point eye, Texture texture){
        this.object = object;
        this.hitinfo = hitinfo;
        this.transform = transform;
        this.transform_original = transform;
        this.eye = eye;
        this.ray = new Ray();
        this.texture = texture;
    }
    public ObjectCol(String name, ObjectShapeIF object, Matrix transform, Hitinfo hitinfo, Point eye, Texture texture){
        this.object = object;
        this.hitinfo = hitinfo;
        this.transform = transform;
        this.transform_original = transform;
        this.eye = eye;
        this.ray = new Ray();
        this.texture = texture;
        this.name = name;
    }

    public ObjectCol(ObjectCol object, Hitinfo hitinfo, Ray ray) {
        this(object.name, object.object, object.transform, hitinfo, ray.eye , object.texture);
        this.transform_original = object.transform_original;
        this.light = object.light;
    }

    public ObjectCol() {

    }

    public void isHit(Vector vec){
        Direction direction = new Direction(transform.multiply(vec));
        ray.setDirection(direction);
        hitinfo = object.isHit(ray);
    }

    public Hitinfo isHit_light(Ray ray2){
        Direction direction2 = new Direction(transform.multiply(ray2.direction));
        Point hitpoint = new Point(transform.multiply(ray2.eye));
        return object.isHit(new Ray(direction2, hitpoint));
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
        this.light = new Light(new Point(transform.multiply(light.getPosition())),  light.getIntensity());
        this.eye = new Point(transform.multiply(eye.getVector()));
        this.ray.setStart(eye);
    }



    public Direction getNormal() {
        return hitinfo.getNormal();
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
