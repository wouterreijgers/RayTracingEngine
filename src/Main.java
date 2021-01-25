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
		int nRows = 1080;
		int nCols = 1080;

		PointPlotter plotter = null;

		AMOUNT_OF_FRAMES = 1200; // this with 800 is nice!
		SAFE_POINTS = 12;
		MAX_REFLECT_RECURSION = 5;
		MAX_REFRACT_RECURSION = 5;
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

		SceneFactory sceneFactory = new SceneFactory();
		Image export = new Image();
		for(double frame = 0; frame<AMOUNT_OF_FRAMES; frame = frame + TIME_STEP) {
			// RAY
			Point eye;
			Light light;

			if(AMOUNT_OF_FRAMES>1){
				eye = new Point(7, -6+0.001 + frame/100, -0.5);
				light = new Light(new Point(4, 2-frame/100, -2), new Color(1f, 1f, 1f));
			} else {
				eye = new Point(7, 0.001, -0.5); // TODO: SET OTHER ONE FOR VIDEO
//				light = new Light(new Point(-7, 4, -1), new Color(1f, 0.67f, 0.67f)); // TODO: LIGHT FOR DESERT
				light = new Light(new Point(4, 2, -2), new Color(1f, 1f, 1f)); // TODO: DEMO LIGHT
//				light = new Light(new Point(4, 6, -0.2), new Color(1f, 1f, 1f)); // TODO: DEMO LIGHT
			}

			Ray ray = new Ray();

			objects = sceneFactory.getBase(eye);
//			objects = sceneFactory.getSpace(eye);
//			objects = sceneFactory.getDesert(eye);

			for(ObjectCol obj:objects){
				obj.setLight(light);
				obj.inverses();
			}
			if(plotter==null){
				plotter = new PointPlotter(nCols, nRows, objects, MAX_REFLECT_RECURSION, MAX_REFRACT_RECURSION);
			} else {
				plotter.clear();
			}

			for (double r = 0; r < nRows; r++) {
				if(!(AMOUNT_OF_FRAMES >1))
					plotter.forceUpdate();
				for (double c = 0; c < nCols; c++) {
					Vector direction = new Vector(-7, 2 * (((2 * c) / nCols) - 1) + (3-frame/200), 2 * (((2 * r) / nRows) - 1), 0);

					for (ObjectCol obj : objects) {
						obj.isHit(direction);
					}

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
