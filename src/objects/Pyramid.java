package objects;

import mathematics.Direction;
import mathematics.Point;
import util.Hitinfo;

public class Pyramid implements ObjectShapeIF{
    public Pyramid(){}

    public Hitinfo isHit(Ray ray){
        // Calculate a discriminant
        double t;
        Point p;
        Hitinfo hitinfo = new Hitinfo();


        t = -ray.eye.getZ()/ ray.direction.getZ();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (t >= 0 && Math.abs(p.getX()) <= 1 && Math.abs(p.getY()) <= 1) {
            hitinfo.addHit(t, p, new Direction(0, 0, -1));
        }
        // De twee X-Z relaties
        t = ( ray.eye.getX() - ray.eye.getZ() - 1)/(ray.direction.getZ() - ray.direction.getX());
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (t >= 0 && p.getX()>=0 && p.getX()<=1 && Math.abs(p.getY())<=1+p.getZ()) {
            hitinfo.addHit(t, p, new Direction(1, 0, -1));
        }
        t = ( - ray.eye.getX() - ray.eye.getZ() - 1)/(ray.direction.getZ() + ray.direction.getX());
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (t >= 0 && p.getX()<=0 && p.getX()>=-1 && Math.abs(p.getY())<=1+p.getZ()) {
            hitinfo.addHit(t, p, new Direction(-1, 0, -1));
        }

        // De twee Y-Z relaties
        t = ( ray.eye.getY() - ray.eye.getZ() - 1)/(ray.direction.getZ() - ray.direction.getY());
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (t >= 0 && p.getY()>=0 && p.getY()<=1 && Math.abs(p.getX())<=1+p.getZ()) {
            hitinfo.addHit(t, p, new Direction(0, 1, -1));
        }
        t = ( - ray.eye.getY() - ray.eye.getZ() - 1)/(ray.direction.getZ() + ray.direction.getY());
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (t >= 0 && p.getY()<=0 && p.getY()>=-1 && Math.abs(p.getX())<=1+p.getZ()) {
            hitinfo.addHit(t, p, new Direction(0, -1, -1));
        }
//        System.out.println(hitinfo.getAmountOfHits());
        hitinfo.fillCache();

        return hitinfo;

    }


}
