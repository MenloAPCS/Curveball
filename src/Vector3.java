
public class Vector3
{
	private double x;
	private double y;
	private double z;
	
	/**
	 * Initialize a new Vector2 object.
	 * 
	 * @param xIn	starting x coordinate
	 * @param yIn	starting y coordinate
	 * @param zIn	starting z coordinate
	 */
	public Vector3(double xIn, double yIn, double zIn)
	{
		x = xIn;
		y = yIn;
		z = zIn;
	}
	
	/**
	 * Sets the x coordinate.
	 * 
	 * @param xIn	new x coordinate
	 */
	public void setX(double xIn)
	{
		x = xIn;
	}
	
	/**
	 * Sets the y coordinate.
	 * 
	 * @param xIn	new y coordinate
	 */
	public void setY(double yIn)
	{
		y = yIn;
	}
	
	/**
	 * Sets the z coordinate.
	 * 
	 * @param xIn	new z coordinate
	 */
	public void setZ(double zIn)
	{
		z = zIn;
	}
	
	/**
	 * Gets the current x coordinate.
	 * 
	 * @return current x coordinate
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Gets the current y coordinate.
	 * 
	 * @return current y coordinate
	 */
	public double getY()
	{
		return y;
	}
	
	/**
	 * Gets the current z coordinate.
	 * 
	 * @return current z coordinate
	 */
	public double getZ()
	{
		return z;
	}
	
	/**
	 * Adds this vector to another and returns the result.
	 * 
	 * @param v		3D vector to add to this vector
	 * 
	 * @return sum of the two vectors (as a vector)
	 */
	public Vector3 add(Vector3 v)
	{
		return new Vector3(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	
	/**
	 * Multiplies this vector with another and returns the result.
	 * 
	 * @param v		2D vector to multiply by this vector
	 * 
	 * @return product of the two vectors (as a vector)
	 */
	public Vector3 multiply(Vector3 v)
	{
		return new Vector3(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	
	/**
	 * Returns a string representation of the vector.
	 * 
	 * @return the object as a string
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
}