import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Rectangle
{
	private Vector2 upperRightBound;
	private Vector2 lowerLeftBound;
	
	private double width;
	private double height;
	
	public Paddle(Vector3 center, double widthIn, double heightIn, Vector2 upperRightBoundIn, Vector2 lowerLeftBoundIn, Color colorIn)
	{
		super(center, widthIn, heightIn);
		upperRightBound = upperRightBoundIn;
		lowerLeftBound = lowerLeftBoundIn;
		width = widthIn;
		height = heightIn;
		super.setColor(colorIn);
	}
	
	public void setCenter(Vector3 center)
	{
		if(center.getX() > upperRightBound.getX())
		{
			center.setX(upperRightBound.getX() - width / 2.0);
		}
		if(center.getY() > upperRightBound.getY())
		{
			center.setY(upperRightBound.getY() - height / 2.0);
		}
		if(center.getX() < lowerLeftBound.getX())
		{
			center.setX(lowerLeftBound.getX() + width / 2.0);
		}
		if(center.getY() < lowerLeftBound.getY())
		{
			center.setY(lowerLeftBound.getY() + height / 2.0);
		}
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
	}
}
