
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
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}