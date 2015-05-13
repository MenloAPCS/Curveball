import java.awt.Color;
import java.awt.Graphics;

public class Sphere extends GameObject
{
	public static final int NUM_CROSS_SECTIONS = 4;
	
	private double radius;
	
	public Sphere(int radiusIn, Position3D center)
	{
		super(center);
		radius = radiusIn;
	}
	
	public Sphere(int radiusIn, Position3D center, Color color)
	{
		super(center, color);
		radius = radiusIn;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public void render(Graphics g, Camera cam)
	{
		super.render(g, cam);
		int radius2D = get2DRadius(cam);
		Position2D center2D = translatePoint(super.getCenter(), cam);
		g.fillOval(center2D.getX() - radius2D, center2D.getY() - radius2D, 2*radius2D, 2*radius2D);
	}
	
	private int get2DRadius(Camera cam)
	{
		double distance = super.getDistance(cam);
		Position3D center = super.getCenter();
		Position3D camPos = cam.getPosition();
		//will need to also adjust by x/z Angle
		double yAngle = Math.asin(
			Math.abs(center.getY() - camPos.getY()) / distance
		);
		Position3D top = new Position3D(
			center.getX() + radius*Math.sin(yAngle), 
			center.getY() + radius*Math.cos(yAngle),
			center.getZ() + radius*Math.sin(yAngle)
		);
		
		Position2D center2D = translatePoint(center, cam);
		Position2D top2D = translatePoint(top, cam);
		
		return Math.abs(top2D.getY() - center2D.getY());
	}
}
