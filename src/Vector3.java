
public class Vector3
{
	private double x;
	private double y;
	private double z;
	
	public Vector3(double xIn, double yIn, double zIn)
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
	
	public Vector3 add(Vector3 v)
	{
		return new Vector3(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	
	public Vector3 multiply(Vector3 v)
	{
		return new Vector3(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
}