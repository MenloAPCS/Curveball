
public class Position3D
{
	private double x;
	private double y;
	private double z;
	
	public Position3D(double xIn, double yIn, double zIn)
	{
		x = xIn;
		y = yIn;
		z = zIn;
	}
	
	public void setX(double xIn)
	{
		x = xIn;
	}
	
	public void setY(double yIn)
	{
		y = yIn;
	}
	
	public void setZ(double zIn)
	{
		z = zIn;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
}