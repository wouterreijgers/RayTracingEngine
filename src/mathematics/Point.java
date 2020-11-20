package mathematics;

public class Point extends Vector {
    public Point(double[] vector) {

        super(vector);
        super.vector[3] = 1;
    }

    public Point(double x, double y, double z) {
        super(x, y, z, 1);
    }

    public Point(Vector vector){
        super(vector.vector);
        super.vector[3] = 1;
    }

}
