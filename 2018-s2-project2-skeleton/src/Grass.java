/**
 * This class implements a grass tile
 * 
 * @author Marvel Jonathan
 */
public class Grass extends Sprite {

	// image for grass
	private static final String GRASS_IMAGE = "assets/grass.png";

	/**
	 * This constructs a Grass Tile with x and y coordinate
	 * 
	 * @param x X-coordinate of the Sprite
	 * @param y Y-coordinate of the Sprite
	 */
	public Grass(float x, float y) {
		super(GRASS_IMAGE, x, y);
	}
}
