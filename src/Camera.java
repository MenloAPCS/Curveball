
public class Camera
{
	private Position3D pos;
	private Orientation orient;
	
	public Camera(Position3D posIn, Orientation orientIn)
	{
		pos = posIn;
		orient = orientIn;
	}
	
	public void setPosition(Position3D posIn)
	{
		pos = posIn;
	}
	
	public void setOrientation(Orientation orientIn)
	{
		orient = orientIn;
	}
	
	public Position3D getPosition()
	{
		return pos;
	}
	
	public Orientation getOrientation()
	{
		return orient;
	}
	
	public void move(int x, int y, int z)
	{
		Position3D c = getPosition();
			setPosition(
				new Position3D(c.getX() + x, c.getY() + y, c.getZ() + z
			)
		);
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void turn(double x, double y, double z)
	{
		Orientation c = getOrientation();
		setOrientation(
			new Orientation(c.getX() + x, c.getY() + y, c.getZ() + z)
		);
	}
}