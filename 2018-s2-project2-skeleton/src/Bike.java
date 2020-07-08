import org.newdawn.slick.Input;

/**
 * This class implements a bike that could kill Player
 * 
 * @author Marvel Jonathan
 */
public class Bike extends Hazard {
	// bike speed
	private static final float BIKE_SPEED = 0.2f;

	// image for bike
	private static String BIKE_IMAGE = "assets/bike.png";

	// direction for bike -1(left) and 1(right)
	private float bikeDirection;

	/**
	 * This constructs a bike with an x and y value with a certain direction
	 * 
	 * @param x         X-coordinate of the sprite
	 * @param y         Y-coordinate of the sprite
	 * @param direction The starting moving direction
	 */
	public Bike(float x, float y, int direction) {
		super(BIKE_IMAGE, x, y);
		bikeDirection = direction;
	}

	@Override
	public void update(Input input, int delta) {
		// moves the bike according to BIKE_SPEED with direction
		setX(getX() + bikeDirection * BIKE_SPEED * delta);

		// changes bike's direction when it reaches a corner
		if (getX() > App.SCREEN_WIDTH) {
			bikeDirection = -1;
		} else if (getX() < 0) {
			bikeDirection = 1;
		}

		// sets bounding box of bike
		setBb(getX(), getY());
	}
}
