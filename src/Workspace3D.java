import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Workspace3D
{
	private Camera camera;
	private ArrayList<GameObject> objects;
	private Ball ball;
	private Cube trace;
	
	public Workspace3D(Ball ballIn)
	{
		camera = new Camera(new Vector3(0, 0, -100), new Vector3(0, 0, 0));
		objects = new ArrayList<GameObject>();
		objects.add(ballIn);
		ball = ballIn;
		Vector3 ballLowerLeft = ball.getLowerLeft();
		Vector3 cubeLowerLeft = new Vector3(ballLowerLeft.getX(), ballLowerLeft.getY(), ball.getCenter().getZ());
		Vector3 ballUpperRight = ball.getUpperRight();
		Vector3 cubeUpperRight = new Vector3(ballUpperRight.getX(), ballUpperRight.getY(), ball.getCenter().getZ());
		System.out.println(cubeLowerLeft + " " + cubeUpperRight);
		trace = new Cube(cubeLowerLeft, cubeUpperRight, Color.WHITE);
		objects.add(trace);
		System.out.println(trace);
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public void addObject(GameObject obj)
	{
		objects.add(0, obj);
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
		ball.step();
		//camera.setPosition(camera.getPosition().add(ball.getVelocity()));
		
		Vector3 cubeLowerLeft = trace.getLowerLeft();
		Vector3 cubeUpperRight = trace.getUpperRight();
		cubeLowerLeft = new Vector3(cubeLowerLeft.getX(), cubeLowerLeft.getY(), ball.getCenter().getZ());
		cubeUpperRight = new Vector3(cubeUpperRight.getX(), cubeUpperRight.getY(), ball.getCenter().getZ() + 1);
		trace.setLowerLeft(cubeLowerLeft);
		trace.setUpperRight(cubeUpperRight);
	}
}