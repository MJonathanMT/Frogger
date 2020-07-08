import org.newdawn.slick.Input;

/**
 * This class implements a long log that could drag a player
 * 
 * @author Marvel Jonathan
 */
public class LongLog extends Rideable {

	// longLong speed
	private static final float LONGLOG_SPEED = 0.07f;

	// image for longLog
	private static String LONGLOG_IMAGE = "assets/longlog.png";

	// size of image thats off-screen
	private float offScreenSize;

	// direction for longLog -1(left) and 1(right)
	private float longLogDirection;
	private float longLogMovement;

	// distance between extra life and center of this sprite
	private float extraLifeDist;

	/**
	 * This constructs a long log with an x and y coordinate
	 * 
	 * @param x         X-coordinate of the Sprite
	 * @param y         Y-coordinate of the Sprite
	 * @param direction The direction of the Sprite
	 */
	public LongLog(float x, float y, int direction) {
		super(LONGLOG_IMAGE, x, y);
		longLogDirection = direction;
	}

	@Override
	public void update(Input input, int delta) {
		// size of the off screen image
		offScreenSize = Sprite.BORDER_SIZE + World.TILE_SIZE * 2;
		longLogMovement = longLogDirection * LONGLOG_SPEED * delta;
		// moves the longLog according to LONGLOG_SPEED with direction
		setX(getX() + longLogMovement);

		// pops back the longLog to the other side when it reaches a corner
		if (getX() > App.SCREEN_WIDTH + offScreenSize) {
			setX(-offScreenSize);
		}
		if (getX() < (-offScreenSize)) {
			setX(App.SCREEN_WIDTH + offScreenSize);
		}

		// sets bounding box of longLog
		setBb(getX(), getY());
	}

	/**
	 * This function is called when this sprite contacts player or extralife it then
	 * drags the otherSprite base on its movement
	 * 
	 * @param otherSprite This parameter is to check which other Sprite this Sprite
	 *                    contacts
	 */
	public void contactSprite(Sprite otherSprite) {
		if (otherSprite instanceof Player) {
			if (((otherSprite.getX() + longLogMovement) < App.SCREEN_WIDTH - Sprite.BORDER_SIZE)
					&& ((otherSprite.getX() + longLogMovement) > Sprite.BORDER_SIZE)) {
				otherSprite.setX(otherSprite.getX() + longLogMovement);
			} else if (longLogDirection == 1) {
				otherSprite.setX(App.SCREEN_WIDTH - Sprite.BORDER_SIZE);
			} else {
				otherSprite.setX(Sprite.BORDER_SIZE);
			}
		}
		if (otherSprite instanceof ExtraLife) {
			extraLifeDist = otherSprite.getX() - this.getX();
			otherSprite.setX(otherSprite.getX() + longLogMovement);
			if (this.getX() > App.SCREEN_WIDTH + offScreenSize) {
				otherSprite.setX(-offScreenSize + extraLifeDist);
			}
			if (this.getX() < (-offScreenSize)) {
				setX(App.SCREEN_WIDTH + offScreenSize + extraLifeDist);
			}
		}
	}
}
