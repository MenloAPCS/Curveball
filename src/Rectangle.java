import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends GameObject
{
	private Vector3 p1;
	private Vector3 p2;
	private Vector3 p3;
	private Vector3 p4;
	
	public Rectangle(Vector3 p1In, Vector3 p2In, Vector3 p3In, Vector3 p4In)
	{
		super(getCenter(p1In, p2In, p3In, p4In));
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 = p4In;
	}
	
	public Rectangle(Vector3 p1In, Vector3 p2In, Vector3 p3In, Vector3 p4In, Color color)
	{
		super(getCenter(p1In, p2In, p3In, p4In), color);
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 = p4In;
	}
	
	private static Vector3 getCenter(Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4)
	{
		double x = (p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4;
		double y = (p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4;
		double z = (p1.getZ() + p2.getZ() + p3.getZ() + p4.getZ()) / 4;
		return new Vector3(x, y, z);
	}

	public void render(Graphics g, Camera cam)
	{
		super.render(g, cam);
		Vector2 d1 = translatePoint(p1, cam);
		Vector2 d2 = translatePoint(p2, cam);
		Vector2 d3 = translatePoint(p3, cam);
		Vector2 d4 = translatePoint(p4, cam);
		
		g.drawLine(d1.getX(), d1.getY(), d2.getX(), d2.getY());
		g.drawLine(d2.getX(), d2.getY(), d3.getX(), d3.getY());
		g.drawLine(d3.getX(), d3.getY(), d4.getX(), d4.getY());
		g.drawLine(d4.getX(), d4.getY(), d1.getX(), d1.getY());
	}
}