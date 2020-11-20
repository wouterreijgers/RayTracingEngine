import graphics.PointPlotter;
import mathematics.*;
import objects.Cube;
import objects.Ray;
import objects.Sphere;
import util.Hitinfo;


public class Main {

	public static void main(String[] args) {

		// Define window size;
		int nRows = 700;
		int nCols = 700;
		PointPlotter plotter = new PointPlotter(nCols, nRows);

		// RAY
		Point eye = new Point(7, 0, 0);
		Ray ray = new Ray();

		// OBJECTS
		Sphere sphere = new Sphere();
		Cube cube = new Cube();
		Hitinfo hitinfo;
		Matrix transform = new MatrixFactory().rotationMatrix("X", Math.PI / 3);
		transform = transform.multiply( new MatrixFactory().rotationMatrix("Y", Math.PI / 4 ));


//		transform = transform.multiply( new MatrixFactory().rotationMatrix("Z", 15));

		Matrix transf = transform.getInverse();
		ray.setStart(new Point(transf.multiply(eye.getVector())));
		for(double r=0; r<nRows; r++) {
			for (double c=0; c<nCols; c++){
				Vector direction = new Vector(-7, 2*((2*c)/nCols-1), 2*((2*r)/nRows-1), 0);
				ray.setDirection(new Direction(transf.multiply(direction.getVector())));

				hitinfo = cube.isHit(ray);

				if(hitinfo.getAmountOfHits()>0){
					plotter.drawPoint((int)r, (int)c);
				}
			}
		}
		plotter.forceUpdate();
	}
}
