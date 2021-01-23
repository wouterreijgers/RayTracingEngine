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
        if (A<0){
            System.out.println("Not possible");
        }
        double B = ray.getDirection().dotproduct(ray.getStart())* 2;
        double C = Math.pow(ray.getStart().getnorm(), 2) - 1;

        double discriminant = Math.pow(B, 2)-(4*A*C);

        if (discriminant<0) {
            new Logger(this.getClass().getName(), "isHit()", "Miss");
            return hitinfo;
        }
        double t1 = (-B-Math.sqrt(discriminant))/(2*A);
        double t2 = (-B+Math.sqrt(discriminant))/(2*A);
        int amountOfHits = 2;
        if (discriminant==0)
            amountOfHits = 1;
        if(t1>0){
            Point p1 = new Point((ray.getStart().getX()+ray.getDirection().getX())*t1, (ray.getStart().getY()+ray.getDirection().getY())*t1, (ray.getStart().getZ()+ray.getDirection().getZ())*t1);
            hitinfo.addHit(t1, p1, new Direction(new Direction(p1)));
        }
        if(t2>0){
            Point p2 = new Point((ray.getStart().getX()+ray.getDirection().getX())*t2, (ray.getStart().getY()+ray.getDirection().getY())*t2, (ray.getStart().getZ()+ray.getDirection().getZ())*t2);
            System.out.println("Point2: "+p2.getX()+" time "+t2);
            hitinfo.addHit(t2, p2, new Direction(new Direction(p2)));
        }

        return hitinfo;
    }
}
