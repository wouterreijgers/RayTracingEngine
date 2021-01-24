package mathematics;

import objects.Light;

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

//    public Vector multiply(double j){
//        double[] newVector = {0, 0, 0, 0};
//        for (int i = 0; i < 4; i++)
//            newVector[i] = vector[0]*j +  vector[1]*j +  vector[2]*j +  vector[3]*j;
//        return new Vector(newVector);
//    }
    public Vector multiply(double j){
        double[] newVector = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++)
            newVector[i] = vector[i]*j;
        return new Vector(newVector);
    }

    public Vector multiply(Vector j){
        double[] newVector = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++)
            newVector[i] = vector[i]*j.get(i) ;
        return new Vector(newVector);

    }


    public Vector sum(Vector v){
        return new Vector(
                new double[]{
                        this.getX() + v.getX(),
                        this.getY() + v.getY(),
                        this.getZ() + v.getZ(),
                        this.get(3) + v.getA()
                }
        );
    }

    public Vector invert(){
        return new Vector(
                new double[]{
                        1/this.getX(),
                        1/this.getY(),
                        1/this.getZ(),
                        1/this.get(3)
                }
        );
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
        return new Vector(vector[0]-arg.getX(), vector[1]-arg.getY(), vector[2]-arg.getZ(), vector[3]-arg.getA());
    }

    public void setX(double a){
        vector[0] = a;
    }

    public void setY(double a){
        vector[1] = a;
    }

    public void setZ(double a) { vector[2] = a;
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

    protected void setA(double a) {
        vector[3] = a;
    }

    public void add_color(Vector color) {
        /**
         * This add function should only be used for colors.
         */
        vector[0] = vector[0] + color.getX();
        vector[1] = vector[1] + color.getY();
        vector[2] = vector[2] + color.getZ();
    }

}
