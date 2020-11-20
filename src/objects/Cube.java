package objects;

import mathematics.Point;
import util.Hitinfo;
import util.Logger;

public class Cube {
    public Cube(){}

    public Hitinfo isHit(Ray ray){
        // Calculate a discriminant
        double t;
        Point p;
        Hitinfo hitinfo = new Hitinfo();
        /*
        More info can be found at page 635
         */
        // X
        t = (1 - ray.eye.getX()) / ray.direction.getX();
        System.out.println(ray.direction.getX());
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'X'))
            hitinfo.addHit(t, p);

        t = (-1 - ray.eye.getX()) / ray.direction.getX();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'X'))
            hitinfo.addHit(t, p);

        // Y
        t = (1 - ray.eye.getY()) / ray.direction.getY();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Y'))
            hitinfo.addHit(t, p);

        t = (-1 - ray.eye.getY()) / ray.direction.getY();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Y'))
            hitinfo.addHit(t, p);

        // Z
        t = (1 - ray.eye.getZ()) / ray.direction.getZ();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Z'))
            hitinfo.addHit(t, p);

        t = (-1 - ray.eye.getZ()) / ray.direction.getZ();
        p = new Point((ray.eye.getX()+ray.direction.getX()*t), (ray.eye.getY()+ray.direction.getY()*t), (ray.eye.getZ()+ray.direction.getZ()*t));
        if (hit(t, p, 'Z'))
            hitinfo.addHit(t, p);

        System.out.println(hitinfo.getAmountOfHits());
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
