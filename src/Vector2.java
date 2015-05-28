
public class Vector2
{
	private int x;
	private int y;
	
	/**
	 * Initialize a new Vector2 object.
	 * 
	 * @param xIn	starting x coordinate
	 * @param yIn	starting y coordinate
	 */
	public Vector2(int xIn, int yIn)
	{
		x = xIn;
		y = yIn;
	}
	
	/**
	 * Sets the x coordinate.
	 * 
	 * @param xIn	new x coordinate
	 */
	public void setX(int xIn)
	{
		x = xIn;
	}
	
	/**
	 * Sets the y coordinate.
	 * 
	 * @param yIn	new y coordinate
	 */
	public void setY(int yIn)
	{
		y = yIn;
	}
	
	/**
	 * Gets the current x coordinate.
	 * 
	 * @return current x coordinate
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gets the current y coordinate.
	 * 
	 * @return current y coordinate
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Adds this vector to another and returns the result.
	 * 
	 * @param v		2D vector to add to this vector
	 * 
	 * @return sum of the two vectors (as a vector)
	 */
	public Vector2 add(Vector2 v)
	{
		return new Vector2(x + v.getX(), y + v.getY());
	}
	
	/**
	 * Multiplies this vector with another and returns the result.
	 * 
	 * @param v		2D vector to multiply by this vector
	 * 
	 * @return product of the two vectors (as a vector)
	 */
	public Vector2 multiply(Vector2 v)
	{
		return new Vector2(x * v.getX(), y * v.getY());
	}
	
	/**
	 * Returns a string representation of the vector.
	 * 
	 * @return the object as a string
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}