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

		// Initialize tools
		PointPlotter plotter = null;
		SceneFactory sceneFactory = new SceneFactory();
		Image export = new Image();

		/***
		 * Set the different parameters of the render, when the AMOUNT_OF_FRAMES>1 the system will
		 * automatically insert a path, this can be changed in the loops.
		 */
		AMOUNT_OF_FRAMES = 1; // this with 800 is nice!
		SAFE_POINTS = 12;
		MAX_REFLECT_RECURSION = 4;
		MAX_REFRACT_RECURSION = 4;
		TIME_STEP = 1; // Calculate a frame and place it TIME_STEP times
		if(AMOUNT_OF_FRAMES>1){
			System.out.println("RENDERING VIDEO:\n-------------");
			System.out.println("\tAMOUNT_OF_FRAMES = " + AMOUNT_OF_FRAMES+
					"\n\tSAFE_POINTS = " + SAFE_POINTS+
					"\n\tMAX_REFLECT_RECURSION = " + MAX_REFLECT_RECURSION+
					"\n\tMAX_REFRACT_RECURSION = " + MAX_REFRACT_RECURSION+
					"\n\tTIME_STEP = "+ TIME_STEP);
			System.out.println("\tExpected duration: " + (8*AMOUNT_OF_FRAMES)/(60*TIME_STEP) +" minutes");
		}


		// Start the frame loop
		for(double frame = 0; frame<AMOUNT_OF_FRAMES; frame = frame + TIME_STEP) {

			// Set the movements of the light/eye, Standard everything will be placed on a static position
			Point eye;
			Light light;
			if(!(AMOUNT_OF_FRAMES ==1)){
				eye = new Point(7, -6+0.001 + frame/100, -0.5);
				light = new Light(new Point(4, 2-frame/100, -2), new Color(1f, 1f, 1f));
			} else {
				eye = new Point(7, 0.001, -0.5); // TODO: SET OTHER ONE FOR VIDEO
//				light = new Light(new Point(-7, 4, -1), new Color(1f, 0.67f, 0.67f)); // TODO: LIGHT FOR DESERT
				light = new Light(new Point(4, 2, -2), new Color(1f, 1f, 1f)); // TODO: DEMO LIGHT
//				light = new Light(new Point(4, 6, -0.2), new Color(1f, 1f, 1f)); // TODO: DEMO LIGHT
			}

			/***
			 * Select the scene, note you will also have to change the lights
			 * and adapt the textures that get loaded
			 */
			objects = sceneFactory.getBase(eye);
//			objects = sceneFactory.getSpace(eye);
//			objects = sceneFactory.getDesert(eye);

			// To speed up the process we already calculate the inverses before entering the main loop
			for(ObjectCol obj:objects){
				obj.setLight(light);
				obj.inverses();
			}

			// If we are rendering more then 1 frame we clear it between every run
			if(plotter==null){
				plotter = new PointPlotter(nCols, nRows, objects, MAX_REFLECT_RECURSION, MAX_REFRACT_RECURSION);
			} else {
				plotter.clear();
			}

			/***
			 * The main loop, this is where the 'magic' happens. We iterate every pixel and calculate the
			 * color we need to show there.
			 */
			long start = System.currentTimeMillis();
			for (double r = 0; r < nRows; r++) {

				// When plotting 1 frame we can see the screen loading -> slows down the proces but errors
				// can be detected earlier
				if((r%100) == 0)
					plotter.forceUpdate();

				for (double c = 0; c < nCols; c++) {

					// Calculate the direction of the ray
					Vector direction = new Vector(-7, 2 * (((2 * c) / nCols) - 1), 2 * (((2 * r) / nRows) - 1), 0);

					// Since the eye is static on every frame we have already calculated the inverse earlier
					for (ObjectCol obj : objects) {
						obj.isHit(direction);
					}

					// Find the closes t, this is the object we hit first.
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

					// Investigate this point.
					plotter.drawPoint((int) r, (int) c, closestObj.getTexture(), closestObj);

				}
			}
			long finish = System.currentTimeMillis();
			System.out.println("Time elapsed: " + (finish - start)/1000.0 + " seconds");
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
