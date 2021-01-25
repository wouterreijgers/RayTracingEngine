package objects;

import util.Hitinfo;

public class BooleanObj implements ObjectShapeIF {
    public ObjectCol left, right;

    public BooleanObj(ObjectCol left, ObjectCol right){
        this.left=left;
        this.right=right;
    }

    public ObjectCol getLeft() {
        return left;
    }

    public void setLeft(ObjectCol left) {
        this.left = left;
    }

    public ObjectCol getRight() {
        return right;
    }

    public void setRight(ObjectCol right) {
        this.right = right;
    }

    @Override
    public Hitinfo isHit(Ray ray) {
        return null;
    }
}
