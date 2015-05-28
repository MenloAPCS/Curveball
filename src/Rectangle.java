import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends GameObject
{
	private Vector3 p1;
	private Vector3 p2;
	private Vector3 p3;
	private Vector3 p4;
	
	/**
	 * Initialize a new Rectangle object.
	 * 
	 * @param p1In	3D position of first point
	 * @param p2In	3D position of second point
	 * @param p3In	3D position of third point
	 * @param p4In	3D position of fourth point
	 */
	public Rectangle(Vector3 p1In, Vector3 p2In, Vector3 p3In, Vector3 p4In)
	{
		super(getCenter(p1In, p2In, p3In, p4In));
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 = p4In;
	}
	
	/**
	 * Initialize a new Rectangle object.
	 * 
	 * @param p1In		3D position of first point
	 * @param p2In		3D position of second point
	 * @param p3In		3D position of third point
	 * @param p4In		3D position of fourth point
	 * @param color		starting color of Rectangle
	 */
	public Rectangle(Vector3 p1In, Vector3 p2In, Vector3 p3In, Vector3 p4In, Color color)
	{
		super(getCenter(p1In, p2In, p3In, p4In), color);
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 = p4In;
	}
	
	/**
	 * Initialize a new Rectangle object.
	 * 
	 * @param center	3D position of center
	 * @param width		width of rectangle
	 * @param height	height of rectangle
	 */
	public Rectangle(Vector3 center, double width, double height)
	{
		super(center);
		p1 = center.add(new Vector3(-width/2.0, height/2.0, 0));
		p2 = center.add(new Vector3(width/2.0, height/2.0, 0));
		p3 = center.add(new Vector3(width/2.0, -height/2.0, 0));
		p4 = center.add(new Vector3(-width/2.0, -height/2.0, 0));
	}
	
	/**
	 * Sets the center of the Rectangle.
	 * 
	 * @param center	3D position of the new center
	 */
	public void setCenter(Vector3 center)
	{
		super.setCenter(center);
		Vector3 centerOld = getCenter(p1, p2, p3, p4);
		Vector3 diff = center.add(centerOld.multiply(new Vector3(-1.0, -1.0, -1.0)));
		p1 = p1.add(diff);
		p2 = p2.add(diff);
		p3 = p3.add(diff);
		p4 = p4.add(diff);
	}
	
	/**
	 * Gets the center of four points in 3D space.
	 * 
	 * @param p1	3D position of first point
	 * @param p2	3D position of second point
	 * @param p3	3D position of third point
	 * @param p4	3D position of fourth point
	 * @return
	 */
	private static Vector3 getCenter(Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4)
	{
		double x = (p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4;
		double y = (p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4;
		double z = (p1.getZ() + p2.getZ() + p3.getZ() + p4.getZ()) / 4;
		return new Vector3(x, y, z);
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
		//translate the four 3D points of the Rectangle to 2D space
		Vector2 d1 = translatePoint(p1, cam);
		Vector2 d2 = translatePoint(p2, cam);
		Vector2 d3 = translatePoint(p3, cam);
		Vector2 d4 = translatePoint(p4, cam);
		
		//draw lines between the 2D points
		g.drawLine(d1.getX(), d1.getY(), d2.getX(), d2.getY());
		g.drawLine(d2.getX(), d2.getY(), d3.getX(), d3.getY());
		g.drawLine(d3.getX(), d3.getY(), d4.getX(), d4.getY());
		g.drawLine(d4.getX(), d4.getY(), d1.getX(), d1.getY());
	}
}