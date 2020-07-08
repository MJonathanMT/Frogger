import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This class implements a turtles that could drag the player
 * 
 * @author Marvel Jonathan
 */
public class Turtles extends Rideable {

	// time above water in milliseconds
	private static final float ABOVE_WATER = 7000f;

	// time under water in milliseconds
	private static final float UNDERWATER = 2000f;

	// image of Turtles
	private Image turtlesImage;
	private static String TURTLES_IMAGE = "assets/turtles.png";

	// turtle speed
	private static final float TURTLE_SPEED = 0.085f;

	// size off screen
	private float offScreenSize;

	// timer for the turtles
	private float deltaCounter = 0;

	// boolean true if under water
	private boolean aboveWater = true;

	// image for turtle
	private static String TURTLE_IMAGE = "assets/turtles.png";

	// direction for turtle -1(left) and 1(right)
	private float turtlesDirection;
	private float turtlesMovement;

	/**
	 * This constructs a turtles with an x and y coordinate
	 * 
	 * @param x         X-coordinate of the Sprite
	 * @param y         Y-coordinate of the Sprite
	 * @param direction The direction of the Sprite
	 */
	public Turtles(float x, float y, int direction) {
		super(TURTLE_IMAGE, x, y);
		turtlesDirection = direction;
		try {
			turtlesImage = new Image(TURTLES_IMAGE);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Input input, int delta) {
		// add delta to the deltacounter
		deltaCounter += delta;

		// go under water and resurface when conditions met
		if (deltaCounter >= ABOVE_WATER) {
			deltaCounter -= ABOVE_WATER;
			aboveWater = false;
		} else if (deltaCounter >= UNDERWATER && !aboveWater) {
			deltaCounter -= UNDERWATER;
			aboveWater = true;
		}

		// size of the off screen image
		offScreenSize = Sprite.BORDER_SIZE + World.TILE_SIZE;

		// moves the log according to LOG_SPEED with direction
		turtlesMovement = turtlesDirection * TURTLE_SPEED * delta;
		setX(getX() + turtlesMovement);

		// pops back the turtle to the other side when it reaches a corner
		if (getX() > App.SCREEN_WIDTH + offScreenSize) {
			setX(-offScreenSize);
		}
		if (getX() < (-offScreenSize)) {
			setX(App.SCREEN_WIDTH + offScreenSize);
		}

		// sets bounding box of turtle
		setBb(getX(), getY());
	}

	/**
	 * This function is called when this sprite contacts player it then drags the
	 * otherSprite base on its movement
	 * 
	 * @param otherSprite This parameter is to check which other Sprite this Sprite
	 *                    contacts
	 */
	public void contactSprite(Sprite otherSprite) {
		if (otherSprite instanceof Player) {
			if (((otherSprite.getX() + turtlesMovement) < App.SCREEN_WIDTH - Sprite.BORDER_SIZE)
					&& ((otherSprite.getX() + turtlesMovement) > Sprite.BORDER_SIZE)) {
				otherSprite.setX(otherSprite.getX() + turtlesMovement);
			} else if (turtlesDirection == 1) {
				otherSprite.setX(App.SCREEN_WIDTH - Sprite.BORDER_SIZE);
			} else {
				otherSprite.setX(Sprite.BORDER_SIZE);
			}
		}
	}

	/**
	 * This function is called to render the image of the turtles only when
	 * aboveWater = true
	 */
	public void render() {
		if (aboveWater) {
			turtlesImage.drawCentered(getX(), getY());
		}
	}

	// boolean for when the turtle is above water
	public boolean getAboveWater() {
		return aboveWater;
	}
}