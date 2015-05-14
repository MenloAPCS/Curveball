import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

import javax.swing.JFrame;


//PLEASE DISREGARD THE HORRIBLE CODE I MADE FOR THE DEMO!!!
public class Curveball extends JFrame implements
	KeyListener, MouseListener, MouseMotionListener
{
	public static final int WINDOW_SIZE = 512;
	public static final int HEADER_Y = 20;
	
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
		workspace = new Workspace3D();
		workspace.addObject(new Sphere(5, new Position3D(0, 0, 50), Color.RED));
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