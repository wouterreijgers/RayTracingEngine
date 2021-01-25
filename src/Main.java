import graphics.PointPlotter;
import mathematics.*;
import objects.*;
import objects.texture.Color;
import objects.texture.Texture;
import org.junit.Test;
import util.Hitinfo;
import util.Image;

import java.net.MalformedURLException;
import java.util.Map;


public class Main {
	public static int AMOUNT_OF_FRAMES, SAFE_POINTS, MAX_REFLECT_RECURSION, MAX_REFRACT_RECURSION, TIME_STEP ;

	public static void main(String[] args) throws MalformedURLException {

		// Define window size;
		ObjectCol[] objects = new ObjectCol[6];
		int nRows = 900;
		int nCols = 900;

		PointPlotter plotter = null;

		AMOUNT_OF_FRAMES = 1; // this with 800 is nice!
		SAFE_POINTS = 8;
		MAX_REFLECT_RECURSION = 4;
		MAX_REFRACT_RECURSION = 4;
		TIME_STEP = 1; // Calculate a frame and place it TIME_STEP times
		if(AMOUNT_OF_FRAMES>1){
			System.out.println("RENDERING VIDEO:\n-------------");
			System.out.println("\t\tAMOUNT_OF_FRAMES = " + AMOUNT_OF_FRAMES+
					"\n\t\tSAFE_POINTS = " + SAFE_POINTS+
					"\n\t\tMAX_REFLECT_RECURSION = " + MAX_REFLECT_RECURSION+
					"\n\t\tMAX_REFRACT_RECURSION = " + MAX_REFRACT_RECURSION+
					"\n\t\tTIME_STEP = "+ TIME_STEP);
			System.out.println("\t\tExpected duration: " + (8*AMOUNT_OF_FRAMES)/(60*TIME_STEP) +" minutes");
		}


		Image export = new Image();
		for(double frame = 0; frame<AMOUNT_OF_FRAMES; frame = frame + TIME_STEP) {
			// RAY
			Point eye = new Point(7, 0.001, -0.5); // TODO: SET OTHER ONE FOR VIDEO
//			Point eye = new Point(7, -2+0.001 + frame/200, -0.5);
			Ray ray = new Ray();

			// OBJECTS
			objects[0] = new ObjectCol(
					"CubeBig",
					new Cube(),
					new MatrixFactory().translationMatrix(0, 0, -1)
							.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/4))
							.multiply(new MatrixFactory().scalingMatrix(10, 10, 10)),
					//.multiply(new MatrixFactory().)
					new Hitinfo(),
					eye,
					new Texture(new Color(0.1f, 0.3f, 0.1f), new Vector(0.3, 0.3, 0.3, 0), new Vector(0.2, 0.2, 0.2, 0), 0.9, 0.1001, 0.0, 0.0, 0.0)
			);
			objects[1] = new ObjectCol(
					"Bol 1-mat",
					new Sphere(),
					new MatrixFactory()
							.scalingMatrix(0.5, 0.5, 0.5)
							//.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 12))
							.multiply(new MatrixFactory().rotationMatrix("Z", 2*Math.PI))
							.multiply(new MatrixFactory().translationMatrix(-3, 1, -.5)),
	//						.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 4 )),
					new Hitinfo(),
					eye,
					new Texture(new Color(0.3f, 0.2f, 0.3f), new Vector(0.4, 0.4, 0.4, 0), new Vector(0.2, 0.2, 0.2, 0), 2.0, 0.05, 0.0, 0.0, 0.0)
			);
			objects[2] = new ObjectCol(
					"Bol 2 spiegel",
					new Sphere(),
					new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
							.multiply(new MatrixFactory().translationMatrix(-3, -0, -4))
							.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
					//.multiply(new MatrixFactory().)
					new Hitinfo(),
					eye,
					new Texture(new Color("grey"), new Vector(0.4, 0.4, 0.4, 0), new Vector(0.1, 0.1, 0.1, 0), 0.1, 1.0, 0.0, 0.0, 0.0)
			);
			objects[3] = new ObjectCol(
					"Pyramide",
					new Pyramid(),
					new MatrixFactory().translationMatrix(2.5, 1.3, 0)
							.multiply(new MatrixFactory().rotationMatrix("Z", 2*Math.PI/4))
							.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.707)),
					//.multiply(new MatrixFactory().)
					new Hitinfo(),
					eye,
					new Texture(new Color(0.3f, 0.2f, 0.1f), new Vector(0.2, 0.2, 0.2, 0), new Vector(0.1, 0.1, 0.1,0), 2.0, 0.05, 0.0, 0.0, 0.0)
			);
			objects[4] = new ObjectCol(
					"Glass",
					new Sphere(),
					new MatrixFactory().translationMatrix(2, 2, -1)
	//						.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/8))
							.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
					//.multiply(new MatrixFactory().)
					new Hitinfo(),
					eye,
					new Texture("glass")
			);
			objects[5] = new ObjectCol(
					"Cube",
					new Cube(),
					new MatrixFactory().rotationMatrix("Z", 0*Math.PI/4)
							.multiply(new MatrixFactory().translationMatrix(-3, -1, 0))
							.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 1)),
					//.multiply(new MatrixFactory().)
					new Hitinfo(),
					eye,
					new Texture(new Color(0.3f, 0.2f, 0.4f), 0.2, 0.3, 2.0, 0.0, 0.0, 0.0, 0.0, "checkerboard")
			);
			objects[3] = new ObjectCol(
					"PyramTex",
					new Cube(),
					new MatrixFactory().rotationMatrix("Z", Math.PI/4)
							.multiply(new MatrixFactory().translationMatrix(1, -2.3, -1))
							.multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
					//.multiply(new MatrixFactory().)
					new Hitinfo(),
					eye,
					new Texture(new Color("blue"), 0.2, 0.3, 4.0, 0.01, 0.0, 0.0, 0.0, "circles")
			);

