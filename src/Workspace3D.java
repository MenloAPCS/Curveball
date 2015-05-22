import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Workspace3D
{
	private Camera camera;
	private ArrayList<GameObject> objects;
	private Ball ball;
	private Cube tracer;
	private Paddle playerPaddle;
	private Paddle enemyPaddle;
	
	private boolean isRunning = false;
	
	public Workspace3D(Ball ballIn)
	{
		camera = new Camera(new Vector3(0, 0, -100), new Vector3(0, 0, 0));
		objects = new ArrayList<GameObject>();
		float rgb = (float) 0.8;
		float alpha = (float) 0.7;
		Color paddleColor = new Color(rgb, rgb, rgb, alpha);
		enemyPaddle = new Paddle(new Vector3(0, 0, 500), 32.0, 18.0, new Vector2(70, 50), new Vector2(-70, -50), paddleColor, Color.red);
		objects.add(enemyPaddle);
		objects.add(ballIn);
		ball = ballIn;
		Vector3 ballLowerLeft = ball.getLowerLeft();
		Vector3 cubeLowerLeft = new Vector3(ballLowerLeft.getX(), ballLowerLeft.getY(), ball.getCenter().getZ());
		Vector3 ballUpperRight = ball.getUpperRight();
		Vector3 cubeUpperRight = new Vector3(ballUpperRight.getX(), ballUpperRight.getY(), ball.getCenter().getZ());
		System.out.println(cubeLowerLeft + " " + cubeUpperRight);
		tracer = new Cube(cubeLowerLeft, cubeUpperRight, Color.WHITE);
		objects.add(tracer);
		System.out.println(alpha);
		playerPaddle = new Paddle(new Vector3(0, 0, 50), 32.0, 18.0, new Vector2(70, 50), new Vector2(-70, -50), paddleColor, Color.blue);
		objects.add(playerPaddle);
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
		mouse = mouse.add(new Vector2(-27, -155));
		Vector3 paddleCoords = new Vector3(mouse.getX(), mouse.getY(), 50);
		paddleCoords = paddleCoords.multiply(new Vector3(140.0/747.0, 100.0/533.0, 1.0));
		paddleCoords = new Vector3(paddleCoords.getX() - 70, 50 - paddleCoords.getY(), paddleCoords.getZ());
		playerPaddle.setCenter(paddleCoords);
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
		stepEnemy(1);
		ball.step(playerPaddle, enemyPaddle);
		stepTracer();
	}
	
	public void stepEnemy(int level)
	{
		Vector3 enemyCenter = enemyPaddle.getCenter();
		Vector3 ballCenter = ball.getCenter();
		if(enemyCenter.getX() < ballCenter.getX())
		{
			enemyCenter = enemyCenter.add(new Vector3(level, 0, 0));
		}
		else
		{
			enemyCenter = enemyCenter.add(new Vector3(-level, 0, 0));
		}
		
		if(enemyCenter.getY() < ballCenter.getY())
		{
			enemyCenter = enemyCenter.add(new Vector3(0, level, 0));
		}
		else
		{
			enemyCenter = enemyCenter.add(new Vector3(0, -level, 0));
		}
		enemyPaddle.setCenter(enemyCenter);
		System.out.println(level);
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