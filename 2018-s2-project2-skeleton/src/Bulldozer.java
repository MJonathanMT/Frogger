import org.newdawn.slick.Input;

/**
 * This class implements a bulldozer that could push player
 * 
 * @author Marvel Jonathan
 */
public class Bulldozer extends Solid {

	// bulldozer speed
	private static final float BULLDOZER_SPEED = 0.05f;

	// image for bulldozer
	private static String BULLDOZER_IMAGE = "assets/bulldozer.png";

	// direction for bulldozer -1(left) and 1(right)
	private float bulldozerDirection;

	// bulldozer's movement is the speed * its drection * delta
	private float bulldozerMovement;

	/**
	 * This constructs a bulldozer with an x and y coordinate with a certain
	 * direction
	 * 
	 * @param x         X-coordinate of the Sprite
	 * @param y         Y-coordinate of the Sprite
	 * @param direction The starting moving direction
	 */
	public Bulldozer(float x, float y, int direction) {
		super(BULLDOZER_IMAGE, x, y);
		bulldozerDirection = direction;
	}

	@Override
	public void update(Input input, int delta) {
		// moves the bulldozer according to BULLDOZER_SPEED with direction
		bulldozerMovement = bulldozerDirection * BULLDOZER_SPEED * delta;
		setX(getX() + bulldozerMovement);

		// pops back the bulldozer to the other side when it reaches a corner
		if (getX() > App.SCREEN_WIDTH + Sprite.BORDER_SIZE) {
			setX(-Sprite.BORDER_SIZE);
		}
		if (getX() < (-Sprite.BORDER_SIZE)) {
			setX(App.SCREEN_WIDTH + Sprite.BORDER_SIZE);
		}

		// sets bounding box of bulldozer
		setBb(getX(), getY());
	}

	/**
	 * This function is called when a bulldozer contacts a player this causes the
	 * player'x to be shifted by bulldozer movement
	 * 
	 * @param otherSprite This parameter is to check which other Sprite this Sprite
	 *                    contacts
	 */
	public void contactSprite(Sprite otherSprite) {
		if (otherSprite instanceof Player) {
			otherSprite.setX(otherSprite.getX() + bulldozerMovement);
			if ((getX() >= App.SCREEN_WIDTH - World.TILE_SIZE - Sprite.BORDER_SIZE)
					|| (getX() <= World.TILE_SIZE + Sprite.BORDER_SIZE)) {
				((Player) otherSprite).loseLife();
			}
			if (((Player) otherSprite).getLivesCounter() < 1) {
				System.exit(0);
			}

		}
	}
}