public class Paddle extends Rectangle
{
	private Vector2 upperRightBound;
	private Vector2 lowerLeftBound;
	
	public Paddle(Vector3 p1In, Vector3 p2In, Vector3 p3In, Vector3 p4In, Vector2 upperRightBoundIn, Vector2 lowerLeftBoundIn)
	{
		super(p1In, p2In, p3In, p4In);
		upperRightBound = upperRightBoundIn;
		lowerLeftBound = lowerLeftBoundIn;
	}
}
