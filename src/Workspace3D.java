import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Workspace3D
{
	private Camera camera;
	private ArrayList<GameObject> objects;
	private ArrayList<Ball> balls;
	
	public Workspace3D()
	{
		camera = new Camera(new Vector3(0, 0, -100), new Vector3(0, 0, 0));
		objects = new ArrayList<GameObject>();
		balls = new ArrayList<Ball>();
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public void addObject(GameObject obj)
	{
		objects.add(obj);
	}
	
	public void addBall(Ball obj)
	{
		objects.add(obj);
		balls.add(obj);
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, Curveball.HEADER_Y, Curveball.WINDOW_SIZE, Curveball.WINDOW_SIZE);
		for(GameObject obj: objects)
		{
			obj.render(g, camera);
		}
	}
	
	public void step()
	{
		for(Ball obj: balls)
		{
			obj.step();
			camera.setPosition(camera.getPosition().add(obj.getVelocity()));
		}
	}
}