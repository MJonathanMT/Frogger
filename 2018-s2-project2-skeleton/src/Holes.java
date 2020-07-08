import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class implements the Holes or Goals that a player enters
 * 
 * @author Marvel Jonathan
 */
public class Holes extends Sprite {

	// image for the holes
	Image holeImage;
	private static String HOLE_IMAGE = "assets/frog.png";

	// boolean if this hole is entered or not
	private boolean goalEntered;

	/**
	 * This constructs a hole with an x and y coordinate with a boolean that checks
	 * if it is filled
	 * 
	 * @param x         X-coordinate of the Sprite
	 * @param y         Y-coordinate of the Sprite
	 * @param checkMark Boolean to check if it is filled
	 */
	public Holes(float x, float y, boolean checkMark) {
		super(HOLE_IMAGE, x, y);
		goalEntered = checkMark;
	}

	/**
	 * This function is called when a Hole contacts a player this causes the hole to
	 * be filled
	 * 
	 * @param otherSprite This parameter is to check which other Sprite this Sprite
	 *                    contacts
	 */
	public void contactSprite(Sprite otherSprite) {
		if (otherSprite instanceof Player && otherSprite.getY() == this.getY()) {
			// Player loses a life if enters a filled hole
			if (goalEntered) {
				((Player) otherSprite).loseLife();
				((Player) otherSprite).reset();
			} else if (!goalEntered) {
				setGoalEntered(true);
				((Player) otherSprite).reset();
			}
		}
	}

	/**
	 * This function is called to render the image of the Hole It only renders the
	 * image if the hole is filled
	 */
	public void render() {
		try {
			holeImage = new Image(HOLE_IMAGE);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		if (goalEntered) {
			holeImage.drawCentered(getX(), getY());
		}
	}

	// getters and setters for boolean goalEntered
	public boolean getGoalEntered() {
		return goalEntered;
	}

	public void setGoalEntered(boolean goalCondition) {
		this.goalEntered = goalCondition;
	}
}
