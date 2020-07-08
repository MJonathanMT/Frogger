import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * World class that gets repeated by slick game
 * 
 * @author Marvel Jonathan
 */
public class World {
	// tile size, in pixels
	public static final float TILE_SIZE = 48f;

	// current level
	private int currentLevel = 0;
	private boolean winCondition = false;

	private int pointsCounter = 0;
	// array list of sprite
	private ArrayList<Sprite> updateSprites = new ArrayList<>();
	private ArrayList<Sprite> renderSprites;
	// the number of sprites
	private int sizeArrayList;

	// variables for extra life (EL)
	private static final int MAX_EXTRALIFE_DELAY = 35;
	private static final int MIN_EXTRALIFE_DELAY = 25;
	private static final int MAX_EXTRALIFE_TIME = 1200;
	private int extraLifeDelay;
	private static int numberOfLogs;
	private int logLocation;
	private int extraLifeTimer;
	private boolean extraLifeExist;
	private Sprite extraLifeLog;

	/**
	 * This constructs the whole sprite list
	 * 
	 * @throws SlickException
	 */
	public World() throws SlickException {
		// Perform initialization logic
		loader(currentLevel);
	}

	/**
	 * Updates the sprites base on input and delta
	 * 
	 * @param input Input key pressed by user
	 * @param delta Amount of milliseconds passed since last update
	 */
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
		for (Sprite sprite : updateSprites) {
			sprite.update(input, delta);
			if (sprite instanceof Holes) {
				if (((Holes) sprite).getGoalEntered()) {
					pointsCounter++;
				}
			}
		}
		// point counter = 5 means win condition = true
		if (pointsCounter == 5) {
			winCondition = true;
		} else {
			pointsCounter = 0;
			winCondition = false;
		}
		// checking if any sprite intersects
		for (Sprite sprite : updateSprites) {
			for (Sprite otherSprite : updateSprites) {
				if (sprite.getBb().intersects(otherSprite.getBb())) {
					sprite.contactSprite(otherSprite);
				}
			}
		}
		// go to the next level if win condition = true
		if (winCondition == true) {
			if (currentLevel == 1) {
				System.exit(0);
			}
			currentLevel++;
			loader(currentLevel);
			winCondition = false;
			pointsCounter = 0;
		}
	}

	/**
	 * renders the image all sprites
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		// Draw all of the sprites in the game
		for (Sprite sprite : renderSprites) {
			sprite.render();
		}
	}

	// function to load all the sprites
	private void loader(int level) {
		updateSprites = Loader.loadLevel(level);
		extraLifeDelay = randomWithRange(MIN_EXTRALIFE_DELAY, MAX_EXTRALIFE_DELAY);
		logLocation = randomWithRange(1, numberOfLogs);
		extraLifeLog = updateSprites.get(logLocation);
		updateSprites.add(1, new ExtraLife(extraLifeLog.getX(), extraLifeLog.getY()));
		renderSprites = new ArrayList<>(updateSprites);
		Collections.reverse(renderSprites);
		sizeArrayList = updateSprites.size();
	}

	// number of logs in the level
	public static void setNumberOfLogs(int numbOfLogs) {
		numberOfLogs = numbOfLogs;
	}

	// random range generator
	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}
