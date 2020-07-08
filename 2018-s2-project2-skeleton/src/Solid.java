/**
 * This class implements all sprites that player cant move into
 */
public class Solid extends Sprite {

	/**
	 * This constructs the solid with an x and y coordinate with a string of their
	 * image
	 * 
	 * @param imageSrc String of their image location file
	 * @param x        X-coordinate of the Sprite
	 * @param y        Y-coordinate of the Sprite
	 */
	public Solid(String imageSrc, float x, float y) {
		super(imageSrc, x, y);
	}
}
