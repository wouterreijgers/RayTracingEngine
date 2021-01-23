package mathematics;

public class Direction extends Vector {

    public Direction(double[] vector) {
        super(vector);
        super.vector[3] = 1;
    }

    public Direction(double x, double y, double z) {
        super(x, y, z, 0);
    }

    public Direction(Point a, Point b) {
        super(a.getX()-b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ(), a.getA() - b.getZ());
    }

    public Direction(Vector vector) {
        super(vector.vector);
        super.vector[3] = 0;
    }

}
