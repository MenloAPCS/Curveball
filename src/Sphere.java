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
		System.out.println();
		Position3D center = super.getCenter();
		Position3D camPos = cam.getPosition();
		double distance = super.getDistance(cam);
		double horizDistance = Math.sqrt(
			Math.pow(center.getZ()-camPos.getZ(), 2) + 
			Math.pow(center.getX()-camPos.getX(), 2)
		);
		System.out.println("Distance:" + distance + " " + horizDistance);
		//will need to also adjust by x/z Angle
		double yAngleSin = (camPos.getY() - center.getY()) / distance;
		double yAngleCos = horizDistance / distance;
		double zAngleSin = (camPos.getZ() - center.getZ()) / horizDistance;
		double zAngleCos = (camPos.getX() - center.getX()) / horizDistance;
		System.out.println(yAngleSin + " " + yAngleCos + " " + zAngleSin + " " + zAngleCos);
		double height = radius*yAngleCos;
		double width = radius*yAngleSin;
		double x = width*zAngleSin;
		double z = width*zAngleCos;
		Position3D top = new Position3D(
			center.getX() + x, 
			center.getY() + height,
			center.getZ() + z
		);
		Position3D topRev = new Position3D(
			center.getX() - x, 
			center.getY() + height,
			center.getZ() - z
		);
		System.out.println("Center: " + center + ", top: " + top);
		
		Position2D center2D = translatePoint(center, cam);
		Position2D top2D = translatePoint(top, cam);
		Position2D topRev2D = translatePoint(topRev, cam);
		
		int result = (int)distanceBetweenPoints(top2D, center2D);
		int resultRev = (int) distanceBetweenPoints(topRev2D, center2D);
		return Math.max(result, resultRev);
	}
}
