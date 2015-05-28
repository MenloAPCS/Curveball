import java.awt.Color;

public class Ball extends Sphere
{
	private Vector3 velocity;
	private Vector3 acceleration;
	
	//ball bounces if it's about to exit these bounds
	private Vector3 lowerLeftBound;
	private Vector3 upperRightBound;
	
	/**
	 * Initialize a new Ball object.
	 * 
	 * @param radius				radius of the ball
	 * @param center				coordinates of the center of the ball
	 * @param lowerLeftBoundIn		lower left (close) bound in 3D
	 * @param upperRightBoundIn		upper right (far) bound in 3D
	 * @param zAccel				starting z acceleration of the ball
	 */
	public Ball(
		double radius,
		Vector3 center, 
		Vector3 lowerLeftBoundIn,
		Vector3 upperRightBoundIn, 
		double zAccel
	)
	{
		super(radius, center);
		initialize(lowerLeftBoundIn, upperRightBoundIn, zAccel);
	}
	
	/**
	 * Initialize a new Ball object.
	 * 
	 * @param radius				radius of the ball
	 * @param center				coordinates of the center of the ball
	 * @param lowerLeftBoundIn		lower left (close) bound in 3D
	 * @param upperRightBoundIn		upper right (far) bound in 3D
	 * @param zAccel				starting z acceleration of the ball
	 * @param color					starting color of the ball
	 */
	public Ball(
		double radius,
		Vector3 center,
		Vector3 lowerLeftBoundIn,
		Vector3 upperRightBoundIn,
		double zAccel,
		Color color
	)
	{
		super(radius, center, color);
		initialize(lowerLeftBoundIn, upperRightBoundIn, zAccel);
	}
	
	/**
	 * Called by ball constructors to initialize object.
	 * @param lowerLeftBoundIn		lower left (close) bound in 3D
	 * @param upperRightBoundIn		upper right (far) bound in 3D
	 * @param zAccel				starting z acceleration of the ball
	 */
	private void initialize(
		Vector3 lowerLeftBoundIn,
		Vector3 upperRightBoundIn,
		double zAccel
	)
	{
		velocity = new Vector3(0, 0, 0);
		acceleration = new Vector3(0, 0, zAccel);
		lowerLeftBound = lowerLeftBoundIn;
		upperRightBound = upperRightBoundIn;
	}
	
	/**
	 * Sets the velocity of the ball
	 * 
	 * @param velocityIn	3D vector of new velocity
	 */
	public void setVelocity(Vector3 velocityIn)
	{
		velocity = velocityIn;
	}
	
	/**
	 * Sets the acceleration of the ball
	 * 
	 * @param accelerationIn	3D vector of the new velocity
	 */
	public void setAcceleration(Vector3 accelerationIn)
	{
		acceleration = accelerationIn;
	}
	
	/**
	 * Returns the current velocity of the ball
	 * 
	 * @return 3D vector of the ball's velocity
	 */
	public Vector3 getVelocity()
	{
		return velocity;
	}
	
	/**
	 * Returns the current acceleration of the ball
	 * 
	 * @return 3D vector of the ball's acceleration
	 */
	public Vector3 getAcceleration()
	{
		return acceleration;
	}
	
	/**
	 * Returns the lower left (close) bound of the ball
	 * 
	 * @return 3D position of lower left (close) bound
	 */
	public Vector3 getLowerLeft()
	{
		return lowerLeftBound;
	}
	
	/**
	 * Returns the upper right (far) bound of the ball
	 * 
	 * @return 3D position of upper right (far) bound
	 */
	public Vector3 getUpperRight()
	{
		return upperRightBound;
	}
	
	/**
	 * Advances the ball to the next state with acceleration and velocity.
	 * 
	 * @param playerPaddle	Paddle object of the player's paddle
	 * @param enemyPaddle	Paddle object of the enemy's paddle
	 */
	public void step(Paddle playerPaddle, Paddle enemyPaddle)
	{
		//add acceleration to velocity
		velocity = velocity.add(acceleration);
		
		Vector3 center = super.getCenter();
		//check for and adjust to collisions
		adjustForCollisions(center, playerPaddle, enemyPaddle);
		//add velocity to position
		super.setCenter(center.add(velocity));
	}
	
