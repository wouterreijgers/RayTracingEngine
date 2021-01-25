package util;

import mathematics.Direction;
import mathematics.Point;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hitinfo {

    Map<Double, HitPoint> hitlist = new HashMap<Double, HitPoint>(); // double = Hit time, Hitpoint contains more information about the point.
    public double closestT;
    public Direction normal;
    public Point point;
    public Boolean isInside;


    public Hitinfo(){
    }

    public void addHit(double t, Point p, Direction d){
        HitPoint point = new HitPoint(p, d);
        hitlist.put(t, point);
    }

    public int getAmountOfHits() {
        return hitlist.size();
    }

    public void fillCache(){
        isInside = false;
        if(hitlist.size()!=0){
            double lowestT = Double.POSITIVE_INFINITY;
            HitPoint lowestHit = new HitPoint();
            for ( Map.Entry<Double, HitPoint> entry : hitlist.entrySet()) {
                double key = entry.getKey();
                HitPoint value = entry.getValue();
                if(key<0 || hitlist.size()==1){
                    isInside = true;
                }
                if(key<=lowestT && key>0.0001){
                    lowestT = key;
                    lowestHit = value;
                }
            }
            this.closestT = lowestT;
            this.normal = lowestHit.getNormal();
            this.point = lowestHit.getPoint();
        }
    }

    public Double closestT() {
        return closestT;

    }

    public Direction getNormal() {
        return normal;

    }

    public Point getPoint() {
        return point;
    }
}

