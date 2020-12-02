package objects;

import mathematics.Direction;
import mathematics.Point;
import util.Hitinfo;
import util.Logger;

public class Sphere implements ObjectShapeIF{

    public Sphere(){

    }

    public Hitinfo isHit(Ray ray){
        Hitinfo hitinfo = new Hitinfo();
        // Calculate a discriminant
        double A = ray.getDirection().dotproduct(ray.getDirection());
        double B = ray.getDirection().dotproduct(ray.getStart());
        double C = Math.pow(ray.getStart().getnorm(), 2)- 1;

        double discriminant = Math.pow(B, 2)-(A*C);

        if (discriminant<0) {
            new Logger(this.getClass().getName(), "isHit()", "Miss");
            return hitinfo;
        }
        double t1 = (-B+Math.sqrt(discriminant))/(A);
        double t2 = (-B-Math.sqrt(discriminant))/(A);
        int amountOfHits = 2;
        if (discriminant==0)
            amountOfHits = 1;

        Point p1 = new Point((ray.getStart().getX()+ray.getDirection().getX())*t1, (ray.getStart().getY()+ray.getDirection().getY())*t1, (ray.getStart().getZ()+ray.getDirection().getZ())*t1);
        Point p2 = new Point((ray.getStart().getX()+ray.getDirection().getX())*t2, (ray.getStart().getY()+ray.getDirection().getY())*t2, (ray.getStart().getZ()+ray.getDirection().getZ())*t2);
        hitinfo.addHit(t1, p1, new Direction(p1));
        hitinfo.addHit(t2, p2, new Direction(p2));
        return hitinfo;
    }
}
