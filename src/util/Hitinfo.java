package util;

import mathematics.Direction;
import mathematics.Point;

import java.util.HashMap;
import java.util.Map;

public class Hitinfo {

    Map<Double, Map<Point, Direction>> hitlist = new HashMap<Double, Map<Point, Direction>>();

    public Hitinfo(){
    }

    public void addHit(double t, Point p, Direction d){
        Map<Point, Direction> entry = new HashMap<Point, Direction>();
        entry.put(p, d);
        hitlist.put(t, entry);
    }

    public int getAmountOfHits() {
        return hitlist.size();
    }

    public double closestT() {
        if(hitlist.size()==0)
            return -Double.POSITIVE_INFINITY;
        //System.out.println("Test closest obj");
        double lowestT = Double.POSITIVE_INFINITY;
        //System.out.println(lowestT);
        for ( Map.Entry<Double, Map<Point, Direction>> entry : hitlist.entrySet()) {
            double key = entry.getKey();
            System.out.println(key);
            if(key<=lowestT)
                lowestT = key;
            //System.out.println(lowestT);

        }
        return lowestT;
    }
}

