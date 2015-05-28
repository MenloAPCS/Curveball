import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Rectangle
{
	private Vector2 upperRightBound;
	private Vector2 lowerLeftBound;
	
	private Vector2 lastPos;
	private Vector2 velocity;
	private int moveIndex = 0;
	
	private double width;
	private double height;
	
	private Color colorOutline;
	private int lives;
	
	/**
	 * Initializes a new Paddle object.
	 * 
	 * @param center				3D position of Paddle center
	 * @param widthIn				width of paddle
	 * @param heightIn				height of paddle
	 * @param upperRightBoundIn		upper right (far) bound of Paddle
	 * @param lowerLeftBoundIn		lower left (close) bound of Paddle
	 * @param colorIn				starting color of Paddle
	 * @param colorOutlineIn		starting outline of Paddle
	 */
	public Paddle(Vector3 center, double widthIn, double heightIn, Vector2 upperRightBoundIn, Vector2 lowerLeftBoundIn, Color colorIn, Color colorOutlineIn)
	{
		super(center, widthIn, heightIn);
		//update bound with Paddle width & height for easier comparison
		upperRightBound = upperRightBoundIn.add(
			new Vector2((int) -widthIn/2, (int) -heightIn/2)
		);
		lowerLeftBound = lowerLeftBoundIn.add(
			new Vector2((int) widthIn/2, (int) heightIn/2)
		);
		//lastPos = new
		width = widthIn;
		height = heightIn;
		super.setColor(colorIn);
		colorOutline = colorOutlineIn;
	}
	
	/**
	 * Sets the number of lives remaining for the player owning the paddle.
	 * 
	 * @param livesIn	number of lives remaining
	 */
	public void setLives(int livesIn)
	{
		lives = livesIn;
	}
	
	/**
	 * Gets the current velocity of the Paddle
	 * 
	 * @return 3D vector of current Paddle velocity
	 */
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	/**
	 * Gets the width of the Paddle
	 * 
	 * @return Paddle width
	 */
	public double getWidth()
	{
		return width;
	}
	
	/**
	 * Gets the height of the Paddle
	 * 
	 * @return Paddle height
	 */
	public double getHeight()
	{
		return height;
	}
	
	/**
	 * Gets the number of lives remaining on the Paddle
	 * 
	 * @return number of lives remaining
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * Sets the center of the Paddle
	 * 
	 * @param center	3D position of new center
	 */
	public void setCenter(Vector3 center)
	{
		//keeps Paddle inside the bounds
		if(center.getX() > upperRightBound.getX())
		{
			center.setX(upperRightBound.getX());
		}
		if(center.getY() > upperRightBound.getY())
		{
			center.setY(upperRightBound.getY());
		}
		if(center.getX() < lowerLeftBound.getX())
		{
			center.setX(lowerLeftBound.getX());
		}
		if(center.getY() < lowerLeftBound.getY())
		{
			center.setY(lowerLeftBound.getY());
		}
		
		if(moveIndex % 5 == 0)
		{
			//calculates the velocity vector based on position every 5 times
			Vector2 currentPos =
				new Vector2((int) center.getX(), (int) center.getY());
			if(lastPos != null)
			{
				velocity = currentPos.add(lastPos.multiply(new Vector2(-1, -1)));
			}
			lastPos = currentPos;
		}
		moveIndex++;
		super.setCenter(center);
	}
	
	/**
	 * Renders the Paddle.
	 * 
	 * @param g		Graphics object to draw on canvas
	 * @param cam	Camera object to translate points appropriately
	 */
	public void render(Graphics g, Camera cam)
	{
		super.render(g, cam);
		Vector3 center = super.getCenter();
		Vector3 upperLeft = center.add(
				new Vector3(-width/2.0, height/2.0, 0)
		);
		Vector3 lowerRight = center.add(
				new Vector3(width/2.0, -height/2.0, 0)
		);
		
		//upperLeft and lowerRight in 2D space
		Vector2 first = GameObject.translatePoint(upperLeft, cam);
		Vector2 second = GameObject.translatePoint(lowerRight, cam);
		
		//get x and y for fill/drawRect
		int x = first.getX();
		int y = first.getY();
		int width = second.getX() - x;
		int height = second.getY() - y;
		g.fillRect(x, y, width, height);
		
		//draw outline of rectangle after filling rectangle
		g.setColor(colorOutline);
		g.drawRect(x, y, width, height);
	}
}