/**
 * This class implements tree as a solid sprite
 * 
 * @author Marvel Jonathan
 */
public class Tree extends Solid {
	// image for tree
	private static String TREE_IMAGE = "assets/tree.png";

	/**
	 * This constructs a tree with an x and y coordinate with a image TREE_IMAGE
	 * 
	 * @param x X-coordinate of the Sprite
	 * @param y Y-coordinate of the Sprite
	 */
	public Tree(float x, float y) {
		super(TREE_IMAGE, x, y);
	}
}