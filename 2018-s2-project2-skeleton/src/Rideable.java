/**
 * This class implements all sprites that can be ridden by player
 */
public class Rideable extends Sprite {

	/**
	 * This constructs the Rideable with an x and y coordinate with a string of
	 * their image
	 * 
	 * @param imageSrc String of their image location file
	 * @param x        X-coordinate of the Sprite
	 * @param y        Y-coordinate of the Sprite
	 */
	public Rideable(String imageSrc, float x, float y) {
		super(imageSrc, x, y);
	}
}
