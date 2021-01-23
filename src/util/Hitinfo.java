package util;

import mathematics.Direction;
import mathematics.Point;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hitinfo {

    Map<Double, HitPoint> hitlist = new HashMap<Double, HitPoint>(); // double = Hit time, Hitpoint contains more information about the point.

    public Hitinfo(){
    }

    public void addHit(double t, Point p, Direction d){
        HitPoint point = new HitPoint(p, d);
        hitlist.put(t, point);
    }

    public int getAmountOfHits() {
        return hitlist.size();
    }

    public Double closestT() {
        if(hitlist.size()==0)
            return null;
        double lowestT = Double.POSITIVE_INFINITY;
        for ( Map.Entry<Double, HitPoint> entry : hitlist.entrySet()) {
            double key = entry.getKey();
            if(key<=lowestT)
                lowestT = key;
        }
        return lowestT;
    }

    public Direction getNormal() {
        if(hitlist.size()==0)
            return null;
        double lowestT = Double.POSITIVE_INFINITY;
        HitPoint lowestHit = new HitPoint();
        for ( Map.Entry<Double, HitPoint> entry : hitlist.entrySet()) {
            double key = entry.getKey();
            HitPoint value = entry.getValue();
            if(key<=lowestT) {
                lowestT = key;
                lowestHit = value;
            }
        }
        return lowestHit.getNormal();
    }

    public Point getPoint() {
        if(hitlist.size()==0)
            return null;
        double lowestT = Double.POSITIVE_INFINITY;
        HitPoint lowestHit = new HitPoint();
        for ( Map.Entry<Double, HitPoint> entry : hitlist.entrySet()) {
            double key = entry.getKey();
            HitPoint value = entry.getValue();
            if(key<=lowestT) {
                lowestT = key;
                lowestHit = value;
            }
        }
        return lowestHit.getPoint();
    }
}

