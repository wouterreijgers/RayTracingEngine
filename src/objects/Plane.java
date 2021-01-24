package objects;

import mathematics.Direction;
import mathematics.Point;
import util.Hitinfo;

public class Plane implements ObjectShapeIF {
    public Plane(){}
    @Override
    public Hitinfo isHit(Ray ray) {
        Hitinfo hitinfo = new Hitinfo();

        double t = -(ray.eye.getZ()) / ray.direction.getZ();
        Point p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (t >= 0) {
            hitinfo.addHit(t, p, new Direction(0, 0, -1));

        }
        hitinfo.fillCache();

        return hitinfo;
    }
}
