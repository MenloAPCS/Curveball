import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Workspace3D
{
	private Camera camera;
	private ArrayList<GameObject> objects;
	
	public Workspace3D()
	{
		camera = new Camera(new Position3D(0, 0, 0), new Orientation(0, 0, 0));
		objects = new ArrayList<GameObject>();
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public void addObject(GameObject obj)
	{
		objects.add(obj);
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
}