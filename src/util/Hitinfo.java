package util;

import mathematics.Point;

public class Hitinfo {
    public int amountOfHits;
    public double t1;
    public double t2;
    Point p1;
    Point p2;

    public Hitinfo(int amountOfHits, double t1, double t2, Point p1, Point p2){
        this.amountOfHits = amountOfHits;
        this.t1 = t1;
        this.t2 = t2;
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getAmountOfHits() {
        return amountOfHits;
    }

    public void setAmountOfHits(int amountOfHits) {
        this.amountOfHits = amountOfHits;
    }

    public double getT1() {
        return t1;
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    public double getT2() {
        return t2;
    }

    public void setT2(double t2) {
        this.t2 = t2;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }
}

