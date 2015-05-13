import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends GameObject
{
	private Position3D p1;
	private Position3D p2;
	private Position3D p3;
	private Position3D p4;
	
	public Rectangle(Position3D p1In, Position3D p2In, Position3D p3In, Position3D p4In)
	{
		super(getCenter(p1In, p2In, p3In, p4In));
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 = p4In;
	}
	
	public Rectangle(Position3D p1In, Position3D p2In, Position3D p3In, Position3D p4In, Color color)
	{
		super(getCenter(p1In, p2In, p3In, p4In), color);
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 = p4In;
	}
	
	private static Position3D getCenter(Position3D p1, Position3D p2, Position3D p3, Position3D p4)
	{
		double x = (p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4;
		double y = (p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4;
		double z = (p1.getZ() + p2.getZ() + p3.getZ() + p4.getZ()) / 4;
		return new Position3D(x, y, z);
	}

	public void render(Graphics g, Camera cam)
	{
		super.render(g, cam);
		Position2D d1 = translatePoint(p1, cam);
		Position2D d2 = translatePoint(p2, cam);
		Position2D d3 = translatePoint(p3, cam);
		Position2D d4 = translatePoint(p4, cam);
		
		g.drawLine(d1.getX(), d1.getY(), d2.getX(), d2.getY());
		g.drawLine(d2.getX(), d2.getY(), d3.getX(), d3.getY());
		g.drawLine(d3.getX(), d3.getY(), d4.getX(), d4.getY());
		g.drawLine(d4.getX(), d4.getY(), d1.getX(), d1.getY());
	}
}