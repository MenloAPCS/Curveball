
public class Sphere extends GameObject
{
	private int radius;
	
	public Sphere(int rIn, int xIn, int yIn, int zIn)
	{
		super(xIn, yIn, zIn);
		radius = rIn;
	}
	
	public int getRadius()
	{
		return radius;
	}
}
