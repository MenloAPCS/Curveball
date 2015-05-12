public abstract class GameObject 
{
	private int x;
	private int y;
	private int z;
	
	public GameObject(int xIn, int yIn, int zIn)
	{
		x = xIn;
		y = yIn;
		z = zIn;
	}
	
	public int getXCoord()
	{
		return x;
	}
	
	public int getYCoord()
	{
		return y;
	}
	
	public int getZCoord()
	{
		return z;
	}
	
	
}
