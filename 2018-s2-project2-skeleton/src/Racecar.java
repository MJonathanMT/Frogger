import org.newdawn.slick.Input;

/**
 * This class implements a race car that could kill a player
 * 
 * @author Marvel Jonathan
 */
public class Racecar extends Hazard {

	// race car speed
	private static final float RACECAR_SPEED = 0.5f;

	// image for race car
	private static String RACECAR_IMAGE = "assets/racecar.png";

	// direction for race car -1(left) and 1(right)
	private float racecarDirection;

	/**
	 * This constructs a race car with an x and y coordinate with a certain
	 * direction
	 * 
	 * @param x         X-coordinate of the Sprite
	 * @param y         Y-coordinate of the Sprite
	 * @param direction The starting moving direction
	 */
	public Racecar(float x, float y, int direction) {
		super(RACECAR_IMAGE, x, y);
		racecarDirection = direction;
	}

	@Override
	public void update(Input input, int delta) {
		// moves the race car according to RACECAR_SPEED with direction
		setX(getX() + racecarDirection * RACECAR_SPEED * delta);

		// pops back the race car to the other side when it reaches a corner
		if (getX() > App.SCREEN_WIDTH + Sprite.BORDER_SIZE) {
			setX(-Sprite.BORDER_SIZE);
		}
		if (getX() < (-Sprite.BORDER_SIZE)) {
			setX(App.SCREEN_WIDTH + Sprite.BORDER_SIZE);
		}

		// sets bounding box of race car
		setBb(getX(), getY());
	}
}
