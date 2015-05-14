import java.awt.Color;

public class Ball extends Sphere
{
	int vX;
	int vY;
	int vZ;
	
	public Ball(int radius, Position3D center)
	{
		super(radius, center);
		vX = vY = vZ = 0;
	}
	
	public Ball(int radius, Position3D center, Color color)
	{
		super(radius, center, color);
		vX = vY = vZ = 0;
	}
}