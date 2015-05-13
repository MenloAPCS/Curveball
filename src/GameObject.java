import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject 
{
	private Position3D center;
	private Color color = Color.GREEN;
	
	public GameObject(Position3D centerIn)
	{
		center = centerIn;
	}
	
	public GameObject(Position3D centerIn, Color colorIn)
	{
		center = centerIn;
		color = colorIn;
	}
	
	public void setCenter(Position3D centerIn)
	{
		center = centerIn;
	}
	
	public void setColor(Color colorIn)
	{
		color = colorIn;
	}
	
	public Position3D getCenter()
	{
		return center;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public int getIntDistance(Camera cam)
	{
		return (int) getDistance(cam);
	}
	
	public double getDistance(Camera cam)
	{
		Position3D camPos = cam.getPosition();
		double x = Math.pow(center.getX() - camPos.getX(), 2);
		double y = Math.pow(center.getY() - camPos.getY(), 2);
		double z = Math.pow(center.getZ() - camPos.getZ(), 2);
		return Math.sqrt(x + y + z);
	}
	
	public void render(Graphics g, Camera cam)
	{
		g.setColor(color);
	}
	
	//http://en.wikipedia.org/wiki/3D_projection
	public static Position2D translatePoint(
		Position3D point, Camera camera
	)
	{
		Position3D camPos = camera.getPosition();
		Orientation camOrient = camera.getOrientation();
		
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
		
		return new Position2D((int) bX, (int) bY);
	}
}
