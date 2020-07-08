import org.newdawn.slick.Input;

/**
 * This class implements a log that could drag the player
 * 
 * @author Marvel Jonathan
 */
public class Log extends Rideable {

	// log speed
	private static final float LOG_SPEED = 0.1f;

	// size of image thats off-screen
	private float offScreenSize;

	// image for log
	private static String LOG_IMAGE = "assets/log.png";

	// direction for log -1(left) and 1(right)
	private float logDirection;

	// log's movement is the speed * its direction * delta
	private float logMovement;

	// distance extralife and center of this sprite
	private float extraLifeDist;

	/**
	 * This constructs a log with an x and y coordinate
	 * 
	 * @param x         X-coordinate of the Sprite
	 * @param y         Y-coordinate of the Sprite
	 * @param direction The direction of the Sprite
	 */
	public Log(float x, float y, int direction) {
		super(LOG_IMAGE, x, y);
		logDirection = direction;
	}

	@Override
	public void update(Input input, int delta) {
		// size of the off screen image
		offScreenSize = Sprite.BORDER_SIZE + World.TILE_SIZE;

		// moves the log according to LOG_SPEED with direction
		logMovement = logDirection * LOG_SPEED * delta;
		setX(getX() + logDirection * LOG_SPEED * delta);

		// pops back the log to the other side when it reaches a corner
		if (getX() > App.SCREEN_WIDTH + offScreenSize) {
			setX(-offScreenSize);
		}
		if (getX() < (-offScreenSize)) {
			setX(App.SCREEN_WIDTH + offScreenSize);
		}
		// sets bounding box of log
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
			if (((otherSprite.getX() + logMovement) < App.SCREEN_WIDTH - Sprite.BORDER_SIZE)
					&& ((otherSprite.getX() + logMovement) > Sprite.BORDER_SIZE)) {
				otherSprite.setX(otherSprite.getX() + logMovement);
			} else if (logDirection == 1) {
				otherSprite.setX(App.SCREEN_WIDTH - Sprite.BORDER_SIZE);
			} else {
				otherSprite.setX(Sprite.BORDER_SIZE);
			}
		}
		if (otherSprite instanceof ExtraLife) {
			extraLifeDist = otherSprite.getX() - this.getX();
			otherSprite.setX(otherSprite.getX() + logMovement);
			if (getX() > App.SCREEN_WIDTH + offScreenSize) {
				otherSprite.setX(-offScreenSize + extraLifeDist);
			}
			if (getX() < (-offScreenSize)) {
				setX(App.SCREEN_WIDTH + offScreenSize + extraLifeDist);
			}
		}
	}
}
