import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import javax.swing.JFrame;


public class Curveball extends JFrame implements
	MouseListener, MouseMotionListener
{
	public static final int WINDOW_SIZE = 800;
	public static final int HEADER_Y = 20;
	
	public static final int FRAMES_PER_SEC = 50;
	public static final int MILLISEC_DELAY = 1000 / FRAMES_PER_SEC;
	
	public static final int NUM_DIVIDERS = 10;
	
	public static final Vector3 LOWER_LEFT_CORNER = new Vector3(-70, -50, 50);
	public static final Vector3 UPPER_RIGHT_CORNER = new Vector3(70, 50, 500);
	public static final Vector3 BALL_START = new Vector3(0, 0, 65);
	
	public static final double BALL_RADIUS = 8.2;
	public static final double BALL_Z_ACCEL = 0.08;
	public static final double BALL_SPEED = 3.0;
	public static final Color BALL_COLOR = Color.GREEN;
	
	private Workspace3D workspace;
	
	private HashSet<Character> activeEvents = new HashSet<Character>();
	
	public static void main(String[] args)
	{
		Curveball gp = new Curveball();
		
		gp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.setTitle("Curveball");
		gp.setSize(WINDOW_SIZE, WINDOW_SIZE + HEADER_Y);
		gp.setVisible(true);
		gp.setResizable(false);
		gp.setBackground(Color.BLACK);
		
		//Add listeners
		gp.addMouseListener(gp);
		gp.addMouseMotionListener(gp);
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		gp.getContentPane().setCursor(blankCursor);
		
		gp.start();
	}
	
	public void start()
	{
		setupWorkspace();
		loopGame();
	}
	
	private void setupWorkspace()
	{
		Ball ball = new Ball(
			BALL_RADIUS, BALL_START,
			LOWER_LEFT_CORNER, UPPER_RIGHT_CORNER,
			BALL_Z_ACCEL, BALL_COLOR
		);
		workspace = new Workspace3D(ball);
		Cube bounds = new Cube(LOWER_LEFT_CORNER, UPPER_RIGHT_CORNER);
		workspace.addObject(bounds);
		int zDistance = (int) (UPPER_RIGHT_CORNER.getZ() - LOWER_LEFT_CORNER.getZ());
		int interval = zDistance / (NUM_DIVIDERS + 1);
		for(int i = 1; i <= NUM_DIVIDERS; i++)
		{
			int z = ((int) LOWER_LEFT_CORNER.getZ()) + i * interval;
			Vector3 lowerLeft = new Vector3(LOWER_LEFT_CORNER.getX(), LOWER_LEFT_CORNER.getY(), z);
			Vector3 upperRight = new Vector3(UPPER_RIGHT_CORNER.getX(), UPPER_RIGHT_CORNER.getY(), z);
			Cube divider = new Cube(lowerLeft, upperRight);
			workspace.addObject(divider);
		}
	}
	
	private void loopGame()
	{
		while(true)
		{
			if(workspace.isRunning())
			{
				workspace.step();
				repaint();
			}
			try
			{
				Thread.sleep(MILLISEC_DELAY);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g)
	{
		//make buttons / labels here
		if(workspace != null)
		{
			workspace.render(g);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if(workspace.isRunning())
		{
			workspace.movePaddle(new Vector2(e.getX(), e.getY()));
		}
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println(new Vector2(e.getX(), e.getY()));
		if(!workspace.isRunning())
		{
			workspace.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