	/**
	 * Checks the ball's location against the location of the paddles and the
	 * 		bounding box to determine which
	 * @param center			location of Ball center
	 * @param playerPaddle		Paddle object for player
	 * @param enemyPaddle		Paddle object for enemy
	 */
	private void adjustForCollisions(
		Vector3 center,
		Paddle playerPaddle,
		Paddle enemyPaddle
	)
	{
		//get ball's next position
		Vector3 nextPos = center.add(velocity);
		double radius = super.getRadius();
		if(
			nextPos.getX() + radius > upperRightBound.getX() ||
			nextPos.getX() - radius < lowerLeftBound.getX()
		)
		{
			//1: ball is going to hit the left or right of the bounds
			velocity = velocity.multiply(new Vector3(-1, 1, 1));
		}
		if(
			nextPos.getY() + radius > upperRightBound.getY() ||
			nextPos.getY() - radius < lowerLeftBound.getY()
		)
		{
			//2: ball is going to hit the top or bottom of the bounds
			velocity = velocity.multiply(new Vector3(1, -1, 1));
		}
		if(
			nextPos.getZ() + radius > upperRightBound.getZ() ||
			nextPos.getZ() - radius < lowerLeftBound.getZ()
		)
		{
			//3: ball is going to hit a paddle or go out of bounds
			if(nextPos.getZ() - radius < lowerLeftBound.getZ())
			{
				//3a: ball is going to player's side
				Vector3 paddlePos3D = playerPaddle.getCenter();
				Vector2 paddlePos = new Vector2((int) paddlePos3D.getX(), (int) paddlePos3D.getY());
				
				if(
					Math.abs(nextPos.getX() - paddlePos.getX()) < playerPaddle.getWidth() &&
					Math.abs(nextPos.getY() - paddlePos.getY()) < playerPaddle.getHeight()
				)
				{
					//3a-i: ball is going to hit paddle
					Vector2 ballAccel = playerPaddle.getVelocity().multiply(new Vector2(-1, -1));
					Vector3 ballAccel3D = new Vector3(ballAccel.getX(), ballAccel.getY(), Curveball.BALL_Z_ACCEL);
					
					ballAccel3D = ballAccel3D.multiply(new Vector3(0.01, 0.01, 1.0));
					//set new XY acceleration based on movement of paddle
					setAcceleration(ballAccel3D);
				}
				else
				{
					//3a-ii: ball is going to miss paddle
					Curveball gm = new Curveball();
					//restart game and print "Die!"
					gm.main(null);
					System.out.println("Die!");
					System.exit(0);
				}
			}
			else if(nextPos.getZ() + radius > lowerLeftBound.getZ())
			{
				//3b: ball is going to enemy's side
				Vector3 paddlePos3D = enemyPaddle.getCenter();
				Vector2 paddlePos = new Vector2((int) paddlePos3D.getX(), (int) paddlePos3D.getY());
				
				if(
					Math.abs(nextPos.getX() - paddlePos.getX()) < enemyPaddle.getWidth() &&
					Math.abs(nextPos.getY() - paddlePos.getY()) < enemyPaddle.getHeight()
				)
				{
					//3b-i: ball is going to hit paddle
					Vector2 ballAccel = enemyPaddle.getVelocity().multiply(new Vector2(-1, -1));
					Vector3 ballAccel3D = new Vector3(ballAccel.getX(), ballAccel.getY(), Curveball.BALL_Z_ACCEL);
					
					ballAccel3D = ballAccel3D.multiply(new Vector3(0.01, 0.01, 1.0));
					//set new XY acceleration based on movement of paddle
					setAcceleration(ballAccel3D);
				}
				else
				{
					//3b-ii: ball is going to miss paddle
					Curveball gm = new Curveball();
					//restart game and print "Win!"
					gm.main(null);
					System.out.println("Win!");
					System.exit(0);
				}
			}
			//always bounce ball anyway in case (3)
			velocity = velocity.multiply(new Vector3(1, 1, -1));
		}
	}
}