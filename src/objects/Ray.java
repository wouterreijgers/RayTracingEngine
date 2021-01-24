package objects;

import mathematics.Direction;
import mathematics.Point;
import mathematics.Vector;

public class Ray {
    public Direction direction;
    public Point eye;
    public Double recurseLevel;

    public Ray (){
        this.direction = null;
        this.eye = null;
        this.recurseLevel = 0.0;
    }
    public Ray (Direction direction, Point eye){
        this.direction = direction;
        this.eye = eye;
        this.recurseLevel = 0.0;

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
