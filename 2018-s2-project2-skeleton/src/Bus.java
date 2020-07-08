import org.newdawn.slick.Input;

/**
 * This class implements a bus that could kill a player
 * 
 * @author Marvel Jonathan
 */
public class Bus extends Hazard {

	// bus speed
	private static final float BUS_SPEED = 0.15f;

	// image for bus
	private static String BUS_IMAGE = "assets/bus.png";

	// direction for bus -1(left) and 1(right)
	private float busDirection;

	/**
	 * This constructs a bus with an x and y coordinate with a certain direction
	 * 
	 * @param x         X-coordinate of the Sprite
	 * @param y         Y-coordinate of the Sprite
	 * @param direction The starting moving direction
	 */
	public Bus(float x, float y, int direction) {
		super(BUS_IMAGE, x, y);
		busDirection = direction;
	}

	@Override
	public void update(Input input, int delta) {
		// moves the bus according to BUS_SPEED with direction
		setX(getX() + busDirection * BUS_SPEED * delta);

		// pops back the bus to the other side when it reaches a corner
		if (getX() > App.SCREEN_WIDTH + Sprite.BORDER_SIZE) {
			setX(-Sprite.BORDER_SIZE);
		}
		if (getX() < (-Sprite.BORDER_SIZE)) {
			setX(App.SCREEN_WIDTH + Sprite.BORDER_SIZE);
		}

		// sets bounding box of bus
		setBb(getX(), getY());
	}
}
