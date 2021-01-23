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
		ObjectCol[] objects = new ObjectCol[3];
		int nRows = 700;
		int nCols = 700;

		// RAY
		Point eye = new Point(7, 0.001, -0.5);
		Ray ray = new Ray();


		// OBJECTS
		objects[0] = new ObjectCol(
				new Plane(),
				new MatrixFactory().translationMatrix(0, 0, 10),
						//.scalingMatrix(1.0, 0.6, 1.0)
						//.multiply(new MatrixFactory().)
						//.multiply(new MatrixFactory().rotationMatrix("X", .125*Math.PI)),
				new Hitinfo(),
				eye,
				new Texture(new Color(0.3f, 0.3f, 0.3f), new Vector(0.5, 0.5, 0.5, 0))
		);
		objects[1] = new ObjectCol(
				new Pyramid(),
				new MatrixFactory()
						.scalingMatrix(1, 1, 1)
						.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 12))
						.multiply(new MatrixFactory().rotationMatrix("Z", 15*Math.PI /4))
						.multiply(new MatrixFactory().translationMatrix(0.3, -0.65, -.01)),
//						.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 4 )),
				new Hitinfo(),
				eye,
				new Texture(new Color(0.3f, 0.2f, 0.3f), new Vector(0.4, 0.4, 0.4, 0))
		);
		objects[2] = new ObjectCol(
				new Sphere(),
				new MatrixFactory().translationMatrix(-1.5, 0.75, -1)
						//.multiply(new MatrixFactory().rotationMatrix("Z", -.33*Math.PI))
						.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
				//.multiply(new MatrixFactory().)
				new Hitinfo(),
				eye,
				new Texture(new Color("blue"), new Vector(0.6, 0.6, 0.6, 0))
		);
		PointPlotter plotter = new PointPlotter(nCols, nRows, objects);
		Light light = new Light(new Point(4, 3, -2), new Color(1, 1, 1));
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
		for(ObjectCol obj:objects){
			obj.setLight(light);
			obj.inverses();
		}
		//Matrix transf = transform.getInverse();
		//ray.setStart(new Point(transf.multiply(eye.getVector())));
		for(double r=0; r<nRows; r++) {
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
