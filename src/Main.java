import graphics.PointPlotter;
import mathematics.*;
import objects.*;
import objects.texture.Color;
import org.junit.Test;
import util.Hitinfo;

import java.util.Map;


public class Main {

	public static void main(String[] args) {

		// Define window size;
		ObjectCol[] objects = new ObjectCol[2];
		int nRows = 700;
		int nCols = 700;
		PointPlotter plotter = new PointPlotter(nCols, nRows);

		// RAY
		Point eye = new Point(7, 0, 0);
		Ray ray = new Ray();

		Light light = new Light(new Point(7, 0, 0), new Color(1.0f, 1.0f, 1.0f));
		plotter.setLight(light);
		// OBJECTS
		objects[0] = new ObjectCol(
				new Sphere(),
				new MatrixFactory().translationMatrix(0, -1, 0),
						//.scalingMatrix(1.0, 0.6, 1.0)
						//.multiply(new MatrixFactory().)
						//.multiply(new MatrixFactory().rotationMatrix("Z", 2*Math.PI)),
				new Hitinfo(),
				eye,
				new Color("green")
		);
		objects[1] = new ObjectCol(
				new Cube(),
				new MatrixFactory()
						.scalingMatrix(0.5, 0.5, 0.5)
						.multiply(new MatrixFactory().translationMatrix(-1, 1, 1)),
//						.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI / 3))
//						.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 4 )),
				new Hitinfo(),
				eye,
				new Color(0.2f, 0.1f, 0.7f)
		);


		//Cube cube = new Cube();
		//Hitinfo hitinfo;
		//Matrix transform = new MatrixFactory().rotationMatrix("X", Math.PI / 6);
		//transform = transform.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 4 ));


		/*
		Per object 	-> transform matrix
					-> hitinfo
					-> Texture?future
		RenderObjectIF? object, transform, hitinfo, texture, ...


		 */
//		transform = transform.multiply( new MatrixFactory().rotationMatrix("Z", 15));
		for(ObjectCol obj:objects){
			obj.inverses();
		}
		//Matrix transf = transform.getInverse();
		//ray.setStart(new Point(transf.multiply(eye.getVector())));
		for(double r=0; r<nRows; r++) {
			for (double c=0; c<nCols; c++){
				Vector direction = new Vector(-7, 2*((2*c)/nCols-1), 2*((2*r)/nRows-1), 0);
				for(ObjectCol obj:objects){
					obj.isHit(direction);
				}
				//ray.setDirection(new Direction(transf.multiply(direction.getVector())));


				//hitinfo = cube.isHit(ray);
				double lowestT = Double.POSITIVE_INFINITY;
				ObjectCol closestObj = objects[0];
				for(ObjectCol obj:objects){
					if(obj.getHitinfo().getAmountOfHits()>0){
						if(obj.getHitinfo().closestT()<lowestT) {
							lowestT = obj.getHitinfo().closestT();
							closestObj = obj;
						}
					}
				}
				if(closestObj.getHitinfo().getAmountOfHits()>0)
					plotter.drawPoint((int)r, (int)c, closestObj.getColor(), closestObj);

			}
		}
		plotter.forceUpdate();
	}
}
