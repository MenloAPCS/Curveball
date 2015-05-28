import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject
{
	private Vector3 center;
	private Color color = Color.GREEN;
	
	/**
	 * Initializes a new GameObject.
	 * 
	 * @param centerIn	3D position of the center of the object
	 */
	public GameObject(Vector3 centerIn)
	{
		center = centerIn;
	}
	
	/**
	 * Initializes a new GameObject.
	 * 
	 * @param centerIn	3D position of the center of the object
	 * @param colorIn	initial color of the object
	 */
	public GameObject(Vector3 centerIn, Color colorIn)
	{
		center = centerIn;
		color = colorIn;
	}
	
	/**
	 * Sets the center of the GameObject.
	 * 
	 * @param centerIn	3D position of the new center
	 */
	public void setCenter(Vector3 centerIn)
	{
		center = centerIn;
	}
	
	/**
	 * Sets the color of the GameObject.
	 * 
	 * @param colorIn	new GameObject color
	 */
	public void setColor(Color colorIn)
	{
		color = colorIn;
	}
	
	/**
	 * Gets the current center of the GameObject.
	 * 
	 * @return 3D position of the center
	 */
	public Vector3 getCenter()
	{
		return center;
	}
	
	/**
	 * Gets the current color of the GameObject.
	 * 
	 * @return current GameObject color
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Gets the distance between the GameObject and the given Camera.
	 * 
	 * @param cam	Camera object from which to measure distance
	 * 
	 * @return double of the distance between the camera and the GameObject
	 */
	public double getDistance(Camera cam)
	{
		Vector3 camPos = cam.getPosition();
		return distanceBetweenPoints(center, camPos);
	}
	
	/**
	 * Renders the GameObject.
	 * 
	 * @param g		Graphics object to draw on canvas
	 * @param cam	Camera object to translate points appropriately
	 */
	public void render(Graphics g, Camera cam)
	{
		g.setColor(color);
	}
	
	/**
	 * Projects a point in 3D space to 2D space.  The math is taken from the
	 * Wikipedia article on 3D projection, which you can find below:
	 * http://en.wikipedia.org/wiki/3D_projection
	 * 
	 * @param point		3D position of point to project
	 * @param camera	Camera to project on
	 * 
	 * @return 2D position of projected point
	 */
	public static Vector2 translatePoint(
		Vector3 point, Camera camera
	)
	{
		Vector3 camPos = camera.getPosition();
		Vector3 camOrient = camera.getOrientation();
		
		double x = point.getX() - camPos.getX();
		double y = point.getY() - camPos.getY();
		double z = point.getZ() - camPos.getZ();
		
		//to make y=20 higher instead of lower
		y *= -1;
		
		int eX = -Curveball.WINDOW_SIZE / 2;
		int eY = -Curveball.WINDOW_SIZE / 2 - Curveball.HEADER_Y;
		int eZ = Curveball.WINDOW_SIZE;
		
		double cX = Math.cos(camOrient.getX());
		double cY = Math.cos(camOrient.getY());
		double cZ = Math.cos(camOrient.getZ());
		
		double sX = Math.sin(camOrient.getX());
		double sY = Math.sin(camOrient.getY());
		double sZ = Math.sin(camOrient.getZ());
		
		double dX = cY*(sZ*y + cZ*x) - sY*z;
		double dY = sX*(cY*z + sY*(sZ*y + cZ*x)) + cX*(cZ*y - sZ*x);
		double dZ = cX*(cY*z + sY*(sZ*y + cZ*x)) - sX*(cZ*y - sZ*x);
		
		double bX = (eZ/dZ)*dX - eX;
		double bY = (eZ/dZ)*dY - eY;
		
		return new Vector2((int) bX, (int) bY);
	}
	
	/**
	 * Returns the distance between two points in 3D space.
	 * 
	 * @param p1	3D position of first point
	 * @param p2	3D position of second point
	 * 
	 * @return distance between points
	 */
	public static double distanceBetweenPoints(Vector3 p1, Vector3 p2)
	{
		double x = p1.getX() - p2.getX();
		double y = p1.getY() - p2.getY();
		double z = p1.getZ() - p2.getZ();
		//pythagorean theorem
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Returns the distance between two points in 2D space.
	 * 
	 * @param p1	2D position of first point
	 * @param p2	2D position of second point
	 * 
	 * @return distance between points
	 */
	public static double distanceBetweenPoints(Vector2 p1, Vector2 p2)
	{
		double x = p1.getX() - p2.getX();
		double y = p1.getY() - p2.getY();
		//pythagorean theorem
		return Math.sqrt(x*x + y*y);
	}
}
