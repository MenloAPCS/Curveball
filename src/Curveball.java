import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

import javax.swing.JFrame;


public class Curveball extends JFrame implements
	KeyListener, MouseListener, MouseMotionListener
{
	public static final int WINDOW_SIZE = 512;
	public static final int HEADER_Y = 20;
	
	public static final Vector3 LOWER_LEFT_CORNER = new Vector3(-50, -50, 50);
	public static final Vector3 UPPER_RIGHT_CORNER = new Vector3(50, 50, 150);
	public static final Vector3 BALL_START = new Vector3(0, 0, 65);
	
	public static final double BALL_RADIUS = 5.0;
	public static final double BALL_Z_ACCEL = 0.05;
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
		gp.addKeyListener(gp);
		gp.addMouseListener(gp);
		gp.addMouseMotionListener(gp);
		gp.start();
	}
	
	public void start()
	{
		setupWorkspace();
		loopGame();
	}
	
	private void setupWorkspace()
	{
		workspace = new Workspace3D();
		Ball ball = new Ball(
			BALL_RADIUS, BALL_START,
			LOWER_LEFT_CORNER, UPPER_RIGHT_CORNER,
			BALL_Z_ACCEL, BALL_COLOR
		);
		ball.setAcceleration(new Vector3(0, -0.09, 0));
		ball.setVelocity(new Vector3(0.5, 0.3, -1));
		Cube cube = new Cube(LOWER_LEFT_CORNER, UPPER_RIGHT_CORNER);
		workspace.addObject(cube);
		workspace.addBall(ball);
	}
	
	private void loopGame()
	{
		while(true)
		{
			workspace.step();
			repaint();
			try
			{
				Thread.sleep(15);
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
	public void mouseMoved(MouseEvent arg0) 
	{
	
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
