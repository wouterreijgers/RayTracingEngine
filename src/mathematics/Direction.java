package mathematics;

public class Direction extends Vector {

    public Direction(double[] vector) {
        super(vector);
    }

    public Direction(double x, double y, double z) {
        super(x, y, z, 0);
    }

    public Direction(Vector vector) {
        super(vector.vector);
    }

}
