package objects;

import mathematics.Direction;
import mathematics.Point;
import mathematics.Vector;

import java.util.ArrayList;

public class Ray {
    public Direction direction;
    public Point eye;
    public Boolean isInside;
    public ArrayList<ObjectCol> insideArray;

    public Ray (){
        this.direction = null;
        this.eye = null;
        this.isInside = false;
        this.insideArray = new ArrayList<ObjectCol>();

    }
    public Ray (Direction direction, Point eye){
        this.direction = direction;
        this.eye = eye;
        this.isInside = false;
        this.insideArray = new ArrayList<ObjectCol>();
    }

    public Ray(Direction direction, Point point, Boolean inside) {
        this.direction = direction;
        this.eye = point;
        this.isInside = inside;
        this.insideArray = new ArrayList<ObjectCol>();

    }

    public Ray(Direction direction, Point point, Boolean inside, ArrayList<ObjectCol> insideArray) {
        this.direction = direction;
        this.eye = point;
        this.isInside = inside;
        this.insideArray = insideArray;

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

    public ArrayList<ObjectCol> getInsideArray() {
        return insideArray;
    }

    public void setInsideArray(ArrayList<ObjectCol> insideArray) {
        this.insideArray = insideArray;
    }

    public void enterNewObject(ObjectCol objectCol){
        this.insideArray.add(objectCol);
    }

    public void exitOldObject(ObjectCol obj) {
        for(ObjectCol object: insideArray){
            if(obj.equals(object)){
                insideArray.remove(obj);
            }
        }
    }
}
