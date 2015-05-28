
public class Camera
{
	private Vector3 pos;
	//3D vector for the 3 axes of rotation
	private Vector3 orient;
	
	/**
	 * Initializes a new Camera object.
	 * 
	 * @param posIn		starting 3D position of camera
	 * @param orientIn	starting orientation of camera
	 */
	public Camera(Vector3 posIn, Vector3 orientIn)
	{
		pos = posIn;
		orient = orientIn;
	}
	
	/**
	 * Sets the position of the camera.
	 * 
	 * @param posIn		new 3D position
	 */
	public void setPosition(Vector3 posIn)
	{
		pos = posIn;
	}
	
	/**
	 * Sets the orientation of the camera.
	 * 
	 * @param orientIn	new orientation
	 */
	public void setOrientation(Vector3 orientIn)
	{
		orient = orientIn;
	}
	
	/**
	 * Gets the position of the camera.
	 * 
	 * @return the camera's 3D position
	 */
	public Vector3 getPosition()
	{
		return pos;
	}
	
	/**
	 * Gets the orientation of the camera.
	 * 
	 * @return the camera's orientation
	 */
	public Vector3 getOrientation()
	{
		return orient;
	}
}