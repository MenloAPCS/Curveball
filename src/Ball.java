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
	
	public void step()
	{
		//System.out.println("Step!");
		//System.out.println("c: " + super.getCenter() + ", v: " + velocity + ", a: " + acceleration);
		velocity = velocity.add(acceleration);
		Vector3 center = super.getCenter();
		adjustForCollisions(center);
		super.setCenter(center.add(velocity));
	}
	
	private void adjustForCollisions(Vector3 center)
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
			velocity = velocity.multiply(new Vector3(1, 1, -1));
		}
	}
}