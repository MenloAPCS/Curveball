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
	
	public Paddle(Vector3 center, double widthIn, double heightIn, Vector2 upperRightBoundIn, Vector2 lowerLeftBoundIn, Color colorIn, Color colorOutlineIn)
	{
		super(center, widthIn, heightIn);
		upperRightBound = upperRightBoundIn.add(new Vector2((int) -widthIn/2, (int) -heightIn/2));
		lowerLeftBound = lowerLeftBoundIn.add(new Vector2((int) widthIn/2, (int) heightIn/2));
		//lastPos = new
		width = widthIn;
		height = heightIn;
		super.setColor(colorIn);
		colorOutline = colorOutlineIn;
	}
	
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public void minusLives()
	{
		lives--;
	}
	
	public void setCenter(Vector3 center)
	{
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
			Vector2 currentPos = new Vector2((int) center.getX(), (int) center.getY());
			if(lastPos != null)
			{
				velocity = currentPos.add(lastPos.multiply(new Vector2(-1, -1)));
			}
			lastPos = currentPos;
		}
		moveIndex++;
		super.setCenter(center);
	}
	
	public void render(Graphics g, Camera cam)
	{
		super.render(g, cam);
		Vector3 center = super.getCenter();
		Vector3 upperLeft = center.add(new Vector3(-width/2.0, height/2.0, 0));
		Vector3 lowerRight = center.add(new Vector3(width/2.0, -height/2.0, 0));
		Vector2 first = GameObject.translatePoint(upperLeft, cam);
		Vector2 second = GameObject.translatePoint(lowerRight, cam);
		int x = first.getX();
		int y = first.getY();
		int width = second.getX() - x;
		int height = second.getY() - y;
		g.fillRect(x, y, width, height);
		g.setColor(colorOutline);
		g.drawRect(x, y, width, height);
	}
}