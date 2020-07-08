/**
 * This class implements a Water tile
 * 
 * @author Marvel Jonathan
 */
public class Water extends Hazard {

	// image for water
	public static String WATER_IMAGE = "assets/water.png";

	/**
	 * This constructs a Water Tile with x and y coordinate
	 * 
	 * @param x X-coordinate of the Sprite
	 * @param y Y-coordinate of the Sprite
	 */
	public Water(float x, float y) {
		super(WATER_IMAGE, x, y);
	}
}
