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
	
	/**
	 * Initializes a new Workspace3D object.
	 * 
	 * @param ballIn	the Ball object to be used in the game
	 */
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
	
	/**
	 * Returns true if the game is currently running.
	 * 
	 * @return whether the game is running
	 */
	public boolean isRunning()
	{
		return isRunning;
	}
	
	/**
	 * Gets the Camera object of the Workspace3D
	 * 
	 * @return the Workspace3D's Camera object
	 */
	public Camera getCamera()
	{
		return camera;
	}
	
	/**
	 * Starts the Curveball game in the Workspace3D.
	 */
	public void start()
	{
		ball.setVelocity(new Vector3(0, 0, Curveball.BALL_SPEED));
		isRunning = true;
	}
	
	/**
	 * Moves the paddle based on the given MouseEvent information.
	 * 
	 * @param mouse		current MouseEvent object
	 */
	public void movePaddle(Vector2 mouse)
	{
		//sorry for the magic numbers!
		//they allow us to translate the 2D coordinates of the mouse into 3D
		mouse = mouse.add(new Vector2(-27, -155));
		Vector3 paddleCoords = new Vector3(mouse.getX(), mouse.getY(), 50);
		paddleCoords = paddleCoords.multiply(new Vector3(140.0/747.0, 100.0/533.0, 1.0));
		paddleCoords = new Vector3(paddleCoords.getX() - 70, 50 - paddleCoords.getY(), paddleCoords.getZ());
		playerPaddle.setCenter(paddleCoords);
	}
	
	/**
	 * Adds a new GameObject to the list of objects to be rendered with
	 * 	each frame.
	 * 
	 * @param obj	new GameObject to be rendered
	 */
	public void addObject(GameObject obj)
	{
		objects.add(0, obj);
	}
	
	/**
	 * Renders all of the GameObjects in the Workspace3D
	 * @param g
	 */
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		//cover up all of the old stuff
		g.fillRect(0, Curveball.HEADER_Y, Curveball.WINDOW_SIZE, Curveball.WINDOW_SIZE);
		//loop through objects to loop
		for(GameObject obj: objects)
		{
			obj.render(g, camera);
		}
	}
	
	/**
	 * Increments the game by one frame.
	 */
	public void step()
	{
		//magic numbers here again
		//we wanted to have levels but didn't have time
		stepEnemy(1);
		ball.step(playerPaddle, enemyPaddle);
		stepTracer();
		camera.setPosition(camera.getPosition().add(
			new Vector3(
				ball.getVelocity().getX()*0.1,
				ball.getVelocity().getY()*0.1,
				0.0
			)
		));
	}
	
	/**
	 * Increments the enemy Paddle by one frame.
	 * 
	 * @param level		current difficulty of the enemy
	 */
	public void stepEnemy(int level)
	{
		Vector3 enemyCenter = enemyPaddle.getCenter();
		Vector3 ballCenter = ball.getCenter();
		
		//moves the enemy Paddle "level" units toward the Ball
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
		
		//update enemy Paddle center
		enemyPaddle.setCenter(enemyCenter);
	}
	
	/**
	 * Increment the tracer (the white rectangle around the game) by one frame.
	 */
	public void stepTracer()
	{
		Vector3 cubeLowerLeft = tracer.getLowerLeft();
		Vector3 cubeUpperRight = tracer.getUpperRight();
		
		//set new coordinates based on the ball's z position
		cubeLowerLeft = new Vector3(
			cubeLowerLeft.getX(),
			cubeLowerLeft.getY(),
			ball.getCenter().getZ()
		);
		cubeUpperRight = new Vector3(
			cubeUpperRight.getX(),
			cubeUpperRight.getY(),
			ball.getCenter().getZ()
		);
		
		//set new location of tracer
		tracer.setLowerLeft(cubeLowerLeft);
		tracer.setUpperRight(cubeUpperRight);
	}
}