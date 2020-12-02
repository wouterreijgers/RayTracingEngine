package mathematics;

public class Vector {
    double[] vector;

    public Vector() {
        double[] emptyVector = {0, 0, 0, 0};
        this.vector = emptyVector;
    }

    public Vector(double[] vector) {
        this.vector = vector;
    }

    public Vector(double x, double y, double z, double a) {
        double[] newVector = {x, y, z, a};
        this.vector = newVector;
    }

    public Vector getVector() {
        return this;
    }

    public Vector multiplication(Matrix matrix){
        double[] newVector = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++)
            newVector[i] = vector[0]*matrix.getMatrix()[i][0] +  vector[1]*matrix.getMatrix()[i][1] +  vector[2]*matrix.getMatrix()[i][2] +  vector[3]*matrix.getMatrix()[i][3];
        return new Vector(newVector);

    }

    public Vector multiply(double j){
        double[] newVector = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++)
            newVector[i] = vector[0]*j +  vector[1]*j +  vector[2]*j +  vector[3]*j;
        return new Vector(newVector);

    }

    public Vector normalise()
    {
        double magnitude = this.getnorm();
        return new Vector(
                vector[0] / magnitude,
                vector[1] / magnitude,
                vector[2] / magnitude,
                vector[3]
        );
    }

    public double dotproduct(Vector p) {
        return vector[0]*p.getX() +vector[1]*p.getY() + vector[2]*p.getZ() + vector[3]*p.getA();
    }

    public double getnorm() {
        return Math.sqrt(Math.pow(vector[0], 2.0) + Math.pow(vector[1], 2.0) + Math.pow(vector[2], 2.0));
    }

    public Vector substract(Vector arg) {
        return new Vector(vector[0]-arg.getX(), vector[0]-arg.getY(), vector[0]-arg.getZ(), vector[3]-arg.getA());
    }

    public double getX(){
        return vector[0];
    }

    public double getY(){
        return vector[1];
    }

    public double getZ(){
        return vector[2];
    }

    public double getA(){
        return vector[3];
    }

    public double get(int j) {
        return vector[j];
    }
}