//			Light light = new Light(new Point(4, 2-frame/200, -2), new Color(1f, 1f, 1f));
			Light light = new Light(new Point(4, 2, -2), new Color(1f, 1f, 1f)); // TODO: SET OTHER ONE FOR VIDEO
			for(ObjectCol obj:objects){
				obj.setLight(light);
				obj.inverses();
			}
			if(plotter==null){
				plotter = new PointPlotter(nCols, nRows, objects, MAX_REFLECT_RECURSION, MAX_REFRACT_RECURSION);
				plotter.setLight(light);
			} else {
				plotter.clear();
			}


			for (double r = 0; r < nRows; r++) {
				plotter.forceUpdate();
				for (double c = 0; c < nCols; c++) {
					Vector direction = new Vector(-7, 2 * (((2 * c) / nCols) - 1) - frame/800, 2 * (((2 * r) / nRows) - 1), 0);


					for (ObjectCol obj : objects) {
						obj.isHit(direction);
					}
					//ray.setDirection(new Direction(transf.multiply(direction.getVector())));


					//hitinfo = cube.isHit(ray);
					double lowestT = Double.POSITIVE_INFINITY;
					ObjectCol closestObj = objects[0];
					for (ObjectCol obj : objects) {
						if (obj.getHitinfo().getAmountOfHits() > 0) {
							if (obj.getHitinfo().closestT() < lowestT) {
								lowestT = obj.getHitinfo().closestT();
								closestObj = obj;
							}
						}
					}
					plotter.drawPoint((int) r, (int) c, closestObj.getTexture(), closestObj);

				}
			}
			plotter.forceUpdate();
			System.out.println("["+frame+"/"+AMOUNT_OF_FRAMES+"]");
			for(int i = 0; i<TIME_STEP; i++){
				export.createImage(plotter.getPointPanel());

			}
			double temp = frame/(AMOUNT_OF_FRAMES/SAFE_POINTS);
			if (Math.round(temp) - temp == 0 && frame!=0){
				System.out.println("Safe point reached: \n----------");
				export.createVideo();
			}
		}
		if (AMOUNT_OF_FRAMES>1){
			export.createVideo();
		}


	}
}
