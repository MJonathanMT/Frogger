import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This class implements a player that could be controlled by the user
 * 
 * @author Marvel Jonathan
 */
public class Player extends Sprite {

	// image for player and lives
	private static String PLAYER_IMAGE = "assets/frog.png";
	private static String LIVES_IMAGE = "assets/lives.png";
	private Image playerImage;
	private Image livesImage;

	// initial coordinates of player/frog
	private static final float PLAYER_X = 512;
	private static final float PLAYER_Y = 720;

	// previous coordinates of player/frog
	private static float previousX;
	private static float previousY;

	// boolean to ensure safety of player
	private static boolean safetyOn;

	// lives counter
	private static float livesCounter = 3;

	// initial coordinate of lives and its seperation distance
	private static final float LIVES_X = 24;
	private static final float LIVES_Y = 744;
	private static final float LIVES_TILE = 32;

	/**
	 * This constructs a player and creates it at its initial coordinates
	 */
	public Player() {
		super(PLAYER_IMAGE, PLAYER_X, PLAYER_Y);
		try {
			livesImage = new Image(LIVES_IMAGE);
			playerImage = new Image(PLAYER_IMAGE);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Input input, int delta) {
		// resets the safety of the player
		safetyOn = false;

		// sets the previous coordinate to the current coordinate before movement
		previousX = getX();
		previousY = getY();

		// calls moves function for movement
		moves(input, delta);
	}

	/**
	 * This function is called for the movement of the player based on key pressed
	 * by the user
	 * 
	 * @param input Input key pressed by user
	 * @param delta Amount of milliseconds passed since last update
	 */
	private void moves(Input input, int delta) {

		/*
		 * first if statement handles the movement and second if statement limits
		 * movement to within the screen
		 */
		// vertical movement
		if (input.isKeyPressed(Input.KEY_UP)) {
			if ((getY() - World.TILE_SIZE) >= Sprite.BORDER_SIZE) {
				setY(getY() - World.TILE_SIZE);
			}
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if ((getY() + World.TILE_SIZE) <= (App.SCREEN_HEIGHT - Sprite.BORDER_SIZE)) {
				setY(getY() + World.TILE_SIZE);
			}
		}

		// horizontal movement
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			if ((getX() - World.TILE_SIZE) >= Sprite.BORDER_SIZE) {
				setX(getX() - World.TILE_SIZE);
			}
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			if ((getX() + World.TILE_SIZE) <= (App.SCREEN_WIDTH - Sprite.BORDER_SIZE)) {
				setX(getX() + World.TILE_SIZE);
			}
		}

		// sets new bounding box
		setBb(getX(), getY());
	}

	/**
	 * This function is called when player contacts another sprite multiple if
	 * statements are written to handle different sprites
	 * 
	 * @param otherSprite This parameter is to check with other sprite the player
	 *                    contacts
	 */
	public void contactSprite(Sprite otherSprite) {
		// contacts rideable (Log, LongLog or turtle)
		if (otherSprite instanceof Rideable) {
			if (otherSprite instanceof Turtles) {
				safetyOn = ((Turtles) otherSprite).getAboveWater();
			} else {
				safetyOn = true;
			}
		}

		// contacts solid (bulldozer or tree)
		if (otherSprite instanceof Solid) {
			setX(previousX);
			setY(previousY);
		}
		// contacts hazard (water, racecar, bus or water)
		if (otherSprite instanceof Hazard && !isSafetyOn()) {
			// if lives reaches 1 and loses a life, exits
			if (livesCounter == 1) {
				System.exit(0);
			}

			// loses life
			else if (otherSprite instanceof Water) {
				livesCounter -= 0.5;
				reset();
			} else {
				loseLife();
			}
		}
		
		if(otherSprite instanceof ExtraLife) { livesCounter ++; }
		
	}

	/**
	 * Getter for safetyOn
	 * 
	 * @return The current boolean state of safetyOn
	 */
	public static boolean isSafetyOn() {
		return safetyOn;
	}

	/**
	 * This function is called to render the image of the amount of lives that
	 * player has and also draws the player base on its x and y coordinate
	 */
	public void render() {
		livesCounter = (int) livesCounter;

		for (int i = 0; i < livesCounter; i++) {
			livesImage.drawCentered(LIVES_X + LIVES_TILE * i, LIVES_Y);
		}

		playerImage.drawCentered(getX(), getY());
	}

	/**
	 * This function is called when player loses a life
	 */
	public void loseLife() {
		livesCounter--;
		reset();
	}

	/**
	 * This function is called to reset the coordinate of the Player
	 */
	public void reset() {
		setX(PLAYER_X);
		setY(PLAYER_Y);
	}

	/**
	 * Getter for life count
	 * 
	 * @return The current value of livesCounter
	 */
	public float getLivesCounter() {
		return livesCounter;
	}

}
