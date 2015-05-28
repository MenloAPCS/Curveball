import java.awt.Color;
import java.awt.Graphics;


public class Cube extends GameObject
{
	//constructs cube based on 2 points (assuming it's parallel to the axes)
	private Vector3 lowerLeft;
	private Vector3 upperRight;
	
	/**
	 * Initializes a new Cube object.
	 * 
	 * @param lowerLeftIn	lower left (close) bound of cube
	 * @param upperRightIn	upper right (far) bound of cube
	 */
	public Cube(Vector3 lowerLeftIn, Vector3 upperRightIn)
	{
		super(
			lowerLeftIn.add(upperRightIn).multiply(new Vector3(0.5, 0.5, 0.5))
		);
		lowerLeft = lowerLeftIn;
		upperRight = upperRightIn;
	}
	
	/**
	 * Initializes a new Cube object.
	 * 
	 * @param lowerLeftIn	lower left (close) bound of cube
	 * @param upperRightIn	upper right (far) bound of cube
	 * @param colorIn		starting color of cube
	 */
	public Cube(Vector3 lowerLeftIn, Vector3 upperRightIn, Color colorIn)
	{
		super(
			lowerLeftIn.add(upperRightIn).multiply(new Vector3(0.5, 0.5, 0.5)), colorIn
		);
		lowerLeft = lowerLeftIn;
		upperRight = upperRightIn;
	}
	
	/**
	 * Sets the lower left (close) bound of the cube
	 * 
	 * @param lowerLeftIn	3D position of new bound
	 */
	public void setLowerLeft(Vector3 lowerLeftIn)
	{
		lowerLeft = lowerLeftIn;
	}
	
	/**
	 * Sets the upper right (far) bound of the cube
	 * 
	 * @param upperRightIn	3D position of new bound
	 */
	public void setUpperRight(Vector3 upperRightIn)
	{
		upperRight = upperRightIn;
	}
	
	/**
	 * Gets the current lower left (close) bound of the cube
	 * 
	 * @return 3D position of current bound
	 */
	public Vector3 getLowerLeft()
	{
		return lowerLeft;
	}
	
	/**
	 * Gets the current upper right (far) bound of the cube
	 * 
	 * @return 3D position of current bound
	 */
	public Vector3 getUpperRight()
	{
		return upperRight;
	}
	
	/**
	 * Renders the cube in 2D space
	 * 
	 * @param g		Graphics object to draw on canvas
	 * @param cam	Camera object to translate points appropriately
	 */
	public void render(Graphics g, Camera cam)
	{
		super.render(g, cam);
		//get all 8 points of the cube
		Vector3 p1 = upperRight;
		Vector3 p2 = new Vector3(upperRight.getX(), lowerLeft.getY(), upperRight.getZ());
		Vector3 p3 = new Vector3(lowerLeft.getX(), lowerLeft.getY(), upperRight.getZ());
		Vector3 p4 = new Vector3(lowerLeft.getX(), upperRight.getY(), upperRight.getZ());
		
		Vector3 p5 = new Vector3(upperRight.getX(), upperRight.getY(), lowerLeft.getZ());
		Vector3 p6 = new Vector3(upperRight.getX(), lowerLeft.getY(), lowerLeft.getZ());
		Vector3 p7 = lowerLeft;
		Vector3 p8 = new Vector3(lowerLeft.getX(), upperRight.getY(), lowerLeft.getZ());
	
		//translate the 8 points to 2D space
		Vector2 d1 = translatePoint(p1, cam);
		Vector2 d2 = translatePoint(p2, cam);
		Vector2 d3 = translatePoint(p3, cam);
		Vector2 d4 = translatePoint(p4, cam);
		
		Vector2 d5 = translatePoint(p5, cam);
		Vector2 d6 = translatePoint(p6, cam);
		Vector2 d7 = translatePoint(p7, cam);
		Vector2 d8 = translatePoint(p8, cam);
		
		//draw lines between the points to make the cube
		g.drawLine(d1.getX(), d1.getY(), d2.getX(), d2.getY());
		g.drawLine(d2.getX(), d2.getY(), d3.getX(), d3.getY());
		g.drawLine(d3.getX(), d3.getY(), d4.getX(), d4.getY());
		g.drawLine(d4.getX(), d4.getY(), d1.getX(), d1.getY());
		
		g.drawLine(d5.getX(), d5.getY(), d6.getX(), d6.getY());
		g.drawLine(d6.getX(), d6.getY(), d7.getX(), d7.getY());
		g.drawLine(d7.getX(), d7.getY(), d8.getX(), d8.getY());
		g.drawLine(d8.getX(), d8.getY(), d5.getX(), d5.getY());
		
		g.drawLine(d1.getX(), d1.getY(), d5.getX(), d5.getY());
		g.drawLine(d2.getX(), d2.getY(), d6.getX(), d6.getY());
		g.drawLine(d3.getX(), d3.getY(), d7.getX(), d7.getY());
		g.drawLine(d4.getX(), d4.getY(), d8.getX(), d8.getY());
	}
}