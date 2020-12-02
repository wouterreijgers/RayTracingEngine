package util;

import mathematics.Direction;
import mathematics.Point;

public class HitPoint {
    public Point point;
    public Direction normal;
    public Boolean valid;

    public HitPoint(Point point, Direction direction){
        this.point = point;
        this.normal = direction;
        this.valid = true;
    }

    public HitPoint(){
        this.valid = false;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Direction getNormal() {
        return normal;
    }

    public void setNormal(Direction normal) {
        this.normal = normal;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
