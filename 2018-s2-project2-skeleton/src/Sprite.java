import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * This class is the main sprite class that all sprites inherits
 * 
 * @author Marvel Jonathan
 */
public class Sprite {

	// image for sprite's image
	private Image image;

	// location of the sprite (x, y)
	private float x;
	private float y;

	// half the tile size
	public static final int BORDER_SIZE = 24;

	// bounding box of sprite
	private BoundingBox bb;

	/**
	 * This constructs a sprite with an x and y coordinate with a certain image
	 * 
	 * @param imageSrc Image of the Sprite
	 * @param x        X-coordinate of the Sprite
	 * @param y        Y-coordinate of the Sprite
	 */
	public Sprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// sets this sprite's coordinate of x and y
		this.x = x;
		this.y = y;

		// sets the bounding box
		bb = new BoundingBox(image, x, y);
	}

	/**
	 * This function is overridden by most sprites in their own update function
	 * 
	 * @param input Input key pressed by user
	 * @param delta Amount of milliseconds passed since last update
	 */
	public void update(Input input, int delta) {
	}

	/**
	 * Renders the image of this sprite Some sprites may override this function
	 */
	public void render() {
		image.drawCentered(x, y);
	}

	/**
	 * This function is overridden by most sprites in their own contactSprite
	 * function
	 * 
	 * @param other the other sprite that this sprite contacts
	 */
	public void contactSprite(Sprite other) {
	}

	// setters and getters for x and y values
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	// setters and getters for bounding box
	public BoundingBox getBb() {
		return bb;
	}

	public void setBb(float x, float y) {
		bb.setX(x);
		bb.setY(y);
	}
}
