/**
 * This class implements all sprites that can kill Player
 */
public class Hazard extends Sprite {

	/**
	 * This constructs the Hazard with an x and y coordinate with a string of their
	 * image
	 * 
	 * @param imageSrc String of their image location file
	 * @param x        X-coordinate of the Sprite
	 * @param y        Y-coordinate of the Sprite
	 */
	public Hazard(String imageSrc, float x, float y) {
		super(imageSrc, x, y);
	}
}
