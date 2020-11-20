package util;

import mathematics.Point;

import java.util.HashMap;
import java.util.Map;

public class Hitinfo {
    Map<Double, Point> hitlist = new HashMap<Double, Point>(); // Create an ArrayList object

    public Hitinfo(){
    }

    public void addHit(double t, Point p){
        hitlist.put(t, p);
    }

    public int getAmountOfHits() {
        return hitlist.size();
    }

    public Point getHit(double t){
        return hitlist.get(t);
    }
}

