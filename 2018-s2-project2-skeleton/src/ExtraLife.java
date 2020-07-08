import org.newdawn.slick.Input;

public class ExtraLife extends Sprite {

	// image for Extra Life
	private static final String EXTRA_LIFE_IMAGE = "assets/extralife.png";

	// counter for movement
	private int counter = 0;
	private static final int TIMER = 2000;
	private int direction = 1;
	private int lengthLog;
	private int position;

	private Sprite currentLog;

	// constructor
	public ExtraLife(float x, float y) {
		super(EXTRA_LIFE_IMAGE, x, y);
	}

	// update movement for extra life
	public void update(Input input, int delta) {
		moves(input, delta);
	}

	// called when one sprite makes contact with another
	public void contactSprite(Sprite otherSprite) {
		currentLog = otherSprite;
		if (otherSprite instanceof LongLog) {
			lengthLog = 5;
			position = (int) (lengthLog / 2);

		}
		if (otherSprite instanceof Log) {
			lengthLog = 3;
			position = (int) (lengthLog / 2);
		}
	}

	public void moves(Input input, int delta) {
		counter += delta;
		if (counter >= TIMER) {
			counter -= TIMER;
			setX(currentLog.getX() + World.TILE_SIZE * direction * (lengthLog - position));

			position += direction;
		}
		if (position == lengthLog - 1 || position == 0) {
			direction *= -1;
		}
		// sets new bounding box
		setBb(getX(), getY());
	}

}
