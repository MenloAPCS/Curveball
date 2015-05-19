import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Workspace3D
{
	private Camera camera;
	private ArrayList<GameObject> objects;
	private Ball ball;
	private Cube tracer;
	private Paddle paddle;
	
	private boolean isRunning = false;
	
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
		tracer = new Cube(cubeLowerLeft, cubeUpperRight, Color.WHITE);
		objects.add(tracer);
		float rgb = (float) 0.8;
		float alpha = (float) 0.7;
		System.out.println(alpha);
		Color paddleColor = new Color(rgb, rgb, rgb, alpha);
		paddle = new Paddle(new Vector3(0, 0, 50), 32.0, 18.0, new Vector2(70, 50), new Vector2(-70, -50), paddleColor);
		objects.add(paddle);
		System.out.println(tracer);
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public void start()
	{
		ball.setVelocity(new Vector3(0, 0, Curveball.BALL_SPEED));
		isRunning = true;
	}
	
	public void movePaddle(Vector2 mouse)
	{
		//(27, 155) and (774, 155) = 747
		//(27, 155) and (27, 688) = 533
		//System.out.println(mouse);
		mouse = mouse.add(new Vector2(-27, -155));
		//System.out.println(mouse);
		Vector3 paddleCoords = new Vector3(mouse.getX(), mouse.getY(), 50);
		//System.out.println(paddleCoords);
		paddleCoords = paddleCoords.multiply(new Vector3(140.0/747.0, 100.0/533.0, 1.0));
		//System.out.println(paddleCoords);
		paddleCoords = new Vector3(paddleCoords.getX() - 70, 50 - paddleCoords.getY(), paddleCoords.getZ());
		//System.out.println("\n");
		paddle.setCenter(paddleCoords);
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
		stepTracer();
		//camera.setPosition(camera.getPosition().add(new Vector3(.25*ball.getVelocity().getX(), .25*ball.getVelocity().getY(), 0)));
	}
	
	public void stepTracer()
	{
		Vector3 cubeLowerLeft = tracer.getLowerLeft();
		Vector3 cubeUpperRight = tracer.getUpperRight();
		cubeLowerLeft = new Vector3(cubeLowerLeft.getX(), cubeLowerLeft.getY(), ball.getCenter().getZ());
		cubeUpperRight = new Vector3(cubeUpperRight.getX(), cubeUpperRight.getY(), ball.getCenter().getZ());
		tracer.setLowerLeft(cubeLowerLeft);
		tracer.setUpperRight(cubeUpperRight);
	}
}