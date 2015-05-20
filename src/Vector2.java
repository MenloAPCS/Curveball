
public class Vector2
{
	private int x;
	private int y;
	
	public Vector2(int xIn, int yIn)
	{
		x = xIn;
		y = yIn;
	}
	
	public void setX(int xIn)
	{
		x = xIn;
	}
	
	public void setY(int yIn)
	{
		y = yIn;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Vector2 add(Vector2 v)
	{
		return new Vector2(x + v.getX(), y + v.getY());
	}
	
	public Vector2 multiply(Vector2 v)
	{
		return new Vector2(x * v.getX(), y * v.getY());
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}