import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * This class is to read the csv files and load them to a single array list
 * 
 * @author Marvel Jonathan
 */
public class Loader {
	// non moveable data has 3 values
	private static final int NON_MOVEABLE = 3;

	// distance between each holes is 4*48 pixels
	private static final float HOLES_SEP_DIST = 4 * World.TILE_SIZE;

	// first X-coordinate
	private static final float FIRST_HOLE_X = 120;

	// y-coordinate for holes
	private static final float HOLE_Y = 48;

	// number of holes
	private static final float NUMB_HOLES = 5;

	// boolean for if the holes is filled
	private static boolean checkMark = false;

	/**
	 * This function is called when to load the sprites base on the level number
	 * corresponding to the file
	 * 
	 * @param currentLevel The current level of the Game
	 * @return sprites The array list of all sprites
	 */
	public static ArrayList<Sprite> loadLevel(int currentLevel) {
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites = loadSprites(currentLevel);
		return sprites;
	}

	/**
	 * This function is called to return an array list sorted in a certain order
	 * 
	 * @param currentLevel The current level of the Game
	 * @return spriteList The array list of all sprites
	 */
	private static ArrayList<Sprite> loadSprites(int currentLevel) {
		// initializing the different type of arrays
		ArrayList<Sprite> spriteList = new ArrayList<>();
		ArrayList<Sprite> extraLifeRideableList = new ArrayList<>();
		ArrayList<Sprite> rideableList = new ArrayList<>();
		ArrayList<Sprite> nonRideable = new ArrayList<>();

		// temporary sprite
		Sprite tempSprite;

		// level file to read from assets
		String levelFile = "assets/levels/" + currentLevel + ".lvl";

		// line for readline for the csv
		String line;

		// data array and its different components
		String[] data = new String[4];
		String spriteType;
		int xPosition;
		int yPosition;
		int direction = 1;

		// read file and instantiate all sprites stored in file
		try (BufferedReader readSprites = new BufferedReader(new FileReader(levelFile))) {
			while ((line = readSprites.readLine()) != null) {
				data = line.split(",");
				spriteType = data[0];
				xPosition = Integer.parseInt(data[1]);
				yPosition = Integer.parseInt(data[2]);

				// check if its not a moveable since moveable has more than 3 values
				if (data.length != NON_MOVEABLE) {
					if (Boolean.parseBoolean(data[3]) == false) {
						direction = -1;
					}
					if (Boolean.parseBoolean(data[3]) == true) {
						direction = 1;
					}
				}

				tempSprite = createSprite(spriteType, xPosition, yPosition, direction);

				// inserts temporary sprite to its respective array list
				if (spriteType.equals("log") || spriteType.equals("longLog")) {
					extraLifeRideableList.add(tempSprite);
				} else if (spriteType.equals("turtle")) {
					rideableList.add(tempSprite);
				} else {
					nonRideable.add(tempSprite);
				}
			}

			// Adding player/frog to sprites
			spriteList.add(new Player());
			spriteList.addAll(extraLifeRideableList);
			spriteList.addAll(rideableList);
			spriteList.addAll(nonRideable);

			// number of logs
			World.setNumberOfLogs(extraLifeRideableList.size());

			// insert the holes in the sprite list
			for (int i = 0; i < NUMB_HOLES; i++) {
				spriteList.add(new Holes(FIRST_HOLE_X + HOLES_SEP_DIST * i, HOLE_Y, checkMark));
			}

			return spriteList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function is called to create a single sprite base on the type of sprite
	 * it is
	 * 
	 * @param type      String of the type of Sprite
	 * @param x         Starting x-coordinate
	 * @param y         Starting y-coordinate
	 * @param direction Starting direction
	 * @return The sprite base on type of sprite
	 */
	private static Sprite createSprite(String type, float x, float y, int direction) {
		switch (type) {
		case "water":
			return new Water(x, y);
		case "grass":
			return new Grass(x, y);
		case "tree":
			return new Tree(x, y);
		case "bus":
			return new Bus(x, y, direction);
		case "bulldozer":
			return new Bulldozer(x, y, direction);
		case "log":
			return new Log(x, y, direction);
		case "longLog":
			return new LongLog(x, y, direction);
		case "bike":
			return new Bike(x, y, direction);
		case "racecar":
			return new Racecar(x, y, direction);
		case "turtle":
			return new Turtles(x, y, direction);
		}
		return null;
	}

}
