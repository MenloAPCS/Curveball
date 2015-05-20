import java.awt.Color;

public class Ball extends Sphere
{
	private Vector3 velocity;
	private Vector3 acceleration;
	private Vector3 lowerLeftBound;
	private Vector3 upperRightBound;
	
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
	
	public void setVelocity(Vector3 velocityIn)
	{
		velocity = velocityIn;
	}
	
	public void setAcceleration(Vector3 accelerationIn)
	{
		acceleration = accelerationIn;
	}
	
	public Vector3 getVelocity()
	{
		return velocity;
	}
	
	public Vector3 getAcceleration()
	{
		return acceleration;
	}
	
	public Vector3 getLowerLeft()
	{
		return lowerLeftBound;
	}
	
	public Vector3 getUpperRight()
	{
		return upperRightBound;
	}
	
<<<<<<< HEAD
	public void step(Paddle playerPaddle, Paddle enemyPaddle)
=======
	public void step()
>>>>>>> origin/master
	{
		//System.out.println("Step!");
		//System.out.println("c: " + super.getCenter() + ", v: " + velocity + ", a: " + acceleration);
		velocity = velocity.add(acceleration);
		Vector3 center = super.getCenter();
<<<<<<< HEAD
		adjustForCollisions(center, playerPaddle, enemyPaddle);
		super.setCenter(center.add(velocity));
	}
	
	private void adjustForCollisions(Vector3 center, Paddle playerPaddle, Paddle enemyPaddle)
=======
		adjustForCollisions(center);
		super.setCenter(center.add(velocity));
	}
	
	private void adjustForCollisions(Vector3 center)
>>>>>>> origin/master
	{
		Vector3 nextPos = center.add(velocity);
		double radius = super.getRadius();
		if(
			nextPos.getX() + radius > upperRightBound.getX() ||
			nextPos.getX() - radius < lowerLeftBound.getX()
		)
		{
			velocity = velocity.multiply(new Vector3(-1, 1, 1));
		}
		if(
			nextPos.getY() + radius > upperRightBound.getY() ||
			nextPos.getY() - radius < lowerLeftBound.getY()
		)
		{
			velocity = velocity.multiply(new Vector3(1, -1, 1));
		}
		if(
			nextPos.getZ() + radius > upperRightBound.getZ() ||
			nextPos.getZ() - radius < lowerLeftBound.getZ()
		)
		{

			if(nextPos.getZ() - radius < lowerLeftBound.getZ())
			{
				Vector3 paddlePos3D = playerPaddle.getCenter();
				Vector2 paddlePos = new Vector2((int) paddlePos3D.getX(), (int) paddlePos3D.getY());
				
				if(
					Math.abs(nextPos.getX() - paddlePos.getX()) < playerPaddle.getWidth() &&
					Math.abs(nextPos.getY() - paddlePos.getY()) < playerPaddle.getHeight()
				)
				{
					Vector2 ballAccel = playerPaddle.getVelocity().multiply(new Vector2(-1, -1));
					Vector3 ballAccel3D = new Vector3(ballAccel.getX(), ballAccel.getY(), Curveball.BALL_Z_ACCEL);
					ballAccel3D = ballAccel3D.multiply(new Vector3(0.01, 0.01, 1.0));
					setAcceleration(ballAccel3D);
				}
				else
				{
					Curveball gm = new Curveball();
					gm.main(null);
					System.out.println("Die!");
					System.exit(0);
				}
			}
			else if(nextPos.getZ() + radius > lowerLeftBound.getZ())
			{
				Vector3 paddlePos3D = enemyPaddle.getCenter();
				Vector2 paddlePos = new Vector2((int) paddlePos3D.getX(), (int) paddlePos3D.getY());
				
				if(
					Math.abs(nextPos.getX() - paddlePos.getX()) < enemyPaddle.getWidth() &&
					Math.abs(nextPos.getY() - paddlePos.getY()) < enemyPaddle.getHeight()
				)
				{
					Vector2 ballAccel = enemyPaddle.getVelocity().multiply(new Vector2(-1, -1));
					Vector3 ballAccel3D = new Vector3(ballAccel.getX(), ballAccel.getY(), Curveball.BALL_Z_ACCEL);
					ballAccel3D = ballAccel3D.multiply(new Vector3(0.01, 0.01, 1.0));
					setAcceleration(ballAccel3D);
				}
				else
				{
					Curveball gm = new Curveball();
					gm.main(null);
					System.out.println("Win!");
					System.exit(0);
				}
			}
			velocity = velocity.multiply(new Vector3(1, 1, -1));
		}
	}
}