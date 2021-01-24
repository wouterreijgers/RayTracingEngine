import graphics.PointPlotter;
import mathematics.*;
import objects.*;
import objects.texture.Color;
import objects.texture.Texture;
import org.junit.Test;
import util.Hitinfo;

import java.util.Map;


public class Main {

	public static void main(String[] args) {

		// Define window size;
		ObjectCol[] objects = new ObjectCol[5];
		int nRows = 900;
		int nCols = 900;

		// RAY
		Point eye = new Point(7, 0.001, -0.5);
		Ray ray = new Ray();


		// OBJECTS
		objects[0] = new ObjectCol(
				"Cube",
				new Cube(),
				new MatrixFactory().translationMatrix(0, 0, -1)
						.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/4))
						.multiply(new MatrixFactory().scalingMatrix(10, 10, 4)),
				//.multiply(new MatrixFactory().)
				new Hitinfo(),
				eye,
				new Texture(new Color(0.38f, 0.48f, 0.3f), new Vector(0.5, 0.5, 0.5, 0), new Vector(0.1, 0.1, 0.1, 0), 2.0, 0.4)
		);
		objects[1] = new ObjectCol(
				"Bol 1-mat",
				new Sphere(),
				new MatrixFactory()
						.scalingMatrix(0.5, 0.5, 0.5)
						//.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 12))
						.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI))
						.multiply(new MatrixFactory().translationMatrix(0, -1.3, -.5)),
//						.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 4 )),
				new Hitinfo(),
				eye,
				new Texture(new Color(0.3f, 0.2f, 0.3f), new Vector(0.4, 0.4, 0.4, 0), new Vector(0.2, 0.2, 0.2, 0), 2.0, 0.0)
		);
		objects[2] = new ObjectCol(
				"Bol 2-glim",
				new Sphere(),
				new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
						.multiply(new MatrixFactory().translationMatrix(-3, -0, -4))
						.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
				//.multiply(new MatrixFactory().)
				new Hitinfo(),
				eye,
				new Texture(new Color(0.1f, 0.1f, 0.1f), new Vector(0.2, 0.2, 0.2, 0), new Vector(0.4, 0.4, 0.4, 0), 3.0, 0.0)
		);
		objects[3] = new ObjectCol(
				"Pyramide",
				new Pyramid(),
				new MatrixFactory().translationMatrix(-1.5, 1.3, 0)
						.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/4))
						.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 1)),
				//.multiply(new MatrixFactory().)
				new Hitinfo(),
				eye,
				new Texture(new Color(0.3f, 0.2f, 0.6f), new Vector(0.4, 0.4, 0.4, 0), new Vector(0.1, 0.1, 0.1, 0), 2.0, 0.0)
		);
		objects[4] = new ObjectCol(
				"Cube",
				new Cube(),
				new MatrixFactory().translationMatrix(-1, -2.5, -1)
						.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/4))
						.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
				//.multiply(new MatrixFactory().)
				new Hitinfo(),
				eye,
				new Texture(new Color("grey"), new Vector(0.2, 0.2, 0.2, 0), new Vector(0.1, 0.1, 0.1, 0), 0.3, 0.65)
		);

		Light light = new Light(new Point(4, 1.3, -2), new Color(1f, 1f, 1f));
		for(ObjectCol obj:objects){
			obj.setLight(light);
			obj.inverses();
		}
		PointPlotter plotter = new PointPlotter(nCols, nRows, objects);
		plotter.setLight(light);

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

		// In this piece of code the Eye is inverted, the naming is a bit confusing.

		//Matrix transf = transform.getInverse();
		//ray.setStart(new Point(transf.multiply(eye.getVector())));
		for(double r=0; r<nRows; r++) {
			plotter.forceUpdate();
			for (double c=0; c<nCols; c++){
				Vector direction = new Vector(-7, 2*(((2*c)/nCols)-1), 2*(((2*r)/nRows)-1), 0);


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
				plotter.drawPoint((int)r, (int)c, closestObj.getTexture(), closestObj);

			}
		}
		plotter.forceUpdate();
	}
}
