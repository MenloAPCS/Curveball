import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject
{
	private Vector3 center;
	private Color color = Color.GREEN;
	
	public GameObject(Vector3 centerIn)
	{
		center = centerIn;
	}
	
	public GameObject(Vector3 centerIn, Color colorIn)
	{
		center = centerIn;
		color = colorIn;
	}
	
	public void setCenter(Vector3 centerIn)
	{
		center = centerIn;
	}
	
	public void setColor(Color colorIn)
	{
		color = colorIn;
	}
	
	public Vector3 getCenter()
	{
		return center;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public int compareTo(GameObject obj, Camera cam)
	{
		double objDist = obj.getDistance(cam);
		double thisDist = getDistance(cam);
		if(thisDist - objDist > 0)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
	public int getIntDistance(Camera cam)
	{
		return (int) getDistance(cam);
	}
	
	public double getDistance(Camera cam)
	{
		Vector3 camPos = cam.getPosition();
		return distanceBetweenPoints(center, camPos);
	}
	
	public void render(Graphics g, Camera cam)
	{
		g.setColor(color);
	}
	
	//http://en.wikipedia.org/wiki/3D_projection
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
	
	public static double distanceBetweenPoints(Vector3 p1, Vector3 p2)
	{
		double x = p1.getX() - p2.getX();
		double y = p1.getY() - p2.getY();
		double z = p1.getZ() - p2.getZ();
		//System.out.println("x: " + x + ", y: " + y + ", z: " + z);
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public static double distanceBetweenPoints(Vector2 p1, Vector2 p2)
	{
		double x = p1.getX() - p2.getX();
		double y = p1.getY() - p2.getY();
		//System.out.println("x: " + x + ", y: " + y);
		return Math.sqrt(x*x + y*y);
	}
}
