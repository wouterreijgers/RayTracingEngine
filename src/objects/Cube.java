package objects;

import mathematics.Direction;
import mathematics.Point;
import util.Hitinfo;
import util.Logger;

public class Cube implements ObjectShapeIF{
    public Cube(){}

    public Hitinfo isHit(Ray ray){
        // Calculate a discriminant
        double t;
        Point p;
        Hitinfo hitinfo = new Hitinfo();

        /*
        More info can be found at page 635
        #TODO: implement the method used at page 635
         */
        // X
        t = (1 - ray.eye.getX()) / ray.direction.getX();
        //System.out.println(ray.direction.getX());
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'X'))
            hitinfo.addHit(t, p, new Direction(1, 0, 0));

        t = (-1 - ray.eye.getX()) / ray.direction.getX();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'X'))
            hitinfo.addHit(t, p, new Direction(-1, 0, 0));

        // Y
        t = (1 - ray.eye.getY()) / ray.direction.getY();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Y'))
            hitinfo.addHit(t, p, new Direction(0, 1, 0));

        t = (-1 - ray.eye.getY()) / ray.direction.getY();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Y'))
            hitinfo.addHit(t, p, new Direction(0, -1, 0));

        // Z
        t = (1 - ray.eye.getZ()) / ray.direction.getZ();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Z'))
            hitinfo.addHit(t, p, new Direction(0, 0, 1));

        t = (-1 - ray.eye.getZ()) / ray.direction.getZ();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Z'))
            hitinfo.addHit(t, p, new Direction(0, 0, -1));

//        System.out.println(hitinfo.getAmountOfHits());
        hitinfo.fillCache();
        return hitinfo;

    }

    private Boolean hit(double t, Point p, char axis){
        switch (axis){
            case 'X':
                return t >= 0 && Math.abs(p.getY()) <= 1 && Math.abs(p.getZ()) <= 1;
            case 'Y':
                return t >= 0 && Math.abs(p.getX()) <= 1 && Math.abs(p.getZ()) <= 1;
            case 'Z':
                return t >= 0 && Math.abs(p.getX()) <= 1 && Math.abs(p.getY()) <= 1;
        }
        return false;

    }
}
