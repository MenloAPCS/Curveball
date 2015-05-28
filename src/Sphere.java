import java.awt.Color;
import java.awt.Graphics;

public class Sphere extends GameObject
{
	public static final int NUM_CROSS_SECTIONS = 4;
	
	private double radius;
	
	/**
	 * Initialize a new Sphere object.
	 * 
	 * @param radiusIn	radius of the Sphere
	 * @param center	3D position of the Sphere's center
	 */
	public Sphere(double radiusIn, Vector3 center)
	{
		super(center);
		radius = radiusIn;
	}
	
	/**
	 * Initialize a new Sphere object.
	 * 
	 * @param radiusIn	radius of the Sphere
	 * @param center	3D position of the Sphere's center
	 * @param color		starting color of the Sphere
	 */
	public Sphere(double radiusIn, Vector3 center, Color color)
	{
		super(center, color);
		radius = radiusIn;
	}
	
	/**
	 * Gets the radius of the Sphere.
	 * 
	 * @return Sphere's radius
	 */
	public double getRadius()
	{
		return radius;
	}
	
	/**
	 * Renders the GameObject.
	 * 
	 * @param g		Graphics object to draw on canvas
	 * @param cam	Camera object to translate points appropriately
	 */
	public void render(Graphics g, Camera cam)
	{
		super.render(g, cam);
		int radius2D = get2DRadius(cam);
		Vector2 center2D = translatePoint(super.getCenter(), cam);
		
		//fill oval based on coordinates of center and 2D radius of sphere
		g.fillOval(
			center2D.getX() - radius2D,
			center2D.getY() - radius2D,
			2*radius2D,
			2*radius2D
		);
	}
	
	/**
	 * Gets the 2D radius of the Sphere based on the Camera.
	 * 
	 * @param cam	Camera the Sphere will be rendered for
	 * 
	 * @return 2D radius of Sphere
	 */
	private int get2DRadius(Camera cam)
	{
		Vector3 center = super.getCenter();
		Vector3 camPos = cam.getPosition();
		double distance = super.getDistance(cam);
		double horizDistance = Math.sqrt(
			Math.pow(center.getZ()-camPos.getZ(), 2) + 
			Math.pow(center.getX()-camPos.getX(), 2)
		);
		
		//simplify calculations with intermediate variables
		double yAngleSin = (camPos.getY() - center.getY()) / distance;
		double yAngleCos = horizDistance / distance;
		double zAngleSin = (camPos.getZ() - center.getZ()) / horizDistance;
		double zAngleCos = (camPos.getX() - center.getX()) / horizDistance;
		
		double height = radius*yAngleCos;
		double width = radius*yAngleSin;
		double x = width*zAngleSin;
		double z = width*zAngleCos;
		
		//3D positions of possible tops of the Sphere
		Vector3 top = new Vector3(
			center.getX() + x, 
			center.getY() + height,
			center.getZ() + z
		);
		Vector3 topRev = new Vector3(
			center.getX() - x, 
			center.getY() + height,
			center.getZ() - z
		);
		
		//2D positions of possible tops of the Sphere
		Vector2 center2D = translatePoint(center, cam);
		Vector2 top2D = translatePoint(top, cam);
		Vector2 topRev2D = translatePoint(topRev, cam);
		
		//take larger radius of the two
		int result = (int)distanceBetweenPoints(top2D, center2D);
		int resultRev = (int) distanceBetweenPoints(topRev2D, center2D);
		return Math.max(result, resultRev);
	}
}
