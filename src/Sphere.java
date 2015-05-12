
public class Sphere extends GameObject
{
	private int radius;
	
	public Sphere(int r, int xIn, int yIn, int zIn)
	{
		super(xIn, yIn, zIn);
		radius = r;
	}
	
	public int getRadius()
	{
		return radius;
	}
}
