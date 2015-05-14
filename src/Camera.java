
public class Camera
{
	private Vector3 pos;
	private Vector3 orient;
	
	public Camera(Vector3 posIn, Vector3 orientIn)
	{
		pos = posIn;
		orient = orientIn;
	}
	
	public void setPosition(Vector3 posIn)
	{
		pos = posIn;
	}
	
	public void setOrientation(Vector3 orientIn)
	{
		orient = orientIn;
	}
	
	public Vector3 getPosition()
	{
		return pos;
	}
	
	public Vector3 getOrientation()
	{
		return orient;
	}
	
	public void move(int x, int y, int z)
	{
		Vector3 c = getPosition();
			setPosition(
				new Vector3(c.getX() + x, c.getY() + y, c.getZ() + z
			)
		);
	}
	
	public void turn(double x, double y, double z)
	{
		Vector3 c = getOrientation();
		setOrientation(
			new Vector3(c.getX() + x, c.getY() + y, c.getZ() + z)
		);
	}
}