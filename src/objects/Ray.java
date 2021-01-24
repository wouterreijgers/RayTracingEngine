package objects;

import mathematics.Direction;
import mathematics.Point;
import mathematics.Vector;

public class Ray {
    public Direction direction;
    public Point eye;
    public Boolean isInside;

    public Ray (){
        this.direction = null;
        this.eye = null;
        this.isInside = false;
    }
    public Ray (Direction direction, Point eye){
        this.direction = direction;
        this.eye = eye;
        this.isInside = false;

    }

    public Ray(Direction direction, Point point, Boolean inside) {
        this.direction = direction;
        this.eye = point;
        this.isInside = inside;
    }

    public Boolean getInside() {
        return isInside;
    }

    public void setInside(Boolean inside) {
        isInside = inside;
    }

    public void setStart(Point eye) {
        this.eye = eye;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Vector getDirection(){
        return direction.getVector();
    }

    public Vector getStart() {
        return eye.getVector();
    }
}
